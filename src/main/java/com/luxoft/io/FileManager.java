package com.luxoft.io;

import java.io.*;

public class FileManager {
    public static int countFiles(String path) {
        int counter = 0;
        File directoryPath = new File(path);
        File[] files = directoryPath.listFiles();
        for (File file : files) {
            if (!file.isDirectory()) {
                counter++;
            } else {
                counter += countFiles(file.getPath());
            }
        }
        return counter;
    }

    public static int countDirs(String path) throws IOException {
        File rootDir = new File(path);
        int result = 0;
        if (rootDir.isDirectory()) {
            File[] files = rootDir.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    result += 1 + FileManager.countDirs(file.getCanonicalPath());
                }
            }
        }

        return result;
    }

    public static void copy(String from, String to) throws IOException {
        String content = FileAnalyser.Reader(from);
        move(from, to);
        File fileFrom = new File(from);
        fileFrom.createNewFile();
        try (FileWriter fileWriter = new FileWriter(fileFrom)) {
            fileWriter.write(content);
        }
    }


    public static void move(String from, String to) {
        new File(from).renameTo(new File(to));
        new File(from).delete();
    }
}
