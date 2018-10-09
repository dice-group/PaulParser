package org.dice_research.paul.parser;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.dice_research.paul_parser.Main;
import org.junit.Test;

public class MainTest {

	@Test
	public void test() throws IOException {
		File testInputFile = Resources.getResource(Resources.FILE_ENGLISH);
		File testOutputFile = File.createTempFile("test_output", ".tmp");
		testOutputFile.deleteOnExit();

		List<String> argsList = new LinkedList<String>();
		argsList.add("-i");
		argsList.add(testInputFile.getAbsolutePath());
		argsList.add("-o");
		argsList.add(testOutputFile.getAbsolutePath());
		String[] argsArray = new String[argsList.size()];
		Main.main(argsList.toArray(argsArray));

		String data = FileUtils.readFileToString(testOutputFile, Charset.defaultCharset());
		System.out.println(data);
		assertTrue(data.contains("John,Doe"));
		assertTrue(data.contains("johndoe"));
		assertTrue(data.contains("adamsmith@mail.uni-paderborn.de"));
	}

	@Test
	public void testDemiliter() throws IOException {
		File testInputFile = Resources.getResource(Resources.FILE_ENGLISH);
		File testOutputFile = File.createTempFile("test_output", ".tmp");
		testOutputFile.deleteOnExit();

		List<String> argsList = new LinkedList<String>();
		argsList.add("-i");
		argsList.add(testInputFile.getAbsolutePath());
		argsList.add("-o");
		argsList.add(testOutputFile.getAbsolutePath());
		argsList.add("-d");
		argsList.add("\t");
		String[] argsArray = new String[argsList.size()];
		Main.main(argsList.toArray(argsArray));

		String data = FileUtils.readFileToString(testOutputFile, Charset.defaultCharset());
		assertTrue(data.contains("John\tDoe"));
	}

	@Test
	public void testTypes() throws IOException {
		File testInputFile = Resources.getResource(Resources.FILE_ENGLISH);
		File testOutputFile = File.createTempFile("test_output", ".tmp");
		testOutputFile.deleteOnExit();

		List<String> argsList = new LinkedList<String>();
		argsList.add("-i");
		argsList.add(testInputFile.getAbsolutePath());
		argsList.add("-o");
		argsList.add(testOutputFile.getAbsolutePath());
		argsList.add("-t");
		argsList.add("fsiem");
		String[] argsArray = new String[argsList.size()];
		Main.main(argsList.toArray(argsArray));

		String data = FileUtils.readFileToString(testOutputFile, Charset.defaultCharset());
		assertTrue(data.contains("John,Doe,johndoe,johndoe@mail.uni-paderborn.de,6754321"));
		assertTrue(data.contains("Adam,Smith,adamsmith,adamsmith@mail.uni-paderborn.de,7654321"));
	}

}