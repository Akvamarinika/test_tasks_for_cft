package com.akvamarin.merge_sorting.merge;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class ReaderWithFile {
   private final File file;
   private final BufferedReader bufferedReader;

    public ReaderWithFile(File file) throws IOException {
        this.file = file;
        bufferedReader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8));
    }

    public File getFile() {
        return file;
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }
}
