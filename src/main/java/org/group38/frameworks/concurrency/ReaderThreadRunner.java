package org.group38.frameworks.concurrency;

import javafx.collections.ObservableList;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ReaderThreadRunner {

    public static <T> ArrayList<T> startReader(String path) throws ExecutionException, InterruptedException {
        File file = new File(path);
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }

        ExecutorService service = Executors.newFixedThreadPool(1);
        ArrayList<T> result = null;
        Future<ArrayList<T>> returnedList = service.submit(new ReaderThread(path));

        result = returnedList.get();
        service.shutdown();
        return result;
    }
}
