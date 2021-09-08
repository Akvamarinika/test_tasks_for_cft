package com.akvamarin.merge_sorting.merge;
import com.akvamarin.merge_sorting.exceptions.IncorrectlySortedFileException;
import com.akvamarin.merge_sorting.options.Options;
import com.akvamarin.merge_sorting.options.comparators.SortASC;
import com.akvamarin.merge_sorting.options.comparators.SortDESC;
import com.akvamarin.merge_sorting.options.params_app.TypeSort;
import com.akvamarin.merge_sorting.validators.DataValidator;
import com.akvamarin.merge_sorting.validators.TypeValidator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public abstract class ReadersQueue<K extends Comparable<K>> implements TypeValidator {
    private final Queue<Map.Entry<K, BufferedReader>> linesReadersQueue;
    private final Options options;

    public ReadersQueue(Options options) throws IOException {
        this.options = options;

        Comparator<Map.Entry<K, BufferedReader>> sortComparator =
                (this.options.getTypeSort() == TypeSort.ASC) ? new SortASC<>() : new SortDESC<>();

        this.linesReadersQueue = new PriorityQueue<>(this.options.getInputFiles().size(), sortComparator);
        this.getInitQueue();
    }

    private void getInitQueue() throws IOException {
        linesReadersQueue.clear();
        List<File> inputFiles = options.getInputFiles();
        List<File> filterInputFiles = DataValidator.filterNotFoundFiles(inputFiles);

        for (File file : filterInputFiles){
            BufferedReader buffReader = new BufferedReader(new FileReader(file));
            K firstLine = convert(buffReader.readLine());
            this.add(buffReader, firstLine);

          /*  if (firstLine != null) {

                linesReadersQueue.offer(new AbstractMap.SimpleImmutableEntry<K, BufferedReader>(Integer.parseInt(firstLine), buffReader));  //int
            } else {
                buffReader.close();
            }*/
        }
    }

    private void add(BufferedReader buffReader, K line) throws IOException {
        if (line != null) {




           // if (options.getDataType() == DataType.INTEGER && DataValidator.isInteger(line)) {
                //int number = Integer.parseInt(line);

                linesReadersQueue.offer(new AbstractMap.SimpleImmutableEntry<>(line, buffReader));
            //}
        } else {
            buffReader.close();
        }

    }

   /* public String next() throws IOException {
        BufferedReader buffReader = getBufferReader();
        String nextLine = buffReader.readLine();
        this.add(buffReader, nextLine);
        return buffReader.readLine();
    }*/

    public K getMinMaxElement() throws IOException, IncorrectlySortedFileException {
        Map.Entry<K, BufferedReader> elemReader = linesReadersQueue.poll();
        assert elemReader != null;
        BufferedReader buffReader = elemReader.getValue();
        K line = elemReader.getKey();
        K nextLine = convert(buffReader.readLine());
        this.add(buffReader, nextLine);

        if (TypeValidator.isCorrectOrderData(options.getTypeSort(), line, nextLine)){
            return nextLine;
        } else {
            throw new IncorrectlySortedFileException("Нарушен порядок сортировки, данная строка будет пропущена: ", nextLine.toString());
        }

    }


    public K getElement() {
        assert linesReadersQueue.peek() != null;
        return linesReadersQueue.peek().getKey();
    }

    private BufferedReader getBufferReader() {
        assert linesReadersQueue.peek() != null;
        return linesReadersQueue.peek().getValue();
    }

    public boolean isEmpty() {
        return linesReadersQueue.isEmpty();
    }

    protected abstract K convert(String string) throws NumberFormatException;


}

