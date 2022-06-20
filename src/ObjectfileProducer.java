import java.io.*;

/**
 * This class implements message producer using the file new.dat
 * @author Reem, DT20
 * @version 1.0
 */
public class ObjectfileProducer implements MessageProducer{
    private Message[] messages;

    private int delay;
    private int times;
    private int size;
    private int currentIndex = -1;
    private String filename;

    /**
     * @param filename file to get the objects from
     */
    public ObjectfileProducer(String filename) {
        this.filename = filename;
        read();
    }

    /**
     * reads the messages from file as object
     * @return the messages objects
     */
    public Message[] read() {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            times = ois.readInt();
            delay = ois.readInt();
            size = ois.readInt();

            for (int i = 0; i < size(); i++) {
                if (messages == null) { //to init
                    messages = new Message[size];
                }
                messages[i] = (Message) ois.readObject();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return messages;
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
     * @return the amount of message objects
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
