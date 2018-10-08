package org.dice_research.paul_parser;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Main entry point
 * 
 * @author Adrian Wilke
 */
public class Main {

	public static void main(String[] args) {

		if (args.length != 2) {
			printInfo();
			System.exit(0);
		}

		File inputFile = new File(args[0]);
		if (!inputFile.canRead()) {
			System.err.println("Can not read file " + inputFile.getPath());
			System.exit(1);
		}

		File outputFile = new File(args[1]);

		List<StudentContainer> students = new LinkedList<StudentContainer>();
		try {
			students = new PaulParser().parse(inputFile).getStudents();
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
			System.exit(1);
		}

		try {
			new CsvPrinter(students).print(outputFile);
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
			System.exit(1);
		}
	}

	protected static void printInfo() {
		System.out.println("Parser for PAUL extended participants list");
		System.out.println("Find list at: PAUL > My Courses > Term administration > Course Overview");
		System.out.println("                   > Event > Participants > Extended list");
		System.out.println("Call parameters: <input.txt> <output.csv>");
	}
}