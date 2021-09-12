package com.akvamarin.merge_sorting.command_line;
import com.akvamarin.merge_sorting.options.Options;
import com.akvamarin.merge_sorting.options.params_app.DataType;
import com.akvamarin.merge_sorting.options.params_app.TypeSort;
import com.akvamarin.merge_sorting.command_line.help.Help;
import com.akvamarin.merge_sorting.validators.CommandLineValidator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class ParseCommandLineImpl implements ParseCommandLine {
    private static final int REQUIRED_NUMBER_OF_ARGS = 3;

    @Override
    public Options parse(String[] inputArgs){
        if (inputArgs.length < 1){
            Help.displayCommandHelp();
            System.exit(0);
        }

        if (inputArgs[0].equals("-h") || inputArgs[0].equals("--help")){
            Help.displayHelp();
            System.exit(0);
        }

        try {
            if ((inputArgs.length < REQUIRED_NUMBER_OF_ARGS)){
                Help.displayCommandHelp();
                throw new IllegalArgumentException("Ошибка: введены не все обязательные параметры! Пожалуйста, повторите ввод или воспользуйтесь справкой(-h или --help).");
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        Options options = new Options();

        for (String inputArg : inputArgs) {
            if (inputArg.equals("-a") || inputArg.equals("--asc")) {
                options.setTypeSort(TypeSort.ASC);
            } else if (inputArg.equals("-d") || inputArg.equals("--desc")) {
                options.setTypeSort(TypeSort.DESC);
            } else if (inputArg.equals("-s") || inputArg.equals("--string")) {
                options.setDataType(DataType.STRING);
            } else if (inputArg.equals("-i") || inputArg.equals("--integer")) {
                options.setDataType(DataType.INTEGER);
            } else if (inputArg.equals("-h") || inputArg.equals("--help")) {
                Help.displayHelp();
                System.exit(0);
            } else {

                if (options.getOutput() == null) {
                    options.setOutput(new File(inputArg));
                } else {
                    options.addInputFile(new File(inputArg));
                }

            }
        }

        try {
            checkingRequiredOptions(options);
        } catch (FileNotFoundException | IllegalArgumentException ex) {
            System.err.println(ex.getMessage());
            System.exit(0);

        }
        return options;

    }

    private void checkingRequiredOptions(Options options) throws FileNotFoundException, IllegalArgumentException {
        List<File> filterInputFiles = CommandLineValidator.filterNotFoundFiles(options.getInputFiles());
        options.setInputFiles(filterInputFiles);

        if(options.getOutput() == null){
            throw new IllegalArgumentException("Ошибка: не задано имя выходного файла. Пожалуйста, повторите ввод или воспользуйтесь справкой(-h или --help).");
        }

        if(filterInputFiles.size() < 1){
            throw new  FileNotFoundException("Ошибка: не найдено ни одного входного файла. Пожалуйста, повторите ввод или воспользуйтесь справкой(-h или --help).");
        }

        CommandLineValidator.checkDataType(options.getDataType());
    }

}
