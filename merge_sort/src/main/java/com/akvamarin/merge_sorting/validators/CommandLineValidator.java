package com.akvamarin.merge_sorting.validators;
import com.akvamarin.merge_sorting.options.params_app.DataType;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

public class CommandLineValidator {

    public static boolean isExistFile(File file) throws FileNotFoundException {
        if (file.exists() && file.isFile()){
            return  true;
        }
        throw new FileNotFoundException(String.format("Файл %s для чтения данных не найден, файл будет пропущен.", file.getName()));
    }

    public static List<File> filterNotFoundFiles(List<File> files){
        return files.stream()
                .filter(file -> {
                    try {
                        if (CommandLineValidator.isExistFile(file)) {
                            return true;
                        }
                    }catch (FileNotFoundException e) {
                        System.err.println(e.getMessage());
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    public static boolean checkDataType(DataType dataType) throws IllegalArgumentException {
        if (dataType == DataType.NOT_INIT){
            throw new IllegalArgumentException("Ошибка: не указан обязательный параметр == тип данных. Пожалуйста, повторите ввод или воспользуйтесь справкой(-h или --help).");
        }
        return true;
    }

}
