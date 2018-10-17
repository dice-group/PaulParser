package org.dice_research.paul_parser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

/**
 * Prints / writes CSV contents
 * 
 * @author Adrian Wilke
 */
public class CsvPrinter {

	protected List<StudentContainer> students;
	protected CSVFormat csvFormat = CSVFormat.DEFAULT;
	protected String types;

	public CsvPrinter(List<StudentContainer> students, String types) {
		this.students = students;
		this.types = types;
	}

	public CsvPrinter setDemiliter(char demiliter) {
		this.csvFormat = this.csvFormat.withDelimiter(demiliter);
		return this;
	}

	public void print(File file) throws IOException {
		CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(file), this.csvFormat);
		printData(csvPrinter);
	}

	public void print() throws IOException {
		CSVPrinter csvPrinter = new CSVPrinter(System.out, this.csvFormat);
		printData(csvPrinter);
	}

	public void print(Appendable out) throws IOException {
		CSVPrinter csvPrinter = new CSVPrinter(out, this.csvFormat);
		printData(csvPrinter);
	}

	protected void printData(CSVPrinter csvPrinter) throws IOException {
		for (StudentContainer student : students) {
			List<String> values = new LinkedList<String>();
			for (char type : this.types.toCharArray()) {
				if (String.valueOf(type).equals(CommandLineInterface.TYPE_ID)) {
					values.add(student.id);
				} else if (String.valueOf(type).equals(CommandLineInterface.TYPE_FIRSTNAME)) {
					values.add(student.firstName);
				} else if (String.valueOf(type).equals(CommandLineInterface.TYPE_SURNAME)) {
					values.add(student.surname);
				} else if (String.valueOf(type).equals(CommandLineInterface.TYPE_MATRICULATION)) {
					values.add("" + student.matriculationNumber);
				} else if (String.valueOf(type).equals(CommandLineInterface.TYPE_EMAIL)) {
					values.add(student.email);
				} else {
					throw new IOException("Unknown type");
				}
			}
			csvPrinter.printRecord(values);
		}
		csvPrinter.flush();
	}
}