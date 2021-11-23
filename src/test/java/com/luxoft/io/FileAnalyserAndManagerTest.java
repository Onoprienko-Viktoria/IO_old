package com.luxoft.io;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FileAnalyserAndManagerTest {

    @BeforeEach
    public void create() throws IOException {
        File path1 = new File("./src/test/resources/path1");
        path1.mkdirs();
        File path2 = new File("./src/test/resources/path2");
        path2.mkdir();
        File path3 = new File("./src/test/resources/path3");
        path3.mkdir();
        File path4 = new File("./src/test/resources/path4");
        path4.mkdir();
        File path5 = new File("./src/test/resources/path4/path5");
        path5.mkdir();


        File file1 = new File("./src/test/resources/path4/file1.txt");
        file1.createNewFile();
        File file2 = new File("./src/test/resources/path4/file2.txt");
        file2.createNewFile();
        File file3 = new File("./src/test/resources/path1/file3.txt");
        file3.createNewFile();
        File file4 = new File("./src/test/resources/path4/path5/file4.txt");
        file4.createNewFile();
        File file5 = new File("./src/test/resources/path4/path5/file5.txt");
        file5.createNewFile();

        try (FileWriter fileWriter = new FileWriter(file4)) {
            String text = "Я люблю java ! Язык программирования java лучший. Без него было бы плохо. java лучший java ? Да, лучший.";
            fileWriter.write(text);
        }

    }


    @Test
    void testWordRepetitionCounterWorkCorrectly() throws IOException {
        assertEquals(4, FileAnalyser.WordRepetitionCounter("java", "./src/test/resources/path4/path5/file4.txt"));
        assertEquals(1, FileAnalyser.WordRepetitionCounter("Я", "./src/test/resources/path4/path5/file4.txt"));
    }

    @Test
    void testSentencesWithWordWorkCorrectly() throws IOException {
        assertEquals("Я люблю java ;  Язык программирования java лучший;  java лучший java ", FileAnalyser.SentencesWithWord("java", "./src/test/resources/path4/path5/file4.txt"));
        assertEquals(" Без него было бы плохо", FileAnalyser.SentencesWithWord("Без", "./src/test/resources/path4/path5/file4.txt"));
    }

    @Test
    void testSentencesWithWordAndWordRepetitionCounterReturnEmptyAndZeroOnEmptyFile() throws IOException {
        assertEquals("", FileAnalyser.SentencesWithWord("jojo", "./src/test/resources/path4/file1.txt"));
        assertEquals(0, FileAnalyser.WordRepetitionCounter("jojo", "./src/test/resources/path4/file1.txt"));
    }

    @Test
    void testWordRepetitionCounterAndSentencesWithWordReturnEmptyAndZeroOnWrongWord() throws IOException {
        assertEquals(0, FileAnalyser.WordRepetitionCounter("jojo", "./src/test/resources/path4/path5/file4.txt"));
        assertEquals("", FileAnalyser.SentencesWithWord("jojo", "./src/test/resources/path4/path5/file4.txt"));

    }

    @Test
    void testCountDirsWorkCorrectly() throws IOException {
        assertEquals(5, FileManager.countDirs("./src/test/resources"));
        assertEquals(1, FileManager.countDirs("./src/test/resources/path4"));
        assertEquals(0, FileManager.countDirs("./src/test/resources/path3"));
    }

    @Test
    void testCountDirsReturnZeroOnPathToTextFile() throws IOException {
        assertEquals(0, FileManager.countDirs("./src/test/resources/path4/path5/file4.txt"));
    }

    @Test
    void testCountFilesWorkCorrectly() {
        assertEquals(2, FileManager.countFiles("./src/test/resources/path4/path5"));
        assertEquals(0, FileManager.countFiles("./src/test/resources/path2"));
        assertEquals(1, FileManager.countFiles("./src/test/resources/path1"));
    }

    @Test
    void testCountFilesThrowsNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> FileManager.countFiles("./src/test/resources/path1/file3.txt"));
    }

    @Test
    void testCopyWorkCorrectly() throws IOException {
        FileManager.copy("./src/test/resources/path1/file3.txt", "./src/test/resources/path3/file3.txt");
        assertEquals(1, FileManager.countFiles("./src/test/resources/path1"));
        assertEquals(1, FileManager.countFiles("./src/test/resources/path3"));
    }

    @Test
    void testMoveWorkCorrectly() throws IOException {
        FileManager.move("./src/test/resources/path4/file2.txt", "./src/test/resources/path3/file2.txt");
        assertEquals(3, FileManager.countFiles("./src/test/resources/path4"));
        assertEquals(1, FileManager.countFiles("./src/test/resources/path3"));
    }

    @AfterEach
    public void remove() {
        File file5 = new File("./src/test/resources/path4/path5/file5.txt");
        file5.delete();
        File file4 = new File("./src/test/resources/path4/path5/file4.txt");
        file4.delete();
        File path5 = new File("./src/test/resources/path4/path5");
        path5.delete();
        File file1 = new File("./src/test/resources/path4/file1.txt");
        file1.delete();
        File file2 = new File("./src/test/resources/path4/file2.txt");
        file2.delete();
        File path4 = new File("./src/test/resources/path4");
        path4.delete();
        File movedFile = new File("./src/test/resources/path3/file3.txt");
        movedFile.delete();
        File copyFile = new File("./src/test/resources/path3/file2.txt");
        copyFile.delete();
        File file3 = new File("./src/test/resources/path1/file3.txt");
        file3.delete();
        File path1 = new File("./src/test/resources/path1");
        path1.delete();
        File path2 = new File("./src/test/resources/path2");
        path2.delete();
        File path3 = new File("./src/test/resources/path3");
        path3.delete();

    }
}
