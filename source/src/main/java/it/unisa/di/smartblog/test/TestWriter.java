package it.unisa.di.smartblog.test;

import java.io.PrintWriter;

public class TestWriter {

    public static void printTest(PrintWriter pw, Object oracle, Object obtained){
        pw.println("\tExpected: "+oracle);
        pw.println("\tObtained: "+obtained);
    }
}
