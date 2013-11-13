package edu.neumont.edu.jjensen.lab;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jonathan
 * Date: 11/12/13
 * Time: 1:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileInputReader {

    private List<String> moves;
    private File filePath;

    public FileInputReader(String filePath) {
        this.filePath = new File(filePath);

    }

    public Iterator<String> getMovesList(){
        return moves.iterator();
    }

    public void readInFile() {
        moves = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            while(reader.ready()) {
                moves.add(reader.readLine());

           }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void displayFile() {
        for(String line : moves) {
            System.out.println(line);
        }
    }

}
