/**
 * This class places message producer object in a buffer
 * @author Reem Mohamed, DT20
 * @version 1.0
 */
public class MessageProducerInput {
    private Buffer<MessageProducer> producerBuffer;

    /**
     * @param producerBuffer buffer for placing message producer implementation
     */
    public MessageProducerInput(Buffer<MessageProducer> producerBuffer){
        this.producerBuffer = producerBuffer;
    }


    /**
     * @param message that will be added to the message producer buffer
     */
    public void addMessageProducer(MessageProducer message){
        producerBuffer.put(message);
    }
}
