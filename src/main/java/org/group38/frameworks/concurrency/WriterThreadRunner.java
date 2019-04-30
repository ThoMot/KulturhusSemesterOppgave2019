package org.group38.frameworks.concurrency;

import javafx.collections.ObservableList;

import java.util.ArrayList;

public class WriterThreadRunner {

    public static void WriterThreadRunner(Object object, String filename) throws InterruptedException {
        Thread writerThread = new Thread(new WriterThread(object, filename));
        writerThread.start();
        writerThread.join();
    }

    public static <T> void WriterThreadRunner(ObservableList<T> list, String filename) throws InterruptedException {
        Thread writerThread = new Thread(new WriterThread(list, filename));
        writerThread.start();
        writerThread.join();
    }
}
