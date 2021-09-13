package com.akvamarin.merge_sorting.command_line;
import com.akvamarin.merge_sorting.options.Options;

public interface ParseCommandLine {
    Options parse(String[] args);
}
