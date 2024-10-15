/**
 * DLLNode class
 * 
 * @author {Armen Khachatryan}
 * @version {09/13/2024}
 * 
 * @param <T>
 *            input type for DLL Node
 */
public class DLLNode<T> {

    private T data; // Data stored in node
    private DLLNode<T> next;
    private DLLNode<T> prev;

    /**
     * DLLNode constructor
     * 
     * @param data
     *            input data for DLL Node
     */
    public DLLNode(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }


    /**
     * Returns data in node
     * 
     * @return data in node
     */
    public T toData() {
        return data;
    }


    /**
     * Gets the next node
     * 
     * @return the next node
     */
    public DLLNode<T> getNext() {
        return next;
    }


    /**
     * Sets the next node
     * 
     * @param next
     *            the next node to set
     */
    public void setNext(DLLNode<T> next) {
        this.next = next;
    }


    /**
     * Gets the previous node
     * 
     * @return the previous node
     */
    public DLLNode<T> getPrev() {
        return prev;
    }


    /**
     * Sets the previous node
     * 
     * @param prev
     *            the previous node to set
     */
    public void setPrev(DLLNode<T> prev) {
        this.prev = prev;
    }
}
