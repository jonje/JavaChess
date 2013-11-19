package edu.neumont.jjensen.lab;

import edu.neumont.jjensen.lab.controller.Controller;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: jjensen
 * Date: 11/12/13
 * Time: 1:12 PM
 */
public class FileInputReader {

    private MoveInterpreter moveInterpreter;

    public FileInputReader(Controller controller) {
        moveInterpreter = new MoveInterpreter(controller);


    }


    public void readInFile(String filePath) {



        try(BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)))) {

            while(reader.ready()) {
                moveInterpreter.interpretMove(reader.readLine());

           }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

}
