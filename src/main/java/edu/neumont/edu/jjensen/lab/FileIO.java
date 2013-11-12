package edu.neumont.edu.jjensen.lab;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jonathan
 * Date: 11/12/13
 * Time: 1:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileIO {

    private List<String> lines;
    private File filePath;

    public FileIO(String filePath) {
        this.filePath = new File(filePath);

    }

    public void readInFile() {
        lines = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            while(reader.ready()) {
                lines.add(reader.readLine());

           }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

}
