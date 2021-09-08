package com.akvamarin.merge_sorting.exceptions;

public class IncorrectlySortedFileException extends Exception{
    private final String line;
    public IncorrectlySortedFileException(String message, String line){
        super(message);
        this.line = line;
    }

    public String getLine() {
        return line;
    }
}
