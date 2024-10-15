/**
 * DLL class
 * 
 * @author {Armen Khachatryan}
 * @version {09/13/2024}
 * 
 * @param <T>
 *            value type in DLL
 */
public class DoubleLL<T> {

    private DLLNode<T> head;
    private DLLNode<T> tail;
    private int size;

    /**
     * DLL Constructor
     */
    public DoubleLL() {
        head = null;
        tail = null;
        size = 0;
    }


    /**
     * Add a new element at the end of the list
     * 
     * @param data
     *            stored in current node
     */
    public void append(T data) {
        DLLNode<T> newNode = new DLLNode<>(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        }
        else {
            tail.setNext(newNode); // Use setter
            newNode.setPrev(tail); // Use setter
            tail = newNode;
        }
        size++;
    }


    /**
     * Remove a specific element
     * 
     * @param data
     *            to be removed
     */
    public void remove(T data) {
        DLLNode<T> current = head;
        while (current != null) {
            if (current.toData().equals(data)) {
                if (current.getPrev() != null) {
                    current.getPrev().setNext(current.getNext());
                }
                else {
                    head = current.getNext(); // Removing the head
                }

                if (current.getNext() != null) {
                    current.getNext().setPrev(current.getPrev());
                }
                else {
                    tail = current.getPrev(); // Removing the tail
                }

                size--;
                return; // Exit after removing the first occurrence
            }
            current = current.getNext(); // Use getter
        }
    }


    /**
     * Print the list from head to tail
     */
    public void printForward() {
        DLLNode<T> current = head;
        while (current != null) {
            System.out.print(current.toData() + " ");
            current = current.getNext(); // Use getter
        }
        System.out.println();
    }


    /**
     * Get the size of the list
     * 
     * @return size of DLL
     */
    public int size() {
        return size;
    }


    /**
     * Check if the list is empty
     * 
     * @return if DLL is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * Clear the list
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }


    /**
     * Return head
     * 
     * @return head of DLL
     */
    public DLLNode<T> giveHead() {
        return head;
    }
}
