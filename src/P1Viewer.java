/**
 * This class view the messages in UI
 * @author Reem Mohamed
 * @version 1.0
 */
public class P1Viewer extends Viewer{

    /**
     * @param messageManager instans to add listener to
     * @param width in new frame
     * @param height in new frame
     */
    public P1Viewer(MessageManager messageManager, int width, int height) {
        super(width, height);
        messageManager.addListener(new UpdateMessage());
    }

    /**
     * This class register the listener
     */
    private class UpdateMessage implements ManagerListener {
        @Override
        public void managerListener(Message message) { //updates message
            setMessage(message);
        }
    }
}
