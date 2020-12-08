package javacore.files.task1.src;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String rootPath = "D:\\Work\\Netology\\src\\javacore\\files\\task1\\src\\Games";
        StringBuilder sb = new StringBuilder();
        String[] dirRoot = {"src", "res", "savegames", "temp"};
        String[] dirRootSrc = {"main", "test"};
        String[] dirRootRes = {"brawbles", "vectors", "icons"};
        String[] filesMain = {"Main.java", "Utils.java"};
        String[] filesTemp = {"temp.txt"};

        mkDir(rootPath, sb, dirRoot);
        mkDir(rootPath.concat("\\").concat(dirRoot[0]), sb, dirRootSrc);
        mkDir(rootPath.concat("\\").concat(dirRoot[1]), sb, dirRootRes);

        mkFiles(rootPath, sb, dirRoot[0], dirRootSrc[0], filesMain);
        mkFiles(rootPath, sb, dirRoot[3], filesTemp);

        FileWriter fw = new FileWriter(
                rootPath
                        .concat("\\")
                        .concat(dirRoot[3])
                        .concat("\\")
                        .concat(filesTemp[0])
        );

        fw.append(sb);
        fw.close();
    }

    private static void mkFiles(String rootPath, StringBuilder sb, String str, String[] filesNameArr) throws IOException {
        for (String fileName : filesNameArr) {
            File myFile = new File(
                    rootPath
                            .concat("\\")
                            .concat(str)
                            .concat("\\")
                            .concat(fileName)
            );
            if (myFile.createNewFile()) {
                sb
                        .append("Файл ")
                        .append(fileName)
                        .append(" создан \n")
                ;
            } else {
                sb
                        .append("Файл ")
                        .append(fileName)
                        .append(" уже существует \n")
                ;
            }
        }
    }

    private static void mkFiles(String rootPath, StringBuilder sb, String str, String str1, String[] filesNameArr) throws IOException {
        for (String fileName : filesNameArr) {
            File myFile = new File(
                    rootPath
                            .concat("\\")
                            .concat(str)
                            .concat("\\")
                            .concat(str1)
                            .concat("\\")
                            .concat(fileName)
            );

            if (myFile.createNewFile()) {
                sb
                        .append("Файл ")
                        .append(fileName)
                        .append(" создан \n")
                ;
            } else {
                sb
                        .append("Файл ")
                        .append(fileName)
                        .append(" уже существует \n")
                ;
            }
        }
    }

    private static void mkDir(String rootPath, StringBuilder sb, String[] dirNameArr) {
        for (String dirName : dirNameArr) {
            File dir = new File(
                    rootPath
                            + "\\"
                            + dirName
            );
            if (dir.mkdir()) {
                sb
                        .append("Каталог ")
                        .append(dirName)
                        .append(" создан \n")
                ;
            } else {
                sb
                        .append("Каталог ")
                        .append(dirName)
                        .append(" уже существует \n")
                ;
            }
        }
    }
}
