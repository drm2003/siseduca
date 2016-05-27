package br.com.cdan.util;

import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VersionTest {

	private static Logger LOG = LoggerFactory.getLogger(VersionTest.class);
	
	@Test
	public void testVersion()
	{
		try {
			LOG.debug("Version from filtered properties :"+ Version.VALUE);
			assertFalse("arquillian2-util.properties is not filtered", "${app.version}".equals(Version.VALUE));
		} catch (ExceptionInInitializerError e) {
			LOG.error("arquillian2-util.properties could not be read");
			fail("Filtering is buggy under M2Eclipse, try cleaning the project or doing a 'touch' on arquillian2-util.properties");
		}
	}
}
