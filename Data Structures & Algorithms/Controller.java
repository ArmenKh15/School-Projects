/**
 * Controller Class:
 * Declaration of hashTables and Graph
 * 
 * @author {Armen Khachatryan}
 * @version {09/13/2024}
 */
public class Controller {

    private HashTableArr artistTable;
    private HashTableArr songTable;
    private HashTableArr recordTable;

    private Graph graph;

    /**
     * Controller Constructor and initialization of tables
     * 
     * @param initialCapacity
     *            initial capacity for table
     */
    public Controller(int initialCapacity) {
        artistTable = new HashTableArr(initialCapacity);
        songTable = new HashTableArr(initialCapacity);
        recordTable = new HashTableArr(initialCapacity);

        graph = new Graph(initialCapacity);
    }


    /**
     * Insert a key into the specified hash table
     * 
     * @param keyArt
     *            string of artist key
     * @param keySong
     *            string of song key
     */
    public void insert(String keyArt, String keySong) {

        if (recordTable.containsKey((keyArt + "<SEP>" + keySong))) {
            System.out.println("|" + keyArt + "<SEP>" + keySong + "|"
                + " duplicates a record already in the database.");
            return;
        }
        else {
            if (artistTable.containsKey(keyArt) == false) {
                if (artistTable.size() >= artistTable.capacity() / 2) {
                    artistTable.insert(keyArt);
                    System.out.println("Artist hash table size doubled.");
                    System.out.println("|" + keyArt + "|"
                        + " is added to the Artist database.");
                }
                else {
                    artistTable.insert(keyArt);
                    System.out.println("|" + keyArt + "|"
                        + " is added to the Artist database.");
                }
            }
            if (songTable.containsKey(keySong) == false) {
                if (songTable.size() >= songTable.capacity() / 2) {
                    songTable.insert(keySong);
                    System.out.println("Song hash table size doubled.");
                    System.out.println("|" + keySong + "|"
                        + " is added to the Song database.");
                }
                else {
                    songTable.insert(keySong);
                    System.out.println("|" + keySong + "|"
                        + " is added to the Song database.");
                }
            }
            recordTable.insert(keyArt + "<SEP>" + keySong);

            insertGraph(keyArt, keySong);
        }
    }


    /**
     * Inserts artist and song into graph
     * 
     * @param keyArt
     *            string key value for artist
     * @param keySong
     *            string key value for song
     */
    private void insertGraph(String keyArt, String keySong) {

        graph.addNode(keyArt);
        graph.addNode(keySong);
        graph.addEdge(keyArt, keySong);

    }


    /**
     * Remove artist/song from graph
     * 
     * @param type
     *            string artist or song
     * @param key
     *            string value being removed
     */
    private void removeGraph(String type, String key) {

        if (type.equals("Song")) {
            graph.removeNode(key);
        }
        else if (type.equals("Artist")) {
            graph.removeNode(key);

        }
    }


    /**
     * Remove all records containing a specific key
     * 
     * @param key
     *            string key value of record
     */
    private void removeRecordsContaining(String key) {

        String[] recordsToRemove = recordTable.findRecordsContaining(key);
        for (String recordKey : recordsToRemove) {
            recordTable.remove(recordKey);
        }
    }


    /**
     * Remove an artist or song from the database and associated records
     * 
     * @param type
     *            string artist or song
     * @param key
     *            string of input key
     */
    public void remove(String type, String key) {

        if (type.equals("Artist")) {
            if (artistTable.containsKey(key)) {
                artistTable.remove(key);
                System.out.println("|" + key + "|"
                    + " is removed from the Artist database.");

                removeGraph("Artist", key);

                removeRecordsContaining(key + "<SEP>");
            }
            else {
                System.out.println("|" + key + "|"
                    + " does not exist in the Artist database.");
            }
        }
        else if (type.equals("Song")) {
            if (songTable.containsKey(key)) {
                songTable.remove(key);

                graph.removeEdge(type, key);

                System.out.println("|" + key + "|"
                    + " is removed from the Song database.");

                removeGraph("Song", key);

                removeRecordsContaining("<SEP>" + key);
            }
            else {
                System.out.println("|" + key + "|"
                    + " does not exist in the Song database.");
            }
        }
        else {
            System.out.println("Invalid type. Use Artist or Song.");
        }
    }


    /**
     * Print contents of both hash tables
     * 
     * @param type
     *            string artist or song
     */
    public void print(String type) {
        if (type.equals("Artist")) {
            artistTable.printTable();
            System.out.println("total artists: " + artistTable.size());
        }
        else if (type.equals("Song")) {
            songTable.printTable();
            System.out.println("total songs: " + songTable.size());
        }
        else if (type.equals("Graph")) {
            // Compute connected components

            graph.recomputeConnectedComponents();
        }
        else {
            System.out.println("Invalid type. Use Artist or Song or Graph.");
        }
    }


    /**
     * Clear both hash tables
     */
    public void clearTables() {
        artistTable.clear();
        songTable.clear();
    }


    /**
     * Get size of hash tables
     * 
     * @param type
     *            string artist or song
     * @return returns table size of given type and throws
     *         an error if type is not valid
     */
    public int getSizeOfTable(String type) {
        if (type.equals("Artist")) {
            return artistTable.size();
        }
        else if (type.equals("Song")) {
            return songTable.size();
        }
        else {
            System.out.println("Invalid type. Use Artist or Song.");
            return 0;
        }
    }

}
