package com.akvamarin.merge_sorting.validators;

import com.akvamarin.merge_sorting.options.params_input.DataType;
import com.akvamarin.merge_sorting.options.params_input.TypeSort;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class InputValidatorImpl implements InputValidator{
    private static final int REQUIRED_NUMBER_OF_ARGS = 3;

    public boolean checkCountArgs(String[] args){
        return args.length < REQUIRED_NUMBER_OF_ARGS;
    }

    public static boolean isExistFile(File file){
        return file.exists() && file.isFile();
    }

    public static List<File> filterNotFoundFiles(List<File> files){
        return files.stream()
                .filter(InputValidatorImpl::isExistFile).
                collect(Collectors.toList());
    }

    public boolean checkDataType(DataType dataType){
        return dataType != DataType.NOT_INIT;

    }

    public boolean checkCountFile(){
        return true;
    }

    public boolean isInteger(String str){
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
