package com.luxoft.io;

import java.io.*;
import java.util.Objects;
import java.util.StringJoiner;


public class FileAnalyser {
    public static int WordRepetitionCounter(String word, String path) throws IOException {
        int count = 0;
        String str = Reader(path);
        String[] words = str.split("[\s.,]+");
        for (String s : words) {
            if (Objects.equals(s, word)) {
                count++;
            }
        }
        return count;
    }

    public static String SentencesWithWord(String word, String path) throws IOException {
        String str = Reader(path);
        String[] sentences = str.split("[\\.\\!\\?]");
        StringJoiner sentencesWithWord = new StringJoiner("; ");
        for (String sentence : sentences) {
            String[] words = sentence.split("[\s.,]+");
            for (String s : words) {
                if (Objects.equals(s, word)) {
                    sentencesWithWord.add(sentence);
                    break;
                }
            }
        }
        return sentencesWithWord.toString();
    }

    private static String Reader(String path) throws IOException {
        File pathToFile = new File(path);
        try (InputStream inputStream = new FileInputStream(pathToFile)) {
            int fileLength = (int) pathToFile.length();
            byte[] text = new byte[fileLength];
            inputStream.read(text);
            return new String(text);
        }
    }
}

