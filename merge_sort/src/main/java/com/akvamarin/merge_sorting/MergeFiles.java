package com.akvamarin.merge_sorting;

import com.akvamarin.merge_sorting.Sort;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MergeFiles {
    private static String answer;
    public static void main(String[] args) throws IOException {
        String typeSort = "-a";
        String dataType = null;
        File outputFile = null;
        List<File> inputFiles = new ArrayList<>();

        for(int param = 1; param < args.length; param++){
            if (args[param].equals("-a") || args[param].equals("-d")){
                typeSort = args[param];
            }
            else if (args[param].equals("-s") || args[param].equals("-i")){
                dataType = args[param];
            }
            else if (args[param].equals("-h") || args[param].equals("--help")){
                displayHelp();
            }
            else {
                if (outputFile == null){
                    outputFile = new File(args[param]);
                }
                else {
                    inputFiles.add(new File(args[param]));
                }
            }
        }

        System.out.println(outputFile);
        System.out.println(inputFiles);
        //while (answer.equals("no")){}
        Sort sort = new Sort();
        sort.mergeSortedFiles(inputFiles, outputFile);

    }

    public static void displayHelp(){
        System.out.println("СПРАВКА: ");
        System.out.println("java -jar <путь к com.akvamarin.merge_sorting.com.akvamarin.merge_sorting.MergeFiles.jar> <тип данных> <выходной файл> <входной файл> <режим сортировки>");
        System.out.println("-h или --help : вызов справки");
        System.out.println("Обязательные параметры:");
        System.out.println("-s или -i : тип данных (-s == строки, -i == целые числа)");
        System.out.println("Необязательные параметры:");
        System.out.println("-a или -d : режим сортировки (-a == по возрастанию, -d == по убыванию) по умолчанию сортировка по возрастанию");
    }

    public static void checkUserInput(){

    }
}
