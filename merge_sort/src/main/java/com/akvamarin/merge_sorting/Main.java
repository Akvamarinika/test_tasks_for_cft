package com.akvamarin.merge_sorting;
import com.akvamarin.merge_sorting.command_line.ParseCommandLine;
import com.akvamarin.merge_sorting.exceptions.EmptyLineException;
import com.akvamarin.merge_sorting.merge.MergeFiles;
import com.akvamarin.merge_sorting.merge.ReadersQueue;
import com.akvamarin.merge_sorting.options.Options;
import com.akvamarin.merge_sorting.options.params_app.DataType;
import com.akvamarin.merge_sorting.command_line.ParseCommandLineImpl;
import com.akvamarin.merge_sorting.validators.DataValidator;

public class Main {
    public static void main(String[] args){
        ParseCommandLine parseCommandLine = new ParseCommandLineImpl();
        Options options = parseCommandLine.parse(args);

        if (options.getDataType() == DataType.INTEGER){
            ReadersQueue<Integer> readersQueueInt = readersQueueForIntegers(options);
            MergeFiles<Integer> mergeFiles = new MergeFiles<>(readersQueueInt);
            mergeFiles.mergeSortedFiles(options.getOutput());
        } else {
            ReadersQueue<String> readersQueueStr = readersQueueForStrings(options);
            MergeFiles<String> mergeFiles = new MergeFiles<>(readersQueueStr);
            mergeFiles.mergeSortedFiles(options.getOutput());
        }
    }

    private static ReadersQueue<Integer> readersQueueForIntegers(Options options){
        return new ReadersQueue<>(options) {
            @Override
            protected Integer convert(String string, String fileName) throws NumberFormatException {
                if (DataValidator.isInteger(string)) {
                    return Integer.valueOf(string);
                }
                throw new NumberFormatException(String.format("Файл %s: Не удалось преобразовать строку %s в число, данная строка будет пропущена", fileName, string));
            }
        };
    }

    private static ReadersQueue<String> readersQueueForStrings(Options options){
        return new ReadersQueue<>(options) {
            @Override
            protected String convert(String string, String fileName) throws EmptyLineException {
                if (!string.isBlank()) {
                    return string.trim();
                }
                throw new EmptyLineException(String.format("Файл %s: Пустая строка в файле будет пропущена.", fileName));
            }
        };
    }
}
