/**
 * This class gets the message producer object from the buffer
 * and places the message in the buffer
 * @author Reem Mohamed, DT20
 * @version 1.0
 */
public class Producer {
    private Buffer<MessageProducer> prodBuffer;
    private Buffer<Message> messageBuffer;
    private Thread thread;

    /**
     * Construction
     * @param prodBuffer buffer of producer
     * @param messageBuffer buffer of message
     */
    public Producer(Buffer<MessageProducer> prodBuffer, Buffer<Message> messageBuffer) {
        this.prodBuffer = prodBuffer;
        this.messageBuffer = messageBuffer;
    }

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
     * This class uses a Thread, to get a message from the message producer buffer
     * and send it (put) to the message buffer
     * @version 2.0
     */
    private class Animation extends Thread {
        public void run() {

            while (!Thread.interrupted()) {
                MessageProducer mp;
                Message[] message;
                int times;
                try {
                    mp = prodBuffer.get(); //1. message from buffer (3)
                    message = new Message[mp.size()];
                    times = mp.times();
                    for (int i = 0; i < times; i++) {
                        for(int row = 0; row < message.length; row++) {
                            message[row] = mp.nextMessage();
                            messageBuffer.put(message[row]); //2. message puts in buffer (4)
                            Thread.sleep(mp.delay());
                        }
                    }


                } catch (InterruptedException e) {
                    break;
                }
                thread = null;

            }
        }

    }
}
