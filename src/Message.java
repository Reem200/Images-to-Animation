import javax.swing.*;
import java.io.Serializable;

/**
 * This class includes data management in messages
 * @author Reem Mohamed, DT20
 * @version 1.0
 */
public class Message implements Serializable { //Ska användas i strömmar
    private String text;
    private Icon icon;

    /**
     * @param text text written in message
     * @param icon picture used in message
     */
    public Message(String text, Icon icon) {
        this.text = text;
        this.icon = icon;
    }

    /**
     * @return picture
     */
    public Icon getIcon(){
        return icon;
    }

    /**
     * @return message text
     */
    public String getText() {
        return text;
    }

}
