package org.dice_research.paul_parser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

/**
 * Prints / writes CSV contents
 * 
 * @author Adrian Wilke
 */
public class CsvPrinter {

	List<StudentContainer> students;

	public CsvPrinter(List<StudentContainer> students) {
		this.students = students;
	}

	public void print(File file) throws IOException {
		CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(file), CSVFormat.DEFAULT);
		printData(csvPrinter);
	}

	protected void printData(CSVPrinter csvPrinter) throws IOException {
		for (StudentContainer student : students) {
			csvPrinter.printRecord(student.id, student.surname, student.firstName, student.matriculationNumber,
					student.email);
		}
		csvPrinter.flush();
	}
}