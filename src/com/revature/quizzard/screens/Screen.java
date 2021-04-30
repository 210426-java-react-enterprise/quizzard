package com.revature.quizzard.screens;

import com.revature.quizzard.IO.ConsoleReader;

public abstract class Screen{
    protected static ConsoleReader consoleReader;

    public static void setReader(ConsoleReader cr){
        consoleReader = cr;
    }
}
