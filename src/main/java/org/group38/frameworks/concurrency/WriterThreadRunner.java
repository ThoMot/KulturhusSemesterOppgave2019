package org.group38.frameworks.concurrency;

import org.group38.kulturhus.model.SaveLoad.CsvBase;

import java.util.ArrayList;

public class WriterThreadRunner {

    public static void WriterThreadRunner(CsvBase object) throws InterruptedException {
        Thread writerThread = new Thread(new WriterThread(object));
        writerThread.start();
        writerThread.join();
    }

    public static <T> void WriterThreadRunner(ArrayList<T> list) throws InterruptedException {
        Thread writerThread = new Thread(new WriterThread(list));
        writerThread.start();
        writerThread.join();
    }
}
