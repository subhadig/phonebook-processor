/*******************************************************************************
 * Copyright 2016 Subhadip Ghosh
 *
 * This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package com.subhadip.phonebookprocessor.reader;

import static java.lang.String.format;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import com.subhadip.phonebookprocessor.exception.PhonebookProcessorException;

/**
 * @author subhadip
 *
 */
public class CsvPhonebookReaderImpl implements PhonebookReader {

	private static Logger LOGGER = Logger.getLogger(CsvPhonebookReaderImpl.class.getName());

	public Collection<Map<String, String>> readPhonebook(String phonebookLoc, String charsetName, String separator,
			Set<String> manadatoryParams) {

		Collection<Map<String, String>> phonebookCollection = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(phonebookLoc), charsetName))) {
			String line = null;
			String[] labels = null;

			fileReadingLoop: while ((line = reader.readLine()) != null) {

				if (null == labels) { // First row, labels row
					labels = line.split(separator);
				} else { // Second row onwards, data rows
					String[] elements = line.split(separator);

					if (elements.length > labels.length) {
						throw new PhonebookProcessorException(format(
								"No of elements can not be greater than no of labels in file:%s for elements:\n%s", phonebookLoc, line));
					}

					Map<String, String> elementsMap = new HashMap<>(labels.length);

					for (int i = 0; i < labels.length; i++) {
						
						if (manadatoryParams.contains(labels[i]) && (i > elements.length || elements[i].isEmpty())) {
							LOGGER.info(format("Manadatory param \"%s\" not present, discarding line:%s", labels[i], line));
							continue fileReadingLoop;
						}
						
						if (i < elements.length) {
							elementsMap.put(labels[i], elements[i]);
						} else {
							elementsMap.put(labels[i], ""); // Put empty string as value if no more value
						}
					}
					phonebookCollection.add(elementsMap);
				}

			}
		} catch (FileNotFoundException e) {
			throw new PhonebookProcessorException(format("The file with the specified location(%s) not found", phonebookLoc), e);
		} catch (IOException e) {
			throw new PhonebookProcessorException(format("Error reading from the file %s with charset %s", phonebookLoc, charsetName), e);
		}
		return phonebookCollection;
	}

}
