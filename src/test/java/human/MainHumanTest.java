package human;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.dice_research.paul.parser.Resources;
import org.dice_research.paul_parser.Main;

public class MainHumanTest {

	public static void main(String[] args) throws InterruptedException {
		
		testHumanWrongType();
		TimeUnit.MILLISECONDS.sleep(10);

		testHumanNoOutFile();
		TimeUnit.MILLISECONDS.sleep(10);

		testHumanNoArgs();
	}

	public static void testHumanNoArgs() {
		System.out.println();
		System.out.println(" --- NO ARGS ---");
		System.out.println();
		List<String> argsList = new LinkedList<String>();
		String[] argsArray = new String[argsList.size()];
		Main.main(argsList.toArray(argsArray));
		System.out.println();
		System.out.println();
	}

	public static void testHumanNoOutFile() {
		System.out.println();
		System.out.println(" --- NO OUT FILE ---");
		System.out.println();
		File testInputFile = Resources.getResource(Resources.FILE_ENGLISH);
		List<String> argsList = new LinkedList<String>();
		argsList.add("-i");
		argsList.add(testInputFile.getAbsolutePath());
		String[] argsArray = new String[argsList.size()];
		Main.main(argsList.toArray(argsArray));
		System.out.println();
		System.out.println();
	}

	public static void testHumanWrongType() {
		System.out.println();
		System.out.println(" --- WRONG TYPE ---");
		System.out.println();
		File testInputFile = Resources.getResource(Resources.FILE_ENGLISH);
		List<String> argsList = new LinkedList<String>();
		argsList.add("-i");
		argsList.add(testInputFile.getAbsolutePath());
		argsList.add("-t");
		argsList.add("X");
		String[] argsArray = new String[argsList.size()];
		Main.main(argsList.toArray(argsArray));
		System.out.println();
		System.out.println();
	}

}