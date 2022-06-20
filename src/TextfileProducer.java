import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class implements message producer using the file new.txt
 * @author Reem, DT20
 * @version 1.0
 */
public class TextfileProducer implements MessageProducer{
    private Message[] messages;
    private Message message;

    private String text;
    private String icon;
    private int delay;
    private int times;
    private int size;
    private int currentIndex = -1;
    private String filename;


    /**
     * @param filename the file to read
     */
    public TextfileProducer(String filename) {
        this.filename = filename;
        read();
    }

    /**
     * reads the messages from file
     * @return the messages objects
     */
    public Message[] read() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename),"UTF-8"))) {
            times = Integer.parseInt(br.readLine());
            delay = Integer.parseInt(br.readLine());
            size = Integer.parseInt(br.readLine());

            for (int i = 0; i < size(); i++) {
                text = br.readLine();
                icon = br.readLine();
                addMessage(text, icon, i);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return messages;
    }


    /**
     * adds messages with text and picture from file
     * @param text text in file
     * @param icon picture in file
     * @param i index for the message
     */
    private void addMessage(String text, String icon, int i){
        message = new Message(text, new ImageIcon(icon));
        if (messages == null) { //to init
            messages = new Message[size];
        }
        messages[i] = message;
    }

    /**
     * @return the duration to show the picture will be shown
     */
    @Override
    public int delay() {
        return delay;
    }

    /**
     * @return how many times the picture will be repeated
     */
    @Override
    public int times() {
        return times;
    }

    /**
     * @return the amount of each text and picture together
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return next message with the updated current index
     */
    @Override
    public Message nextMessage() {
        if(size()==0) {
            return null;
        }
        currentIndex = (currentIndex+1) % messages.length; //FROM EXAMPLE
        return messages[currentIndex];
    }
}
