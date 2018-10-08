package org.dice_research.paul_parser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;

/**
 * Parser for PAUL > My Courses > Term administration > Course Overview > Event
 * > Participants > Extended list
 * 
 * @author Adrian Wilke
 */
public class PaulParser {

	// Columns of extended participant list

	public static int COLUMN_NO = 0;
	public static int COLUMN_MATRIC_NO = 1;
	public static int COLUMN_NAME = 2;
	public static int COLUMN_FIRST_NAME = 3;
	public static int COLUMN_ADVISER = 4;
	public static int COLUMN_CLASS = 5;
	public static int COLUMN_MODULE_REFERENCE = 6;
	public static int COLUMN_OBLIGATION_LEVEL = 7;
	public static int COLUMN_UNI_MAIL = 8;
	public static int COLUMN_SUBJECTS = 9;
	public static int COLUMN_AIMED_DEGREE = 10;
	public static int COLUMN_SUBJECT_SEMESTERS = 11;

	// Copy-Paste can create additional tabulator on line breaks
	public static int EXPECTED_COLUMN_SIZE = 12 + 1;

	List<StudentContainer> students = new LinkedList<StudentContainer>();

	/**
	 * Parses file containing extended participant list
	 * 
	 * @throws IOException
	 */
	public PaulParser parse(File file) throws IOException {
		List<String> lines = FileUtils.readLines(file, Charset.defaultCharset());
		String preparedData = prepareData(lines);
		parsePreparedData(preparedData);
		return this;
	}

	/**
	 * Parses string containing extended participant list
	 * 
	 * @throws IOException
	 */
	public PaulParser parse(String string) throws IOException {
		// https://stackoverflow.com/a/454913
		List<String> lines = Arrays.asList(string.split("\\r?\\n"));
		String preparedData = prepareData(lines);
		parsePreparedData(preparedData);
		return this;
	}

	/**
	 * Returns parsed list of students
	 */
	public List<StudentContainer> getStudents() {
		return this.students;
	}

	/**
	 * Removes empty lines and lines not beginning with integer.
	 */
	protected String prepareData(List<String> lines) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int l = lines.size() - 1; l >= 0; l--) {
			String line = lines.get(l);

			// Empty lines
			if (line.isEmpty()) {
				continue;
			}

			// Lines not starting with integer
			try {
				Integer.parseInt(line.substring(0, 1));
			} catch (Exception e) {
				continue;
			}

			// Add line
			stringBuilder.append(line);
			stringBuilder.append(System.lineSeparator());
		}

		return stringBuilder.toString();
	}

	/**
	 * Parses prepared CSV and creates student containers
	 * 
	 * @param preparedData
	 *            CSV data containing only student lines
	 */
	protected void parsePreparedData(String preparedData) throws IOException {

		// CSV format of prepared copy-paste data
		CSVFormat csvFormat = CSVFormat.DEFAULT;
		csvFormat = csvFormat.withDelimiter('\t');
		csvFormat = csvFormat.withRecordSeparator(System.lineSeparator());

		// Parse data
		CSVParser csvParser = CSVParser.parse(preparedData, csvFormat);
		Iterator<CSVRecord> iterator = csvParser.iterator();
		while (iterator.hasNext()) {
			CSVRecord csvRecord = iterator.next();

			if (EXPECTED_COLUMN_SIZE < csvRecord.size()) {
				throw new IOException(
						"Wrong column size (" + csvRecord.size() + ") in record " + csvRecord.getRecordNumber());
			}

			// Create and add student
			StudentContainer student = new StudentContainer();
			student.matriculationNumber = Integer.parseInt(csvRecord.get(COLUMN_MATRIC_NO).trim());
			student.firstName = csvRecord.get(COLUMN_FIRST_NAME).trim();
			student.surname = csvRecord.get(COLUMN_NAME).trim();
			student.email = csvRecord.get(COLUMN_UNI_MAIL).trim();
			int index = student.email.indexOf("@");
			if (-1 == index) {
				throw new IOException("Could not parse email: " + student.email);
			} else {
				student.id = student.email.substring(0, index);
			}
			this.students.add(student);
		}
	}

}