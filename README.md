# Инструкция по запуску merge_sort.jar:

1. Скачать исполняемый файл merge_sort.jar 
test_tasks_for_cft/merge_sort/out

2. Запуск приложения:
java -jar <путь к merge_sort.jar> <тип данных> <имя выходного файла> <имя входного файла> <режим сортировки>

# Параметры программы:
(задаются при запуске через аргументы командной строки, по порядку):
1. режим сортировки: -a (--asc) или -d (--desc), необязательный, по умолчанию сортируем по возрастанию;
2. тип данных: -s (--string) или -i (--integer), обязательный;
3. имя выходного файла, обязательное;
4. остальные параметры - имена входных файлов, не менее одного.

# Примеры запуска из командной строки для Windows:
java -jar merge_sort.jar -i -a out.txt in1.txt in2.txt (для целых чисел по возрастанию)
java -jar merge_sort.jar -s out.txt in1.txt in2.txt in3.txt (для строк по возрастанию)
java -jar merge_sort.jar -d -s out.txt in1.txt in2.txt (для строк по убыванию)
java -jar merge_sort.jar --desc --string out.txt in1.txt in2.txt (для строк по убыванию)
java -jar merge_sort.jar -h (вызов справки)
java -jar merge_sort.jar --help (вызов справки)

# Особенности реализации:
* Возможны потери данных, если на вход подаются не отсортированные(или частично отсортированные) входные файлы.
* Пустые строки в файлах пропускаются и не учитываются при сортировке слиянием.
* При обработке чисел, если строку невозможно преобразовать к числу, она будет пропущена.
* Все виды ошибок выводятся в консоль.
* Версия Java 11 (corretto-11) 11.0.11
