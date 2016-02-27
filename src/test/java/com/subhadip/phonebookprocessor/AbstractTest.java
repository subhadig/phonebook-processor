package com.subhadip.phonebookprocessor;

import org.junit.Rule;
import org.junit.rules.ExpectedException;

public abstract class AbstractTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none(); 
}
