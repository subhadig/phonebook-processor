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
