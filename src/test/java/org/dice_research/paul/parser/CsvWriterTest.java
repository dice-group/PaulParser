package org.dice_research.paul.parser;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.dice_research.paul_parser.CsvPrinter;
import org.dice_research.paul_parser.PaulParser;
import org.dice_research.paul_parser.StudentContainer;
import org.junit.Test;

public class CsvWriterTest {

	@Test
	public void testWrittenFile() throws IOException {
		PaulParser parser = new PaulParser();
		parser.parse(Resources.getResource(Resources.FILE_ENGLISH));
		CsvPrinter csvPrinter = new CsvPrinter(parser.getStudents());
		File testFile = File.createTempFile("test", ".tmp");
		testFile.deleteOnExit();
		csvPrinter.print(testFile);

		String data = FileUtils.readFileToString(testFile, Charset.defaultCharset());
		assertTrue(data.contains("johndoe"));
		assertTrue(data.contains("adamsmith@mail.uni-paderborn.de"));
	}

	@Test
	public void testEscaping() throws IOException {
		PaulParser parser = new PaulParser();
		parser.parse(Resources.getResource(Resources.FILE_ENGLISH));

		List<StudentContainer> students = parser.getStudents();
		StudentContainer student = students.get(0);
		student.firstName = "Alice,Bob";

		CsvPrinter csvPrinter = new CsvPrinter(students);
		File testFile = File.createTempFile("test", ".tmp");
		testFile.deleteOnExit();
		csvPrinter.print(testFile);

		String data = FileUtils.readFileToString(testFile, Charset.defaultCharset());
		assertTrue(data.contains("\"Alice,Bob\""));
	}

}