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

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.subhadip.phonebookprocessor.AbstractTest;
import com.subhadip.phonebookprocessor.exception.PhonebookProcessorException;
import com.subhadip.phonebookprocessor.logger.TestLogger;

public class CsvPhonebookReaderImplTest extends AbstractTest {

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
		assertEquals("Returned collection size is not matching", 8, resultMaps.size());
		
		for (Map<String, String> eachMap : resultMaps) {
			assertFalse("Mandatory param name is null", null == eachMap.get("Name"));
			assertFalse("Mandatory param name is empty", null != eachMap.get("Name") && eachMap.get("Name").toString().isEmpty());
			
			if ("abc4 cdm4".equals(eachMap.get("Name"))) {
				assertEquals("Phone number not matching", "1256487793", eachMap.get("Phone 1 - Value"));
				assertEquals("Email not matching", "abc2@email.com", eachMap.get("E-mail 1 - Value"));
			}
		}
		System.out.printf("Returned collection size: %d", resultMaps.size());
	}

	@Test
	public void testFileNotFoundException() {
		thrown.expect(PhonebookProcessorException.class);
		thrown.expectMessage("The file with the specified location(src/test/data/googleContacts.csv1) not found");
		phonebookReader.readPhonebook("src/test/data/googleContacts.csv1", "utf-16", ",", null);
	}
	
}
