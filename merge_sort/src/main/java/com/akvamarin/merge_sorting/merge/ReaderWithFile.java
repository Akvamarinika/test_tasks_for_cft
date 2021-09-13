package com.akvamarin.merge_sorting.merge;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ReaderWithFile {
   private final File file;
   private final BufferedReader bufferedReader;

    public ReaderWithFile(File file) throws FileNotFoundException {
        this.file = file;
        bufferedReader = new BufferedReader(new FileReader(file));
    }

    public File getFile() {
        return file;
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }
}
