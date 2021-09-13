package com.akvamarin.merge_sorting.merge;
import com.akvamarin.merge_sorting.exceptions.IncorrectlySortedFileException;
import java.io.*;
import java.util.Optional;

public class MergeFiles<T extends Comparable<T>> {
    private final ReadersQueue<T> readersQueue;

    public MergeFiles(ReadersQueue<T> readersQueue){
        this.readersQueue = readersQueue;
    }

    public void mergeSortedFiles(File output){
        try (BufferedWriter outputBufferedWriter = new BufferedWriter(new FileWriter(output))) {

            while (!readersQueue.isEmpty()) {
                try {

                   Optional<T> optionalElement = readersQueue.getMinMaxElement();
                   if (optionalElement.isPresent()){
                       T minMaxElement = optionalElement.get();
                       outputBufferedWriter.write(minMaxElement.toString() + System.lineSeparator());
                       readersQueue.setPreviousLineInFile(minMaxElement);
                   }

                } catch (IncorrectlySortedFileException e) {
                    System.err.println(String.format("Файл %s: %s %s", e.getFileName(), e.getMessage(), e.getLine()));
                }
            }

        } catch(IOException ex){
            System.err.println("Не удалась запись в файл " + output.getName());
        }

    }
}
