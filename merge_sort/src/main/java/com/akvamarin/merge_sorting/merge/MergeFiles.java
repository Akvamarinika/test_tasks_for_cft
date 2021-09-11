package com.akvamarin.merge_sorting.merge;
import com.akvamarin.merge_sorting.exceptions.IncorrectlySortedFileException;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.Optional;

public class MergeFiles<T extends Comparable<T>> {
    ReadersQueue<T> readersQueue;

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
                       //System.out.println(minMaxElement);
                       outputBufferedWriter.write(minMaxElement.toString() + System.lineSeparator()); //int
                       readersQueue.setPreviousLineInFile(minMaxElement);
                   }
                } catch (IncorrectlySortedFileException e) {
                    System.out.println(e.getMessage() + e.getLine());
                    //continue;
                }
                // System.out.println(minLine);


              // String nextLine = readersQueue.next();

              /*  if (nextLine != null && minLine > Integer.parseInt(nextLine)) {
                    linesReadersQueue.offer(new AbstractMap.SimpleImmutableEntry<>(Integer.parseInt(nextLine), bufferedReader));
                } else {
                    bufferedReader.close();
                }*/
            }
           // outputBufferedWriter.close();
        } catch(IOException ex){
            ex.printStackTrace();
        }
    }




  /* private <T>  Queue<Map.Entry<T,BufferedReader>> getInitQueue() throws IOException {
        Comparator sortComparator = options.getSortComparator();
        List<File> inputFiles = options.getInputFiles();
        Queue<Map.Entry<T,BufferedReader>> linesReadersQueue = new PriorityQueue<>(inputFiles.size(), sortComparator);

        for (File file : inputFiles){
            BufferedReader buffReader = new BufferedReader(new FileReader(file));
                String firstLine = buffReader.readLine();

                if (firstLine != null) {
                    linesReadersQueue.offer(new AbstractMap.SimpleImmutableEntry<>(Integer.parseInt(firstLine), buffReader));  //int
                } else {
                    buffReader.close();
                }
        }
        return linesReadersQueue;
    }

    private int addQueue(String string){
        if (options.getDataType() == DataType.INTEGER && DataValidator.isInteger(string)){
            return Integer.parseInt(string);
        }

    }*/
}
