package javacore.files.task2.src;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Random random = new Random();
        String rootPath = "D:\\Work\\Netology\\src\\javacore\\files\\task1\\src\\Games";
        List<GameProgress> gameProgressList = new ArrayList<>();
        List<String> pathSaveGameFiles;

        createGameProgressList(random, gameProgressList);

        pathSaveGameFiles = saveGame(rootPath.concat("\\savegames"), gameProgressList);

        zipFiles(rootPath.concat("\\savegames\\zipSave.zip"), pathSaveGameFiles);


    }

    private static void createGameProgressList(Random random, List<GameProgress> gameProgressList) {
        for (int i = 0; i < 3; i++) {
            gameProgressList.add(
                    new GameProgress(
                            random.nextInt(90) + 10,
                            random.nextInt(5) + 1,
                            random.nextInt(10) + 1,
                            (i + 1) * random.nextDouble()
                    )
            );
        }
    }

    private static List<String> saveGame(String pathToDir, List<GameProgress> gameProgressList) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < gameProgressList.size(); i++) {
            File file = new File(pathToDir.concat("\\save" + (i + 1) + ".dat"));
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try (FileOutputStream fos = new FileOutputStream(file);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(gameProgressList.get(i));
            } catch (IOException e) {
                e.printStackTrace();
            }

            result.add(file.toString());
        }
        return result;
    }

    private static void zipFiles(String pathToFile, List<String> pathSaveGameFiles) {
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(pathToFile))) {
            for (String pathSaveGameFile : pathSaveGameFiles) {
                try (FileInputStream fis = new FileInputStream(pathSaveGameFile)){
                    ZipEntry entry = new ZipEntry(pathSaveGameFile);
                    zipOut.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zipOut.write(buffer);
                    zipOut.closeEntry();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            deleteFilesDat(pathSaveGameFiles);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deleteFilesDat(List<String> pathSaveGameFiles) {
        for (String pathSaveGameFile : pathSaveGameFiles) {
            File file = new File(pathSaveGameFile);
            file.delete();
        }
    }
}
