package org.example.sender;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileSenderTest {
    @Test
    public void testReadWriteFile() {
        FileSender fileSender = new FileSender();
        String testFileName = "test.txt";
        String testFilePath = "src/test/resources/" + testFileName;
        String testFileContent = "Test file content";
        List<Integer> expectedFileData = new ArrayList<>();
        for (char c : testFileContent.toCharArray()) {
            expectedFileData.add((int) c);
        }

        fileSender.writeFile(expectedFileData, testFileName);
        List<Integer> actualFileData = fileSender.readFile(new File(testFilePath));

        assertEquals(expectedFileData, actualFileData);
    }
    @Test
    public void testAction() throws IOException {
        FileSender fileSender = new FileSender();
        String testFileName = "test.txt";
        String testFilePath = "src/test/resources/" + testFileName;
        String testFileContent = "Test file content";
        List<Integer> expectedFileData = new ArrayList<>();
        for (char c : testFileContent.toCharArray()) {
            expectedFileData.add((int) c);
        }

        fileSender.action(testFilePath);

        File sentFile = new File(fileSender.getPathToFolder() + testFileName);
        FileInputStream fileInputStream = new FileInputStream(sentFile);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int data;
        while ((data = fileInputStream.read()) != -1) {
            byteArrayOutputStream.write(data);
        }
        byte[] actualBytes = byteArrayOutputStream.toByteArray();
        byte[] expectedBytes = testFileContent.getBytes();
        assertArrayEquals(expectedBytes, actualBytes);
    }


}
