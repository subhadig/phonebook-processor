package com.subhadip.phonebookprocessor.reader;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.subhadip.phonebookprocessor.AbstractTest;
import com.subhadip.phonebookprocessor.exception.PhonebookProcessorException;

public class VcardPhonebookReaderImplTest extends AbstractTest {

	private PhonebookReader phonebookReader;
	
	@Before
	public void setUp() throws Exception {
		phonebookReader = new VcardPhonebookReaderImpl();
	}

	@Test
	public void testReadPhonebook() {
		Set<String> mandatoryParams = new HashSet<>();
		mandatoryParams.add(null);
		Collection<Map<String, String>> result = phonebookReader.readPhonebook("src/test/data/vCardContacts.dat", "UTF-8", ";", mandatoryParams);
		assertEquals("Number of expected contacts not matching", 2, result.size());
		Map<String, String> firstContact = result.iterator().next();
		assertEquals("StructuredName not matching", "Abhishek RC", firstContact.get("StructuredName"));
		assertEquals("Telephone not matching", "1234567890", firstContact.get("Telephone")); 
	}

	@Test
	public void testFileNotFoundException() {
		thrown.expect(PhonebookProcessorException.class);
		thrown.expectMessage("Specified vCard in the location:(src/test/data/vCardContacts.dat1) not found.");
		phonebookReader.readPhonebook("src/test/data/vCardContacts.dat1", "utf-8", ";", null);
	}
}
