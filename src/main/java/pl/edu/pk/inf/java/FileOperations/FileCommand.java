package pl.edu.pk.inf.java.FileOperations;

import pl.edu.pk.inf.java.FileOperations.Command;

/**
 * Created by Berrion on 2016-07-05.
 */
public class FileCommand {
    private Command mode;
    public void setMode(Command command){
        mode = command;
    }

    public void execute(){
        mode.execute();
    }
}

