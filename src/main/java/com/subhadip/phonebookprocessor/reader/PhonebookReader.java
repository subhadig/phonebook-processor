package com.subhadip.phonebookprocessor.reader;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface PhonebookReader {

	/**
	 * <p>
	 * It reads the phonebook from specified location and converts it to
	 * collection of phonebook entry map. While reading, it checks if for any
	 * entry, all the mandatory params are not non-empty, then discards the
	 * entry.
	 * </p>
	 * 
	 * @param phonebookLoc
	 * @param charsetName
	 * @param separator
	 * @param manadatoryParams
	 * @return Collection of phonebook entry map
	 */
	Collection<Map<String, String>> readPhonebook(String phonebookLoc, String charsetName, String separator, Set<String> manadatoryParams);
}
