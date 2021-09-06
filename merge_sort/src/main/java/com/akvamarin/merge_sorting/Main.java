package com.akvamarin.merge_sorting;

import com.akvamarin.merge_sorting.user_input.ParseUserInputImpl;

public class Main {
    public static void main(String[] args) {
        ParseUserInputImpl parseUserInputImpl = new ParseUserInputImpl();
        parseUserInputImpl.parseInput(args);
    }
}
