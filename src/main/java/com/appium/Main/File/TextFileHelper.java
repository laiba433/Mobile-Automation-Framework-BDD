package com.appium.Main.File;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TextFileHelper
{
    public static ArrayList<String> GetArrayListFromFile(String Filename)
    {

        BufferedReader reader;
        ArrayList<String> listOfLines = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(Filename));
            String line = reader.readLine();
            while (line != null) {
                listOfLines.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return listOfLines;
    }

    public static void WriteArrayListInFile(ArrayList<String> list, String Filename)
    {
        FileWriter outFile = null;
        try {
            outFile = new FileWriter("Output.txt",true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedWriter outStream = new BufferedWriter(outFile);
        for (int i = 0; i < list.size(); i++) {
            try {
                outStream.write(list.get(i));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                outStream.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            outStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void WriteEachLineInFile(String line) {
        FileWriter outFile = null;
        try {
            outFile = new FileWriter("Output.txt", true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedWriter outStream = new BufferedWriter(outFile);

        try {
            outStream.write(line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            outStream.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            outStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

