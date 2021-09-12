package com.akvamarin.merge_sorting.command_line;

import com.akvamarin.merge_sorting.options.Options;
import com.akvamarin.merge_sorting.options.params_app.DataType;
import com.akvamarin.merge_sorting.options.params_app.TypeSort;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ParseCommandLineImplTest {
    ParseCommandLine parseCommandLine;

    @Before
    public void setUp() {
        parseCommandLine = new ParseCommandLineImpl();
    }

    @Test
    public void whenNoParametersAreEnteredThenGetHelpAndExit() {
     /*   String[] args = {};
        parseCommandLine.parse(args);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertEquals("\nВызов справки: -h или --help\n", outContent.toString());*/
    }

    @Test
    public void whenAllCorrectParametersThenMethodParseReturnedRightOptions() {
        String[] args = {"-i", "-a", "out.txt", "in1.txt", "in2.txt", "in3.txt"};
        Options actualOptions = parseCommandLine.parse(args);
        List<File> expectedFiles = Arrays.asList(new File("in1.txt"), new File("in2.txt"), new File("in3.txt"));
        Options expectedOptions = new Options(expectedFiles, DataType.INTEGER, TypeSort.ASC, new File("out.txt"));
        assertEquals(expectedOptions, actualOptions);
    }

    @Test
    public void whenAllCorrectParamWithFullNameThenMethodParseReturnedRightOptions() {
        String[] args = {"--integer", "--asc", "out.txt", "in1.txt", "in2.txt", "in3.txt"};
        Options actualOptions = parseCommandLine.parse(args);
        List<File> expectedFiles = Arrays.asList(new File("in1.txt"), new File("in2.txt"), new File("in3.txt"));
        Options expectedOptions = new Options(expectedFiles, DataType.INTEGER, TypeSort.ASC, new File("out.txt"));
        assertEquals(expectedOptions, actualOptions);
    }

    @Test
    public void whenFilesNotFoundThenFileContinue() {
        String[] args = {"hvghvhg", "-a",  "in1.txt", "-i", "in2", "in3.txt", "--abc"};
        Options actualOptions = parseCommandLine.parse(args);
        List<File> expectedFiles = Arrays.asList(new File("in1.txt"), new File("in3.txt"));
        Options expectedOptions = new Options(expectedFiles, DataType.INTEGER, TypeSort.ASC, new File("hvghvhg"));
        assertEquals(expectedOptions, actualOptions);
    }

    @Test
    public void whenInputFilesNotSetThenExit(){
    /*    String[] args = {"-i", "-a", "111.txt"};
        Options actualOptions = parseCommandLine.parse(args);
        Options expectedOptions = new Options(new ArrayList<>(), DataType.INTEGER, TypeSort.ASC, new File("111.txt"));
        assertEquals(expectedOptions, actualOptions);
*/
    }

    @Test
    public void whenNotSetTypeSortThenReturnOptions() {
        String[] args = {"out.txt", "-s", "in1.txt", "in2.txt", "in3.txt"};
        Options actualOptions = parseCommandLine.parse(args);
        List<File> expectedFiles = Arrays.asList(new File("in1.txt"), new File("in2.txt"), new File("in3.txt"));
        Options expectedOptions = new Options(expectedFiles, DataType.STRING, TypeSort.ASC, new File("out.txt"));
        assertEquals(expectedOptions, actualOptions);
    }

    @Test
    public void whenNotSetTypeDataThenExit() {
      //  String[] args = {"out.txt", "in1.txt", "in2.txt", "in3.txt"};
      //  Options actualOptions = parseCommandLine.parse(args);
       // System.out.println(actualOptions);
    }

    @Test
    public void whenLessThan3ParametersAreGivenThenExit() {
     // String[] args = {"--string", "--desc", "--desc" , "--desc"};
     // parseCommandLine.parse(args);
    }
}