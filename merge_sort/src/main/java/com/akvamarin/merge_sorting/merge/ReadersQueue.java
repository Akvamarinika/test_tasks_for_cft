package com.akvamarin.merge_sorting.merge;
import com.akvamarin.merge_sorting.exceptions.EmptyLineException;
import com.akvamarin.merge_sorting.exceptions.IncorrectlySortedFileException;
import com.akvamarin.merge_sorting.options.Options;
import com.akvamarin.merge_sorting.options.comparators.SortASC;
import com.akvamarin.merge_sorting.options.comparators.SortDESC;
import com.akvamarin.merge_sorting.options.params_app.TypeSort;
import com.akvamarin.merge_sorting.validators.DataValidator;
import org.jetbrains.annotations.Nullable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public abstract class ReadersQueue<K extends Comparable<K>> {
    private final Queue<Map.Entry<K, BufferedReader>> linesReadersQueue;
    private final Options options;
    private K previousLineInFile = null;

    public ReadersQueue(Options options){
        this.options = options;

        Comparator<Map.Entry<K, BufferedReader>> sortComparator =
                (this.options.getTypeSort() == TypeSort.ASC) ? new SortASC<>() : new SortDESC<>();

        this.linesReadersQueue = new PriorityQueue<>(this.options.getInputFiles().size(), sortComparator);

        try {
            this.getInitQueue(options.getInputFiles());
        } catch (IOException e) {
            System.err.println(String.format("Ошибка при чтении файла: %s", e.getMessage()));
        }
    }

    private void getInitQueue(List<File> inputFiles) throws IOException {
        linesReadersQueue.clear();

        for (File file : inputFiles){
            BufferedReader buffReader = new BufferedReader(new FileReader(file));
            addNextElementInQueue(buffReader);
           /* String firstLine = buffReader.readLine();
            if (firstLine != null){
                linesReadersQueue.offer(new AbstractMap.SimpleImmutableEntry<>(convert(firstLine), buffReader));
            }*/
        }
    }

    private void addNextElementInQueue(BufferedReader buffReader) throws IOException{
        String nextLine =  buffReader.readLine();

        try {
            if (nextLine != null) {
                K nextLineConvert = convert(nextLine);
                linesReadersQueue.offer(new AbstractMap.SimpleImmutableEntry<>(nextLineConvert, buffReader));
            }
        } catch (NumberFormatException | EmptyLineException e) {
            System.err.println(e.getMessage());
            addNextElementInQueue(buffReader);
        }
    }

    @Nullable
    public Optional<K> getMinMaxElement() throws IOException, IncorrectlySortedFileException {
        Map.Entry<K, BufferedReader> elemReader = linesReadersQueue.poll();
        BufferedReader buffReader = elemReader.getValue();
        K line = elemReader.getKey();
       // System.out.println(previousLineInFile);
       // System.out.println(line);
        addNextElementInQueue(buffReader);

        if (line != null) {
            if (DataValidator.isCorrectOrderData(options.getTypeSort(),  previousLineInFile, line)){
                return Optional.of(line);
            } else {
                throw new IncorrectlySortedFileException("Нарушен порядок сортировки, данная строка будет пропущена: ", line.toString());
            }

        } else {
            buffReader.close();
        }
        return Optional.empty();
    }

    public boolean isEmpty() {
        return linesReadersQueue.isEmpty();
    }

    protected abstract K convert(String string) throws NumberFormatException, EmptyLineException;

    public void setPreviousLineInFile(K previousLineInFile) {
        this.previousLineInFile = previousLineInFile;
    }
}

