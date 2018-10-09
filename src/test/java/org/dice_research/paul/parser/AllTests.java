package org.dice_research.paul.parser;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({

		CsvWriterTest.class,

		MainTest.class,

		PaulParserTest.class })

public class AllTests {
}