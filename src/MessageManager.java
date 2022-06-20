import java.util.LinkedList;

/**
 * This class gets the message from the buffer
 * @author Reem, DT20
 * @version 1.0
 */
public class MessageManager{
    private Buffer<Message> messageBuffer;
    Message message;
    private Thread thread;

    private LinkedList<ManagerListener> list = new LinkedList<ManagerListener>();


    /**
     * @param messageBuffer buffer to get message from
     */
    public MessageManager(Buffer<Message> messageBuffer) {
        this.messageBuffer = messageBuffer;
    }

    /**
     * adds listeners to the list of listeners
     * @param listener that will be added to the list of listeners
     */
    public void addListener(ManagerListener listener){
        if(listener != null){
            list.add(listener);
        }
    } // Listener will be registered using P1Viewer



    /**
     * Starts the thread
     */
    public void start(){
        if(thread==null) {
            thread = new Animation();
            thread.start();
        }
    }

    /**
     * This class uses a Thread, to get a message from the message buffer
     * @version 1.0
     */
    private class Animation extends Thread {
        public void run() {

            while (!Thread.interrupted()) {
                try {
                    message = messageBuffer.get(); // message from buffer (5)

                } catch (InterruptedException e) {
                    break;
                }
                for (ManagerListener listener: list ) {
                    listener.managerListener(message);
                }
                thread = null;
            }
        }

    }
}
