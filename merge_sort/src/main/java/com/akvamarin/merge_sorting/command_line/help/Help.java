package com.akvamarin.merge_sorting.command_line.help;

public class Help {
    public static void displayHelp(){
        System.out.printf("%nСПРАВКА: %n" +
        "java -jar <путь к merge_sort.jar> <тип данных> <имя выходного файла> <имя входного файла> <режим сортировки> %n" +
        "Параметры программы задаются при запуске через аргументы командной строки, по порядку:%n" +
        "1. режим сортировки (-a или -d), необязательный, по умолчанию сортируем по возрастанию;%n" +
        "2. тип данных (-s или -i), обязательный;%n" +
        "3. имя выходного файла, обязательное;%n" +
        "4. остальные параметры – имена входных файлов, не менее одного.%n" +
        "%nПримеры запуска из командной строки для Windows:%n" +
        "java -jar merge_sort.jar -i -a out.txt in1.txt in2.txt (для целых чисел по возрастанию)%n" +
        "java -jar merge_sort.jar -s out.txt in1.txt in2.txt in3.txt (для строк по возрастанию)%n" +
        "java -jar merge_sort.jar -d -s out.txt in1.txt in2.txt (для строк по убыванию)%n");
    }

    public static void displayCommandHelp(){
        System.out.println("\nВызов справки: -h или --help\n");
    }
}
