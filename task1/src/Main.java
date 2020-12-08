package javacore.files.task1.src;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String rootPath = "D:\\Work\\Netology\\src\\javacore\\files\\task1\\src\\Games";
        StringBuilder installerLog = new StringBuilder();
        String[] dirRoot = {"src", "res", "savegames", "temp"};
        String[] dirRootSrc = {"main", "test"};
        String[] dirRootRes = {"brawbles", "vectors", "icons"};
        String[] filesMain = {"Main.java", "Utils.java"};
        String[] filesTemp = {"temp.txt"};

        mkDir(rootPath, installerLog, dirRoot);
        mkDir(rootPath.concat("\\").concat(dirRoot[0]), installerLog, dirRootSrc);
        mkDir(rootPath.concat("\\").concat(dirRoot[1]), installerLog, dirRootRes);

        mkFiles(rootPath, installerLog, dirRoot[0], dirRootSrc[0], filesMain);
        mkFiles(rootPath, installerLog, dirRoot[3], filesTemp);

        writeToFile(rootPath, installerLog, dirRoot[3], filesTemp[0]);
    }

    public static void writeToFile(String rootPath, StringBuilder installerLog, String dirName, String fileName) throws IOException {
        try (FileWriter fw = new FileWriter(
                rootPath
                        .concat("\\")
                        .concat(dirName)
                        .concat("\\")
                        .concat(fileName)
        )) {
            fw.append(installerLog);
        }
    }

    private static void createFileSystemObject(StringBuilder installerLog, String fileSystemObjectName, boolean ifCreated, String fileSystemObjectType) {
        if (ifCreated) {
            installerLog
                    .append(fileSystemObjectType)
                    .append(fileSystemObjectName)
                    .append(" создан \n")
            ;
        } else {
            installerLog
                    .append(fileSystemObjectType)
                    .append(fileSystemObjectName)
                    .append(" уже существует \n")
            ;
        }
    }

    private static void mkFiles(String rootPath, StringBuilder installerLog, String dirName, String[] filesNameArr) throws IOException {
        for (String fileName : filesNameArr) {
            File myFile = new File(
                    rootPath
                            .concat("\\")
                            .concat(dirName)
                            .concat("\\")
                            .concat(fileName)
            );

            createFileSystemObject(installerLog, fileName, myFile.createNewFile(), "Файл ");
        }
    }

    private static void mkFiles(String rootPath, StringBuilder installerLog, String dirName1, String dirName2, String[] filesNameArr) throws IOException {
        for (String fileName : filesNameArr) {
            File myFile = new File(
                    rootPath
                            .concat("\\")
                            .concat(dirName1)
                            .concat("\\")
                            .concat(dirName2)
                            .concat("\\")
                            .concat(fileName)
            );

            createFileSystemObject(installerLog, fileName, myFile.createNewFile(), "Файл ");
        }
    }

    private static void mkDir(String rootPath, StringBuilder installerLog, String[] dirNameArr) {
        for (String dirName : dirNameArr) {
            File dir = new File(
                    rootPath
                            + "\\"
                            + dirName
            );
            createFileSystemObject(installerLog, dirName, dir.mkdir(), "Каталог ");
        }
    }
}
