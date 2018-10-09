package org.dice_research.paul_parser;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;

/**
 * Main entry point
 * 
 * @author Adrian Wilke
 */
public class Main {

	public static void main(String[] args) {

		CommandLineInterface commandLineInterface = new CommandLineInterface();

		// User has no arguments
		if (args.length == 0) {
			commandLineInterface.printHelp(commandLineInterface.getOptions());
			return;
		}

		// Parse arguments
		CommandLine commandLine = null;
		try {
			commandLine = new DefaultParser().parse(commandLineInterface.getOptions(), args);
		} catch (ParseException e) {
			System.err.println("Error: " + e.getMessage());
			commandLineInterface.printHelp(commandLineInterface.getOptions());
			return;
		}

		// Check input file
		File inputFile = new File(commandLine.getOptionValue(CommandLineInterface.OPTION_INPUT));
		if (!inputFile.canRead()) {
			System.err.println("Can not read file " + inputFile.getPath());
			return;
		}

		// Parse input file
		List<StudentContainer> students = new LinkedList<StudentContainer>();
		try {
			students = new PaulParser().parse(inputFile).getStudents();
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
			return;
		}

		// Check types
		String types = commandLine.getOptionValue(CommandLineInterface.OPTION_TYPES);
		if (null == types) {
			// Use all types
			StringBuilder stringBuilder = new StringBuilder();
			for (String key : CommandLineInterface.TYPES.keySet()) {
				stringBuilder.append(key);
			}
			types = stringBuilder.toString();
		} else {
			// Check defined types
			for (int i = 0; i < types.length(); i++) {
				String type = types.substring(i, i + 1);
				if (!CommandLineInterface.TYPES.keySet().contains(type)) {
					System.err.println("Error: Unknown type " + type);
					return;
				}
			}
		}

		// Write / print
		try {
			String outputFile = commandLine.getOptionValue(CommandLineInterface.OPTION_OUTPUT);
			CsvPrinter csvPrinter = new CsvPrinter(students, types);

			// Demiliter
			String demiliter = commandLine.getOptionValue(CommandLineInterface.OPTION_DEMILITER);
			if (null != demiliter) {
				csvPrinter.setDemiliter(demiliter.charAt(0));
			}

			// Print or write
			if (outputFile == null) {
				csvPrinter.print();
			} else {
				csvPrinter.print(new File(outputFile));
			}
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
			return;
		}
	}
}