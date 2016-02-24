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
package com.subhadip.phonebookprocessor.exception;

public enum Exceptions {

	FILE_NOT_FOUND_1("The file");
	
	private String message;
	
	private Exceptions(String message) {
		this.message = message;
	}
	
	public Exception get() {
		return new Exception(message);
	}
	
	public PhonebookProcessorException getBusinessException() {
		return new PhonebookProcessorException(message);
	}
}
