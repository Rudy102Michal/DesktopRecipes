package pl.edu.pk.inf.java.DialogBoxes;

/**
 * Created by Michał on 2016-07-01.
 */
public class PopUpBox {
    protected String message;
    protected String title;

    public PopUpBox(String message, String title) {
        this.message = message;
        this.title = title;
    }

    public PopUpBox() {
    }
}
