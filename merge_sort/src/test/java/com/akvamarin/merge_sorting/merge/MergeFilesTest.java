package com.akvamarin.merge_sorting.merge;
import com.akvamarin.merge_sorting.exceptions.EmptyLineException;
import com.akvamarin.merge_sorting.options.Options;
import com.akvamarin.merge_sorting.options.params_app.DataType;
import com.akvamarin.merge_sorting.options.params_app.TypeSort;
import com.akvamarin.merge_sorting.validators.DataValidator;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;

public class MergeFilesTest {
    private Options options;
    private List<File> files;
    File file1;
    File file2;
    File file3;
    File file4;
    File file5;
    File outFile;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setUp() {
        try {
            files = new ArrayList<>();
            file1 = folder.newFile( "test1.txt" );
            file2 = folder.newFile( "test2.txt" );
            file3 = folder.newFile( "test3.txt" );
            file4 = folder.newFile( "test4.txt" );
            file5 = folder.newFile( "test5.txt" );
            outFile = folder.newFile("out-test.txt");
        }
        catch( IOException ioe ) {
            System.err.println("error creating temporary test file!");
        }
    }

    @Test
    public void whenSortNumbersDESCThenResultMaxToMin() throws IOException {
        try {
            Files.write(file1.toPath(), Arrays.asList("9", "4", "-1"));
            Files.write(file2.toPath(), Arrays.asList("27", "8", "0", "-5", "kkk"));
            Files.write(file3.toPath(), Arrays.asList("3", "2", "1"));
        }
        catch(IOException ioe ) {
            System.err.println("error creating temporary test file!");
        }

        files.add(file1);
        files.add(file2);
        files.add(file3);

        options = new Options(files, DataType.INTEGER , TypeSort.DESC, outFile);
        ReadersQueue<Integer> readersQueue = getIntegerReadersQueue(options);
        MergeFiles<Integer> mergeFiles = new MergeFiles<>(readersQueue);
        mergeFiles.mergeSortedFiles(outFile);

        File expected = new File("./src/main/resources/expected/numbersSortDESC.txt");
        assertEquals(FileUtils.readLines(expected, "UTF-8"), FileUtils.readLines(outFile, "UTF-8"));
        System.out.println();
    }

    @Test
    public void whenSortNotCorrectNumbersDESCThenResultMaxToMin() throws IOException {
        try {
            Files.write(file1.toPath(), Arrays.asList("1", "4", "9"));
            Files.write(file2.toPath(), Arrays.asList("0", "1", "8", "27"));
            Files.write(file3.toPath(), Arrays.asList("1", "2", "3"));
        }
        catch(IOException ioe ) {
            System.err.println("error creating temporary test file!");
        }

        files.add(file1);
        files.add(file2);
        files.add(file3);

        options = new Options(files, DataType.INTEGER , TypeSort.DESC, outFile);
        ReadersQueue<Integer> readersQueue = getIntegerReadersQueue(options);
        MergeFiles<Integer> mergeFiles = new MergeFiles<>(readersQueue);
        mergeFiles.mergeSortedFiles(outFile);

        File expected = new File("./src/main/resources/expected/numbersSortErrorDESC.txt");
        assertEquals(FileUtils.readLines(expected, "UTF-8"), FileUtils.readLines(outFile, "UTF-8"));
    }

    @Test
    public void whenSortDESCStringThenResultMaxToMin() throws IOException {
        try {
            Files.write(file1.toPath(), Arrays.asList("www", "horse1*78"));
            Files.write(file2.toPath(), Arrays.asList("рус", "poll", "java", "eng"));
            Files.write(file3.toPath(), Arrays.asList("strawberry", "scroll", "apple", "***46"));
        }
        catch(IOException ioe ) {
            System.err.println("error creating temporary test file!");
        }

        files.add(file1);
        files.add(file2);
        files.add(file3);

        options = new Options(files, DataType.STRING , TypeSort.DESC, outFile);
        ReadersQueue<String> readersQueue = getStringReadersQueue(options);
        MergeFiles<String> mergeFiles = new MergeFiles<>(readersQueue);
        mergeFiles.mergeSortedFiles(outFile);

        File expected = new File("./src/main/resources/expected/stringDESC.txt");
        assertEquals(FileUtils.readLines(expected, "UTF-8"), FileUtils.readLines(outFile, "UTF-8"));
    }

    @Test
    public void whenSortStringASCThenResultMinToMax() throws IOException {
        try {
            Files.write(file1.toPath(), Arrays.asList("amusing", "farm", "harbor", "iron", "mixed"));
            Files.write(file2.toPath(), Arrays.asList("advise", "obtain", "perfect", "refuse", "roomy", "sort", "telephone", "thinkable", "waiting"));
            Files.write(file3.toPath(), Arrays.asList("advise", "advise", "ahead", "competition", "legal", "stream", "waiting"));
        }
        catch(IOException ioe ) {
            System.err.println("error creating temporary test file!");
        }

        files.add(file1);
        files.add(file2);
        files.add(file3);

        options = new Options(files, DataType.STRING , TypeSort.ASC, outFile);
        ReadersQueue<String> readersQueue = getStringReadersQueue(options);
        MergeFiles<String> mergeFiles = new MergeFiles<>(readersQueue);
        mergeFiles.mergeSortedFiles(outFile);

        File expected = new File("./src/main/resources/expected/stringASC.txt");
        assertEquals(FileUtils.readLines(expected, "UTF-8"), FileUtils.readLines(outFile, "UTF-8"));
    }

    @Test
    public void whenSortNoCorrectStringASCThenResultMinToMax() throws IOException {
        try {
            Files.write(file1.toPath(), Arrays.asList("amusing", "12hjkh", "harbor", "iron", "****"));
            Files.write(file2.toPath(), Arrays.asList("advise", "obtain", "perfect", "@@@@", "roomy", "abc", "telephone", "thinkable", "waiting"));
            Files.write(file3.toPath(), Arrays.asList("advise", "advise", "128", "competition", "legal", "stream", "$$$"));
        }
        catch(IOException ioe ) {
            System.err.println("error creating temporary test file!");
        }

        files.add(file1);
        files.add(file2);
        files.add(file3);

        options = new Options(files, DataType.STRING , TypeSort.ASC, outFile);
        ReadersQueue<String> readersQueue = getStringReadersQueue(options);
        MergeFiles<String> mergeFiles = new MergeFiles<>(readersQueue);
        mergeFiles.mergeSortedFiles(outFile);

        File expected = new File("./src/main/resources/expected/stringSortErrorASC.txt");
        assertEquals(FileUtils.readLines(expected, "UTF-8"), FileUtils.readLines(outFile, "UTF-8"));
    }

    @Test
    public void whenSortNoCorrectDESCStringThenResultMaxToMin() throws IOException {
        try {
            Files.write(file1.toPath(), Arrays.asList("www", "мир!", "horse1*78"));
            Files.write(file2.toPath(), Arrays.asList("cat","рус", "poll", "java", "eng", "привет,"));
            Files.write(file3.toPath(), Arrays.asList("123cat", "strawberry", "scroll", "apple", "***46"));
        }
        catch(IOException ioe ) {
            System.err.println("error creating temporary test file!");
        }

        files.add(file1);
        files.add(file2);
        files.add(file3);

        options = new Options(files, DataType.STRING , TypeSort.DESC, outFile);
        ReadersQueue<String> readersQueue = getStringReadersQueue(options);
        MergeFiles<String> mergeFiles = new MergeFiles<>(readersQueue);
        mergeFiles.mergeSortedFiles(outFile);

        File expected = new File("./src/main/resources/expected/stringSortErrorDESC.txt");
        assertEquals(FileUtils.readLines(expected, "UTF-8"), FileUtils.readLines(outFile, "UTF-8"));
    }

    @Test
    public void whenSortNumbersAndHaveStringsDESCThenResultMaxToMin() throws IOException {
        try {
            Files.write(file1.toPath(), Arrays.asList(" 9", "4", "bfnfc", "$$$", "@@@", "-1"));
            Files.write(file2.toPath(), Arrays.asList("500abbbbbss", "27", "8", "$$$", "0", " -5", "4545yyy"));
            Files.write(file3.toPath(), Arrays.asList("3", "2", "1", "ghjj7"));
        }
        catch(IOException ioe ) {
            System.err.println("error creating temporary test file!");
        }

        files.add(file1);
        files.add(file2);
        files.add(file3);

        options = new Options(files, DataType.INTEGER , TypeSort.DESC, outFile);
        ReadersQueue<Integer> readersQueue = getIntegerReadersQueue(options);
        MergeFiles<Integer> mergeFiles = new MergeFiles<>(readersQueue);
        mergeFiles.mergeSortedFiles(outFile);

        File expected = new File("./src/main/resources/expected/numbersSortDESC.txt");
        assertEquals(FileUtils.readLines(expected, "UTF-8"), FileUtils.readLines(outFile, "UTF-8"));
        System.out.println();
    }

    @Test
    public void whenSortNumbersASCThenResultMinToMax() throws IOException {
        try {
            Files.write(file1.toPath(), Arrays.asList("1", "4", "9"));
            Files.write(file2.toPath(), Arrays.asList("-10","0", "1", "8", "27"));
            Files.write(file3.toPath(), Arrays.asList("1", "2", "3"));
        }
        catch(IOException ioe ) {
            System.err.println("error creating temporary test file!");
        }

        files.add(file1);
        files.add(file2);
        files.add(file3);

        options = new Options(files, DataType.INTEGER , TypeSort.ASC, outFile);
        ReadersQueue<Integer> readersQueue = getIntegerReadersQueue(options);
        MergeFiles<Integer> mergeFiles = new MergeFiles<>(readersQueue);
        mergeFiles.mergeSortedFiles(outFile);

        File expected = new File("./src/main/resources/expected/numbersSortASC.txt");
        assertEquals(FileUtils.readLines(expected, "UTF-8"), FileUtils.readLines(outFile, "UTF-8"));
    }

    @Test
    public void whenSortNotCorrectNumbersASCThenResultMinToMax() throws IOException {
        try {
            Files.write(file1.toPath(), Arrays.asList("1", "4", "9", "-50", "-100"));
            Files.write(file2.toPath(), Arrays.asList("-10","0", "-1", "-2", "1", "8", "27"));
            Files.write(file3.toPath(), Arrays.asList("100", "2", "3", "115", "70"));
        }
        catch(IOException ioe ) {
            System.err.println("error creating temporary test file!");
        }

        files.add(file1);
        files.add(file2);
        files.add(file3);

        options = new Options(files, DataType.INTEGER , TypeSort.ASC, outFile);
        ReadersQueue<Integer> readersQueue = getIntegerReadersQueue(options);
        MergeFiles<Integer> mergeFiles = new MergeFiles<>(readersQueue);
        mergeFiles.mergeSortedFiles(outFile);

        File expected = new File("./src/main/resources/expected/numbersSortErrorASC.txt");
        assertEquals(FileUtils.readLines(expected, "UTF-8"), FileUtils.readLines(outFile, "UTF-8"));
    }

    @Test
    public void whenSortNumbersASCWithStringsThenResultMinToMax() throws IOException {
        try {
            Files.write(file1.toPath(), Arrays.asList("1", "4", "9", "string", "  "));
            Files.write(file2.toPath(), Arrays.asList("7.8", "R", "-10","0", "1.2", "  ", " 1", " 8", " 27"));
            Files.write(file3.toPath(), Arrays.asList("100", "ggg", "33.3", "115", "*****"));
        }
        catch(IOException ioe ) {
            System.err.println("error creating temporary test file!");
        }

        files.add(file1);
        files.add(file2);
        files.add(file3);

        options = new Options(files, DataType.INTEGER , TypeSort.ASC, outFile);
        ReadersQueue<Integer> readersQueue = getIntegerReadersQueue(options);
        MergeFiles<Integer> mergeFiles = new MergeFiles<>(readersQueue);
        mergeFiles.mergeSortedFiles(outFile);

        File expected = new File("./src/main/resources/expected/numbersSortASCWithStrings.txt");
        assertEquals(FileUtils.readLines(expected, "UTF-8"), FileUtils.readLines(outFile, "UTF-8"));
    }

    @Test
    public void whenSortNoCorrectStringASCWithEmptyLineThenResultMinToMax() throws IOException {
        try {
            Files.write(file1.toPath(), Arrays.asList("  amusing  ", " ", "harbor", "iron", " "));
            Files.write(file2.toPath(), Arrays.asList("  ", "  ", "  ", "  advise", "obtain", "perfect", " ", "roomy", " ", "telephone", "thinkable", "waiting", " "));
            Files.write(file3.toPath(), Arrays.asList("  advise", "advise", " ", "  competition", "legal", "stream", " "));
        }
        catch(IOException ioe ) {
            System.err.println("error creating temporary test file!");
        }

        files.add(file1);
        files.add(file2);
        files.add(file3);

        options = new Options(files, DataType.STRING , TypeSort.ASC, outFile);
        ReadersQueue<String> readersQueue = getStringReadersQueue(options);
        MergeFiles<String> mergeFiles = new MergeFiles<>(readersQueue);
        mergeFiles.mergeSortedFiles(outFile);

        File expected = new File("./src/main/resources/expected/stringASCWithEmptyLine.txt");
        assertEquals(FileUtils.readLines(expected, "UTF-8"), FileUtils.readLines(outFile, "UTF-8"));
    }




    private ReadersQueue<String> getStringReadersQueue(Options options){
        return new ReadersQueue<>(options) {
            @Override
            protected String convert(String string) throws EmptyLineException {
                if (!string.isBlank()) {
                    return string.trim();
                }
                throw new EmptyLineException("Пустая строка в файле будет пропущена.");
            }
        };
    }

    private ReadersQueue<Integer> getIntegerReadersQueue(Options options){
        return new ReadersQueue<>(options) {
            @Override
            protected Integer convert(String string) throws NumberFormatException {
                if (DataValidator.isInteger(string.trim())) {
                    return Integer.valueOf(string.trim());
                }
                throw new NumberFormatException(String.format("Не удалось преобразовать строку %s в число, данная строка будет пропущена", string));
            }
        };
    }
}