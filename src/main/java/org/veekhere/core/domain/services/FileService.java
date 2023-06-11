package org.veekhere.core.domain.services;

import org.veekhere.core.domain.enums.ElementType;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public final class FileService {
    public static String generateName(UUID uuid, ElementType type) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy-HH.mm.ss");
        LocalDateTime now = LocalDateTime.now();
        String fileExtension = "g".concat(type.toString().toLowerCase());
        return String.format("%s__%s.%s", uuid, dtf.format(now), fileExtension);
    }

    public static void createFile(String data, String fileName) {
        try(FileOutputStream fos = new FileOutputStream(fileName)) {
            writeFile(fos, data);
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static ArrayList<ArrayList<String>> readFile(String fileName) {
        String[] data = readFileAndSplitByLine(fileName);
        return splitDataArguments(data);
    }

    private static void writeFile(FileOutputStream fos, String data) throws IOException {
        byte[] buffer = data.getBytes();
        fos.write(buffer, 0, buffer.length);
        System.out.println("\nThe file has been written\n");
    }

    private static String[] readFileAndSplitByLine(String fileName) {
        String[] buffer = new String[0];

        try {
            buffer = new String(Files.readAllBytes(Paths.get(fileName))).split(";(\n)?", -1);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return buffer;
    }

    private static ArrayList<ArrayList<String>> splitDataArguments(String[] data) {
        ArrayList<ArrayList<String>> parsedFileData = new ArrayList<>();

        for (String str : data) {
            ArrayList<String> tmp = new ArrayList<>(Arrays.asList(str.split(" ", -1)));
            if (tmp.get(0).length() != 0) {
                parsedFileData.add(tmp);
            }
        }

        return parsedFileData;
    }
}
