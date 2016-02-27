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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.subhadip.phonebookprocessor.exception.PhonebookProcessorException;

import ezvcard.VCard;
import ezvcard.io.text.VCardReader;
import ezvcard.property.VCardProperty;

public class VcardPhonebookReaderImpl implements PhonebookReader{
	
	private static Logger LOGGER = Logger.getLogger(CsvPhonebookReaderImpl.class.getName());
	
	private static final Properties properties;
	
	static {
		InputStream propertyInputStream = VcardPhonebookReaderImpl.class.getResourceAsStream("/properties/vcardReader.properties");
		properties = new Properties();
		try {
			properties.load(propertyInputStream);
		} catch (IOException e) {
			LOGGER.severe("Exception reading properties/vcardReader.properties.");
			throw new PhonebookProcessorException("Exception reading properties/vcardReader.properties.", e);
		}
	}

	@Override
	public Collection<Map<String, String>> readPhonebook(String phonebookLoc, String charsetName, String separator,
			Set<String> manadatoryParams) {
		
		Collection<Map<String, String>> phonebookCollection = new ArrayList<>();
		
		try (VCardReader vCardReader = new VCardReader(new File(phonebookLoc))) {
			
			VCard vCard = null;
			while( (vCard = vCardReader.readNext()) != null ) {
				Map<String, String> eachRecord = new HashMap<>();
				phonebookCollection.add(eachRecord);
				for (VCardProperty eachProperty : vCard.getProperties()) {
					Class<?> propertyClass = eachProperty.getClass();
					String key = propertyClass.getSimpleName();
					if (!properties.containsKey(key)) { //If the vCard property is not handled
						LOGGER.severe(String.format("The vCard property:%s is not handled, skipping the property. vCard property:%s"
								, key, eachProperty));
						continue;
					}
					try {
						Method method = propertyClass.getMethod("get" + properties.getProperty(key), new Class[0]);
						Object value = method.invoke(eachProperty, new Object[0]);
						eachRecord.put(key, value.toString());
					} catch (SecurityException | ReflectiveOperationException | IllegalArgumentException e) {
						LOGGER.severe(e.getMessage());
						LOGGER.log(Level.SEVERE, String.format("Exception access getter method for %s in VCard", key), e);
					}
					
				}
			}
			
		} catch (FileNotFoundException e1) {
			LOGGER.severe(String.format("Specified vCard in the location:(%s) not found.", phonebookLoc));
			throw new PhonebookProcessorException(String.format("Specified vCard in the location:(%s) not found.", phonebookLoc), e1);
		} catch (IOException e) {
			LOGGER.severe(String.format("Exception reading from file in location:%s", phonebookLoc));
			throw new PhonebookProcessorException(String.format("Exception reading from file in location:%s", phonebookLoc), e);
		}
		
		
		return phonebookCollection;
	}

}
