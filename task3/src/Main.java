package javacore.files.task3.src;

import javacore.files.task2.src.GameProgress;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    public static void main(String[] args) {
        String rootPath = "D:\\Work\\Netology\\src\\javacore\\files\\task1\\src\\Games";
        List<String> pathSaveGameFiles;
        List<GameProgress> gameProgressList;

        pathSaveGameFiles = openZip(rootPath);

        gameProgressList = openProgress(pathSaveGameFiles);

        for (GameProgress gameProgress : gameProgressList) {
            System.out.println(gameProgress);
        }

    }

    private static List<GameProgress> openProgress(List<String> pathSaveGameFiles) {
        List<GameProgress> gameProgressList = new ArrayList<>();
        for (String pathSaveGameFile : pathSaveGameFiles) {
            try (FileInputStream fileInputStream = new FileInputStream(pathSaveGameFile);
                 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                gameProgressList.add((GameProgress) objectInputStream.readObject());

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        deleteFilesDat(pathSaveGameFiles);

        return gameProgressList;
    }

    private static List<String> openZip(String rootPath) {
        List<String> result = new ArrayList<>();
        try (ZipInputStream zipInputStream = new ZipInputStream(
                new FileInputStream(rootPath.concat("\\savegames\\zipSave.zip")))) {
            ZipEntry entry;
            String name;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                name = entry.getName();
                result.add(name);
                FileOutputStream fileOutputStream = new FileOutputStream(name);
                for (int i = zipInputStream.read(); i != -1; i = zipInputStream.read()) {
                    fileOutputStream.write(i);
                }
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static void deleteFilesDat(List<String> pathSaveGameFiles) {
        for (String pathSaveGameFile : pathSaveGameFiles) {
            File file = new File(pathSaveGameFile);
            file.delete();
        }
    }
}
