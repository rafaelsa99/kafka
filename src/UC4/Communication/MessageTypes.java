
package UC4.Communication;

/**
 * Types of the messages to be sent.
 * @author Rafael Sá (104552), Luís Laranjeira (81526)
 */
public class MessageTypes {
    /** Record from the sensor. */
    public static int RECORD = 1;
    /** Reply with no errors. */
    public static int RESP_OK = 2;
    /** End of the Source. */
    public static int END = 3;
    
    /**
     * To avoid instantiation.
     */
    private MessageTypes() {
    }
}
