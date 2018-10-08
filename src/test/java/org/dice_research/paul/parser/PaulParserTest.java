package org.dice_research.paul.parser;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;
import org.dice_research.paul_parser.PaulParser;
import org.junit.Test;

public class PaulParserTest {

	@Test
	public void testEnglish() throws IOException {
		PaulParser parser = new PaulParser();
		parser.parse(Resources.getResource(Resources.FILE_ENGLISH));
		assertEquals(2, parser.getStudents().size());
	}

	@Test
	public void testGerman() throws IOException {
		PaulParser parser = new PaulParser();
		parser.parse(Resources.getResource(Resources.FILE_GERMAN));
		assertEquals(2, parser.getStudents().size());
	}

	@Test
	public void testStringInput() throws IOException {
		PaulParser parser = new PaulParser();
		String string = FileUtils.readFileToString(Resources.getResource(Resources.FILE_GERMAN),
				Charset.defaultCharset());
		parser.parse(string);
		assertEquals(2, parser.getStudents().size());
	}

}