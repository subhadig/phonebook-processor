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
