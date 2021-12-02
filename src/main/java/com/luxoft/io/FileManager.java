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
        File fileFrom = new File(from);
        File fileTo = new File(to);
        fileTo.createNewFile();
        try (InputStream inputStream = new FileInputStream(fileFrom);
             OutputStream outputStream = new FileOutputStream(fileTo)) {
            int length = (int) fileFrom.length();
            byte[] content = new byte[length];
            inputStream.read(content);
            outputStream.write(content);
        }
    }


    public static void move(String from, String to) {
        new File(from).renameTo(new File(to));
        new File(from).delete();
    }
}
