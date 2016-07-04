package pl.edu.pk.inf.java;

/**
 * Created by Natalie on 2016-07-03.
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
