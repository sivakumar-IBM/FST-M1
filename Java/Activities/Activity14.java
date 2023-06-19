package activities;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Activity14 {
    private static String filePath = "src/test/resources/Test.txt";

    public static void main(String[] args) {
        try {
            //Create text file
            File file = new File(filePath);
            boolean fStatus = file.createNewFile();
            if (fStatus) {
                System.out.println("file creation done");
            } else {
                System.out.println("file already exist");
            }

            //get the file Object
            File fileUtil = FileUtils.getFile(filePath);
            //Read file
            System.out.println("Data in file: " + FileUtils.readFileToString(fileUtil, "UTF8"));

            //Create directory
            File destDir = new File("resources");
            //Copy file to directory
            FileUtils.copyFileToDirectory(file, destDir);

            //Get file from new directory
            File newFile = FileUtils.getFile(destDir, "Test.txt");
            //Read data from file
            String newFileData = FileUtils.readFileToString(newFile, "UTF8");
            //Print it
            System.out.println("Data in new file: " + newFileData);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
