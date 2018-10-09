package org.dice_research.paul_parser;

import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.Types;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 * Options for command line access.
 * 
 * Options are defined in {@link #getOptions()}.
 * 
 * Types are defined in {@link Types}.
 * 
 * @author Adrian Wilke
 */
public class CommandLineInterface {

	public final static String OPTION_INPUT = "i";
	public final static String OPTION_OUTPUT = "o";
	public final static String OPTION_DEMILITER = "d";
	public final static String OPTION_TYPES = "t";

	public final static String TYPE_ID = "i";
	public final static String TYPE_FIRSTNAME = "f";
	public final static String TYPE_SURNAME = "s";
	public final static String TYPE_MATRICULATION = "m";
	public final static String TYPE_EMAIL = "e";

	public static final String INFO = "Parser for PAUL extended participants list. "
			+ "Find list at: PAUL > My Courses > Term administration > Course Overview > Event > Participants > Extended list.";
	public static final String CMD_LINE_SYNTAX = "java -jar paul-parser.jar";

	public static final Map<String, String> TYPES = new LinkedHashMap<String, String>();
	static {
		TYPES.put(TYPE_ID, "IMT university id");
		TYPES.put(TYPE_FIRSTNAME, "first name");
		TYPES.put(TYPE_SURNAME, "surname");
		TYPES.put(TYPE_MATRICULATION, "matriculation number");
		TYPES.put(TYPE_EMAIL, "email address");
	}

	protected static String getTypesInfo() {
		StringBuilder stringBuilder = new StringBuilder();
		for (String key : TYPES.keySet()) {
			stringBuilder.append(" ");
			stringBuilder.append(key);
			stringBuilder.append("  ");
			stringBuilder.append(TYPES.get(key));
			stringBuilder.append(System.lineSeparator());
		}
		return stringBuilder.toString();
	}

	public Options getOptions() {
		Options options = new Options();
		options.addOption(Option.builder(OPTION_INPUT).longOpt("input").desc("Input text file, mandatory").hasArg()
				.argName("file").required().build());
		options.addOption(Option.builder(OPTION_OUTPUT).longOpt("output")
				.desc("Output CSV file, default: print to terminal").hasArg().argName("file").build());
		options.addOption(Option.builder(OPTION_DEMILITER).longOpt("demiliter")
				.desc("Character between entries, default: ','").hasArg().argName("char").build());
		options.addOption(Option.builder(OPTION_TYPES).longOpt("types").desc("Type of entries to include, default: all")
				.hasArg().argName("chars").build());
		return options;
	}

	public void printHelp(Options options) {
		PrintWriter writer = new PrintWriter(System.out);
		printHelp(getOptions(), writer);
		writer.flush();
		writer.close();
	}

	public void printHelp(Options options, PrintWriter printWriter) {
		HelpFormatter formatter = new HelpFormatter();

		// Comparator ensures first option i
		class OptionComparator implements Comparator<Option>, Serializable {
			private static final long serialVersionUID = 1L;

			public int compare(Option opt1, Option opt2) {
				if (opt1.getOpt().equals("i")) {
					return -1;
				} else if (opt2.getOpt().equals("i")) {
					return 1;
				} else {
					return opt1.getOpt().compareToIgnoreCase(opt2.getOpt());
				}
			}
		}
		formatter.setOptionComparator(new OptionComparator());
		formatter.printHelp(printWriter, formatter.getWidth(), CMD_LINE_SYNTAX, "", options, formatter.getLeftPadding(),
				formatter.getDescPadding(), "types: " + System.lineSeparator() + getTypesInfo() + INFO);
	}
}
