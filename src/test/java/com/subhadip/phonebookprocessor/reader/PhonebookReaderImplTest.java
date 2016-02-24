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

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.subhadip.phonebookprocessor.logger.TestLogger;

public class PhonebookReaderImplTest {

	private PhonebookReader phonebookReader = new CsvPhonebookReaderImpl(); 
	
	@BeforeClass
	public static void setUp() throws Exception{
		TestLogger.setUp();
	}
	
	@Test
	public void testReadPhonebook() {
		Set<String> manadatoryParams = new HashSet<>();
		manadatoryParams.add("Name");
		Collection<Map<String, String>> resultMaps = phonebookReader.readPhonebook("src/test/data/googleContacts.csv", "utf-16", ",", manadatoryParams);
		System.out.printf("Returned collection size: %d", resultMaps.size());
	}

}
