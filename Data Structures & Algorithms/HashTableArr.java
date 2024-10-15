/**
 * HashTable class
 * 
 * @author {Armen Khachatryan}
 * @version {09/13/2024}
 */
public class HashTableArr {

    private Record[] allRecords;
    private int size;

    /**
     * Record class
     * 
     * @author {Armen Khachatryan}
     * @version {09/13/2024}
     */
    private class Record {
        private String key;
        private boolean isTombstone;

        /**
         * Record constructor
         * 
         * @param key
         *            key in record node
         * @param isTombstone
         *            boolean representing if node is tombstone
         */
        Record(String key, boolean isTombstone) {
            this.key = key;
            this.isTombstone = isTombstone;
        }


        /**
         * String of node value
         * 
         * @return return node value
         */
        public String toString() {
            return isTombstone ? "TOMBSTONE" : "|" + key + "|";
        }
    }

    /**
     * Constructor with initial capacity
     * 
     * @param initialCapacity
     *            initial capacity of table
     */
    public HashTableArr(int initialCapacity) {
        if (initialCapacity <= 0) {
            System.out.println("Initial capacity must be positive.");
            return;
        }
        allRecords = new Record[initialCapacity];
        size = 0;
    }


    // Find index of key
    private int getIndex(String key, int length) {
        return Hash.h(key, length);
    }


    // Probe function
    private int probe(int originalIndex, int i, int length) {
        return (originalIndex + i * i) % length;
    }


    // Resize hashtable array
    private void resize() {
        int newCapacity = allRecords.length * 2;
        Record[] newTable = new Record[newCapacity];

        for (Record record : allRecords) {
            if (record != null && !record.isTombstone) {
                int newIndex = getIndex(record.key, newCapacity);
                int i = 0;
                int probeIndex = probe(newIndex, i, newCapacity);
                while (newTable[probeIndex] != null) {
                    i++;
                    probeIndex = probe(newIndex, i, newCapacity);
                }
                newTable[probeIndex] = new Record(record.key, false);
            }
        }

        allRecords = newTable;
    }


    /**
     * Insert method for table
     * 
     * @param key
     *            key to insert into table
     */
    public void insert(String key) {

        if (size >= allRecords.length / 2) {
            resize();
        }

        int originalIndex = getIndex(key, allRecords.length);
        int i = 0;
        int index = probe(originalIndex, i, allRecords.length);

        // Find an available slot or existing key
        while (allRecords[index] != null && !allRecords[index].key.equals(
            key)) {
            i++;
            index = probe(originalIndex, i, allRecords.length);
        }

        // If the slot is empty or a tombstone, insert new key
        if (allRecords[index] == null || allRecords[index].isTombstone) {
            allRecords[index] = new Record(key, false);
            size++;
        }
    }


    /**
     * Checks if key exists in table
     * 
     * @param key
     *            key to check for
     * @return return boolean representing if key exists in table
     */
    public boolean containsKey(String key) {
        int originalIndex = getIndex(key, allRecords.length);
        int i = 0;
        int index = probe(originalIndex, i, allRecords.length);

        while (allRecords[index] != null) {
            if (!allRecords[index].isTombstone && allRecords[index].key.equals(
                key)) {
                return true;
            }
            i++;
            index = probe(originalIndex, i, allRecords.length);
        }
        return false;
    }


    /**
     * Remove key from table
     * 
     * @param key
     *            key to remove from table
     */
    public void remove(String key) {
        int originalIndex = getIndex(key, allRecords.length);
        int i = 0;
        int index = probe(originalIndex, i, allRecords.length);

        while (allRecords[index] != null) {
            if (allRecords[index].key.equals(key)) {
                allRecords[index].isTombstone = true;
                size--;
                return;
            }
            i++;
            index = probe(originalIndex, i, allRecords.length);
        }
    }


    /**
     * Return size of table
     * 
     * @return int size of table
     */
    public int size() {
        return size;
    }


    /**
     * return capacity of table
     * 
     * @return int capacity of table
     */
    public int capacity() {
        return allRecords.length;
    }


    /**
     * clear table
     */
    public void clear() {
        allRecords = new Record[allRecords.length];
        size = 0;
    }


    /**
     * print table
     */
    public void printTable() {
        for (int i = 0; i < allRecords.length; i++) {
            if (allRecords[i] != null) {
                System.out.println(i + ": " + allRecords[i]);
            }
        }
    }


    /**
     * Method to find records containing a specific key
     * 
     * @param key
     *            key to look for in records table
     * @return array of all records containing key
     */
    public String[] findRecordsContaining(String key) {
        // Count matching records
        int count = 0;
        for (Record record : allRecords) {
            if (record != null && !record.isTombstone && record.key.contains(
                key)) {
                count++;
            }
        }

        // Create an array to store the matching records
        String[] result = new String[count];
        int index = 0;
        for (Record record : allRecords) {
            if (record != null && !record.isTombstone && record.key.contains(
                key)) {
                result[index++] = record.key;
            }
        }

        return result;
    }
}
