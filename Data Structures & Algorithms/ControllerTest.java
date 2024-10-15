import student.TestCase;

/**
 * Controller test class
 * 
 * @author {Armen Khachatryan}
 * @version {09/13/2024}
 */
public class ControllerTest extends TestCase {

    private Controller controller;

    /**
     * Setup tests
     */
    public void setUp() {
        // Nothing Here
        controller = new Controller(10);
    }


    /**
     * Test insert
     */
    public void testcontrollerInsert0() {

        controller.insert("testArtist", "testSong");
        assertEquals("|testArtist| is added to the Artist database.\n"
            + "|testSong| is added to the Song database.\n", systemOut()
                .getHistory());

        assertTrue(controller.getSizeOfTable("Artist") == 1);
        assertTrue(controller.getSizeOfTable("Song") == 1);
    }


    /**
     * Test multiple inserts
     */
    public void testcontrollerInsert1() {

        controller.insert("testArtist", "testSong");
        controller.insert("testArtist1", "testSong1");
        controller.insert("testArtist2", "testSong2");

        assertEquals("|testArtist| is added to the Artist database.\n"
            + "|testSong| is added to the Song database.\n"
            + "|testArtist1| is added to the Artist database.\n"
            + "|testSong1| is added to the Song database.\n"
            + "|testArtist2| is added to the Artist database.\n"
            + "|testSong2| is added to the Song database.\n", systemOut()
                .getHistory());

        assertTrue(controller.getSizeOfTable("Artist") == 3);
        assertTrue(controller.getSizeOfTable("Song") == 3);
    }


    /**
     * Test insert duplicate record
     */
    public void testcontrollerInsert2() {

        controller.insert("testArtist", "testSong");
        controller.insert("testArtist", "testSong");
        assertEquals("|testArtist| is added to the Artist database.\n"
            + "|testSong| is added to the Song database.\n"
            + "|testArtist<SEP>testSong| duplicates "
            + "a record already in the database.\n", systemOut().getHistory());

        assertTrue(controller.getSizeOfTable("Artist") == 1);
        assertTrue(controller.getSizeOfTable("Song") == 1);
    }


    /**
     * Test insert duplicate artist
     */
    public void testcontrollerInsert3() {

        controller.insert("testArtist", "testSong");
        controller.insert("testArtist", "testSong1");
        assertEquals("|testArtist| is added to the Artist database.\n"
            + "|testSong| is added to the Song database.\n"
            + "|testSong1| is added to the Song database.\n", systemOut()
                .getHistory());

        assertTrue(controller.getSizeOfTable("Artist") == 1);
        assertTrue(controller.getSizeOfTable("Song") == 2);
    }


    /**
     * Test insert duplicate song
     */
    public void testcontrollerInsert4() {

        controller.insert("testArtist", "testSong");
        controller.insert("testArtist1", "testSong");
        assertEquals("|testArtist| is added to the Artist database.\n"
            + "|testSong| is added to the Song database.\n"
            + "|testArtist1| is added to the Artist database.\n", systemOut()
                .getHistory());

        assertTrue(controller.getSizeOfTable("Artist") == 2);
        assertTrue(controller.getSizeOfTable("Song") == 1);
    }


    /**
     * Test insert double size
     */
    public void testcontrollerInsert5() {

        controller.insert("testArtist", "testSong");
        controller.insert("testArtist1", "testSong1");
        controller.insert("testArtist2", "testSong2");
        controller.insert("testArtist3", "testSong3");
        controller.insert("testArtist4", "testSong4");
        controller.insert("testArtist5", "testSong5");

        assertEquals("|testArtist| is added to the Artist database.\n"
            + "|testSong| is added to the Song database.\n"
            + "|testArtist1| is added to the Artist database.\n"
            + "|testSong1| is added to the Song database.\n"
            + "|testArtist2| is added to the Artist database.\n"
            + "|testSong2| is added to the Song database.\n"
            + "|testArtist3| is added to the Artist database.\n"
            + "|testSong3| is added to the Song database.\n"
            + "|testArtist4| is added to the Artist database.\n"
            + "|testSong4| is added to the Song database.\n"
            + "Artist hash table size doubled.\n"
            + "|testArtist5| is added to the Artist database.\n"
            + "Song hash table size doubled.\n"
            + "|testSong5| is added to the Song database.\n", systemOut()
                .getHistory());

        assertTrue(controller.getSizeOfTable("Artist") == 6);
        assertTrue(controller.getSizeOfTable("Song") == 6);
    }


    /**
     * Test insert double size dupe artist
     */
    public void testcontrollerInsert6() {

        controller.insert("testArtist", "testSong");
        controller.insert("testArtist1", "testSong1");
        controller.insert("testArtist2", "testSong2");
        controller.insert("testArtist3", "testSong3");
        controller.insert("testArtist4", "testSong4");
        controller.insert("testArtist", "testSong5");

        assertEquals("|testArtist| is added to the Artist database.\n"
            + "|testSong| is added to the Song database.\n"
            + "|testArtist1| is added to the Artist database.\n"
            + "|testSong1| is added to the Song database.\n"
            + "|testArtist2| is added to the Artist database.\n"
            + "|testSong2| is added to the Song database.\n"
            + "|testArtist3| is added to the Artist database.\n"
            + "|testSong3| is added to the Song database.\n"
            + "|testArtist4| is added to the Artist database.\n"
            + "|testSong4| is added to the Song database.\n"
            + "Song hash table size doubled.\n"
            + "|testSong5| is added to the Song database.\n", systemOut()
                .getHistory());

        assertTrue(controller.getSizeOfTable("Artist") == 5);
        assertTrue(controller.getSizeOfTable("Song") == 6);
    }


    /**
     * Test insert double size dupe song
     */
    public void testcontrollerInsert7() {

        controller.insert("testArtist", "testSong");
        controller.insert("testArtist1", "testSong1");
        controller.insert("testArtist2", "testSong2");
        controller.insert("testArtist3", "testSong3");
        controller.insert("testArtist4", "testSong4");
        controller.insert("testArtist5", "testSong");

        assertEquals("|testArtist| is added to the Artist database.\n"
            + "|testSong| is added to the Song database.\n"
            + "|testArtist1| is added to the Artist database.\n"
            + "|testSong1| is added to the Song database.\n"
            + "|testArtist2| is added to the Artist database.\n"
            + "|testSong2| is added to the Song database.\n"
            + "|testArtist3| is added to the Artist database.\n"
            + "|testSong3| is added to the Song database.\n"
            + "|testArtist4| is added to the Artist database.\n"
            + "|testSong4| is added to the Song database.\n"
            + "Artist hash table size doubled.\n"
            + "|testArtist5| is added to the Artist database.\n", systemOut()
                .getHistory());

        assertTrue(controller.getSizeOfTable("Artist") == 6);
        assertTrue(controller.getSizeOfTable("Song") == 5);
    }


    /**
     * Test remove
     */

    public void testcontrollerRemove0() {
        controller.insert("testArtist", "testSong");
        controller.insert("testArtist1", "testSong1");

        controller.remove("Artist", "testArtist");
        controller.remove("Song", "testSong");

        assertEquals("|testArtist| is added to the Artist database.\n"
            + "|testSong| is added to the Song database.\n"
            + "|testArtist1| is added to the Artist database.\n"
            + "|testSong1| is added to the Song database.\n"
            + "|testArtist| is removed from the Artist database.\n"
            + "|testSong| is removed from the Song database.\n", systemOut()
                .getHistory());

        assertTrue(controller.getSizeOfTable("Artist") == 1);
        assertTrue(controller.getSizeOfTable("Song") == 1);
    }


    /**
     * Test invalid remove
     */
    public void testcontrollerRemove1() {
        controller.remove("Artist", "testArtist");
        controller.remove("Song", "testSong");

        assertEquals("|testArtist| does not exist in the Artist database.\n"
            + "|testSong| does not exist in the Song database.\n", systemOut()
                .getHistory());

        assertTrue(controller.getSizeOfTable("Artist") == 0);
        assertTrue(controller.getSizeOfTable("Song") == 0);
    }


    /**
     * Test invalid remove then re-add artist
     */
    public void testcontrollerRemove2() {
        controller.insert("Artist", "testSong");
        controller.insert("Artist1", "testSong1");

        controller.remove("Artist", "Artist");
        controller.insert("Artist", "testSong");

        assertEquals("|Artist| is added to the Artist database.\n"
            + "|testSong| is added to the Song database.\n"
            + "|Artist1| is added to the Artist database.\n"
            + "|testSong1| is added to the Song database.\n"
            + "|Artist| is removed from the Artist database.\n"
            + "|Artist| is added to the Artist database.\n", systemOut()
                .getHistory());

        assertTrue(controller.getSizeOfTable("Artist") == 2);
        assertTrue(controller.getSizeOfTable("Song") == 2);
    }


    /**
     * Test invalid remove then re-add song
     */
    public void testcontrollerRemove3() {
        controller.insert("Artist", "testSong");
        controller.insert("Artist1", "testSong1");

        controller.remove("Song", "testSong");
        controller.insert("Artist", "testSong");

        assertEquals("|Artist| is added to the Artist database.\n"
            + "|testSong| is added to the Song database.\n"
            + "|Artist1| is added to the Artist database.\n"
            + "|testSong1| is added to the Song database.\n"
            + "|testSong| is removed from the Song database.\n"
            + "|testSong| is added to the Song database.\n", systemOut()
                .getHistory());

        assertTrue(controller.getSizeOfTable("Artist") == 2);
        assertTrue(controller.getSizeOfTable("Song") == 2);
    }


    /**
     * Test invalid remove then re-add both
     */
    public void testcontrollerRemove4() {
        controller.insert("Artist", "testSong");
        controller.insert("Artist1", "testSong1");

        controller.remove("Song", "testSong");
        controller.remove("Artist", "Artist");
        controller.insert("Artist", "testSong");

        assertEquals("|Artist| is added to the Artist database.\n"
            + "|testSong| is added to the Song database.\n"
            + "|Artist1| is added to the Artist database.\n"
            + "|testSong1| is added to the Song database.\n"
            + "|testSong| is removed from the Song database.\n"
            + "|Artist| is removed from the Artist database.\n"
            + "|Artist| is added to the Artist database.\n"
            + "|testSong| is added to the Song database.\n", systemOut()
                .getHistory());

        assertTrue(controller.getSizeOfTable("Artist") == 2);
        assertTrue(controller.getSizeOfTable("Song") == 2);
    }


    /**
     * Test print insert
     */
    public void testcontrollerPrint0() {
        controller.insert("testArtist", "testSong");
        controller.print("Artist");
        controller.print("Song");

        assertEquals("|testArtist| is added to the Artist database.\n"
            + "|testSong| is added to the Song database.\n"
            + "4: |testArtist|\n" + "total artists: 1\n" + "5: |testSong|\n"
            + "total songs: 1\n", systemOut().getHistory());

        assertTrue(controller.getSizeOfTable("Artist") == 1);
        assertTrue(controller.getSizeOfTable("Song") == 1);

    }


    /**
     * Test print remove
     */
    public void testcontrollerPrint1() {
        controller.insert("testArtist", "testSong");
        controller.insert("testArtist1", "testSong1");
        controller.remove("Artist", "testArtist");
        controller.remove("Song", "testSong");
        controller.print("Artist");
        controller.print("Song");

        assertEquals("|testArtist| is added to the Artist database.\n"
            + "|testSong| is added to the Song database.\n"
            + "|testArtist1| is added to the Artist database.\n"
            + "|testSong1| is added to the Song database.\n"
            + "|testArtist| is removed from the Artist database.\n"
            + "|testSong| is removed from the Song database.\n"
            + "4: TOMBSTONE\n" + "8: |testArtist1|\n" + "total artists: 1\n"
            + "4: |testSong1|\n" + "5: TOMBSTONE\n" + "total songs: 1\n",
            systemOut().getHistory());

        assertTrue(controller.getSizeOfTable("Artist") == 1);
        assertTrue(controller.getSizeOfTable("Song") == 1);

    }


    /**
     * Test print clear
     */
    public void testcontrollerPrint2() {
        controller.insert("testArtist", "testSong");
        controller.insert("testArtist1", "testSong1");

        controller.clearTables();
        controller.print("Artist");
        controller.print("Song");

        assertEquals("|testArtist| is added to the Artist database.\n"
            + "|testSong| is added to the Song database.\n"
            + "|testArtist1| is added to the Artist database.\n"
            + "|testSong1| is added to the Song database.\n"
            + "total artists: 0\n" + "total songs: 0\n", systemOut()
                .getHistory());

        assertTrue(controller.getSizeOfTable("Artist") == 0);
        assertTrue(controller.getSizeOfTable("Song") == 0);

    }


    /**
     * test invalid remove type
     */
    public void testInvalidRemove() {

        controller.remove("INVALID", "Songtest");

        assertEquals("Invalid type. Use Artist or Song.\n", systemOut()
            .getHistory());
    }


    /**
     * test invalid print type
     */
    public void testInvalidPrint() {

        controller.print("INVALID");

        assertEquals("Invalid type. Use Artist or Song or Graph.\n", systemOut()
            .getHistory());
    }


    /**
     * test invalid tableSize type
     */
    public void testInvalidSizeType() {
        controller.getSizeOfTable("INVALID");

        assertEquals("Invalid type. Use Artist or Song.\n", systemOut()
            .getHistory());
    }


    /**
     * Test insert with empty string values
     */
    public void testcontrollerInsertEmptyString() {
        controller.insert("", "");

        assertEquals("|| is added to the Artist database.\n"
            + "|| is added to the Song database.\n", systemOut().getHistory());

        assertTrue(controller.getSizeOfTable("Artist") == 1);
        assertTrue(controller.getSizeOfTable("Song") == 1);
    }


    /**
     * Test remove with empty string values
     */
    public void testcontrollerRemoveEmptyString() {
        controller.insert("testArtist", "testSong");
        controller.remove("Artist", "");
        controller.remove("Song", "");

        assertEquals("|testArtist| is added to the Artist database.\n"
            + "|testSong| is added to the Song database.\n"
            + "|| does not exist in the Artist database.\n"
            + "|| does not exist in the Song database.\n", systemOut()
                .getHistory());

        assertTrue(controller.getSizeOfTable("Artist") == 1);
        assertTrue(controller.getSizeOfTable("Song") == 1);
    }


    /**
     * Test remove artist and song and re-add them
     */
    public void testcontrollerRemoveAndReAdd() {
        controller.insert("testArtist", "testSong");
        controller.remove("Artist", "testArtist");
        controller.remove("Song", "testSong");
        controller.insert("testArtist", "testSong");

        assertEquals("|testArtist| is added to the Artist database.\n"
            + "|testSong| is added to the Song database.\n"
            + "|testArtist| is removed from the Artist database.\n"
            + "|testSong| is removed from the Song database.\n"
            + "|testArtist| is added to the Artist database.\n"
            + "|testSong| is added to the Song database.\n", systemOut()
                .getHistory());

        assertTrue(controller.getSizeOfTable("Artist") == 1);
        assertTrue(controller.getSizeOfTable("Song") == 1);
    }


    /**
     * Test clearTables on empty state
     */
    public void testcontrollerClearTablesEmpty() {
        controller.clearTables();
        controller.print("Artist");
        controller.print("Song");

        assertEquals("total artists: 0\n" + "total songs: 0\n", systemOut()
            .getHistory());

        assertTrue(controller.getSizeOfTable("Artist") == 0);
        assertTrue(controller.getSizeOfTable("Song") == 0);
    }


    /**
     * Test adding and removing multiple artists and songs
     */
    public void testcontrollerAddRemoveMultiple() {
        for (int i = 0; i < 10; i++) {
            controller.insert("artist" + i, "song" + i);
        }

        for (int i = 0; i < 10; i++) {
            controller.remove("Artist", "artist" + i);
            controller.remove("Song", "song" + i);
        }

        assertTrue(controller.getSizeOfTable("Artist") == 0);
        assertTrue(controller.getSizeOfTable("Song") == 0);
    }


    /**
     * Test print after multiple removals
     */
    public void testcontrollerPrintAfterRemovals() {
        controller.insert("testArtist", "testSong");
        controller.insert("testArtist1", "testSong1");
        controller.remove("Artist", "testArtist");
        controller.remove("Song", "testSong");
        controller.print("Artist");
        controller.print("Song");

        assertEquals("|testArtist| is added to the Artist database.\n"
            + "|testSong| is added to the Song database.\n"
            + "|testArtist1| is added to the Artist database.\n"
            + "|testSong1| is added to the Song database.\n"
            + "|testArtist| is removed from the Artist database.\n"
            + "|testSong| is removed from the Song database.\n"
            + "4: TOMBSTONE\n" + "8: |testArtist1|\n" + "total artists: 1\n"
            + "4: |testSong1|\n" + "5: TOMBSTONE\n" + "total songs: 1\n",
            systemOut().getHistory());
    }


    /**
     * Test print after inserting duplicate entries
     */
    public void testcontrollerPrintAfterDuplicates() {
        controller.insert("testArtist", "testSong");
        controller.insert("testArtist", "testSong");
        controller.print("Artist");
        controller.print("Song");

        assertEquals("|testArtist| is added to the Artist database.\n"
            + "|testSong| is added to the Song database.\n"
            + "|testArtist<SEP>testSong| duplicates a record "
            + "already in the database.\n" + "4: |testArtist|\n"
            + "total artists: 1\n" + "5: |testSong|\n" + "total songs: 1\n",
            systemOut().getHistory());
    }


    /**
     * Test print graph when no nodes are present
     */
    public void testcontrollerPrintGraphEmpty() {
        controller.print("Graph");

        assertEquals("there are 0 connected components\n"
            + "the largest connected component has 0 elements\n", systemOut()
                .getHistory());
    }


    /**
     * Test add and print graph with nodes
     */
    public void testcontrollerPrintGraphNodes() {
        controller.insert("artist1", "song1");
        controller.insert("artist2", "song2");
        controller.print("Graph");

        assertEquals("|artist1| is added to the Artist database.\n"
            + "|song1| is added to the Song database.\n"
            + "|artist2| is added to the Artist database.\n"
            + "|song2| is added to the Song database.\n"
            + "there are 2 connected components\n"
            + "the largest connected component has 2 elements\n", systemOut()
                .getHistory());
    }


    /**
     * Test add and remove graph nodes
     */
    public void testcontrollerGraphAddRemove() {
        controller.insert("artist1", "song1");
        controller.remove("Artist", "artist1");
        controller.remove("Song", "song1");
        controller.print("Graph");

        assertEquals("|artist1| is added to the Artist database.\n"
            + "|song1| is added to the Song database.\n"
            + "|artist1| is removed from the Artist database.\n"
            + "|song1| is removed from the Song database.\n"
            + "there are 0 connected components\n"
            + "the largest connected component has 0 elements\n", systemOut()
                .getHistory());
    }


    /**
     * Test clearTables and size after clearing
     */
    public void testcontrollerClearTablesAndSize() {
        for (int i = 0; i < 10; i++) {
            controller.insert("artist" + i, "song" + i);
        }
        controller.clearTables();

        assertTrue(controller.getSizeOfTable("Artist") == 0);
        assertTrue(controller.getSizeOfTable("Song") == 0);
    }


    /**
     * Test adding and removing elements with boundary values
     */
    public void testcontrollerBoundaryValues() {
        controller.insert("artistBoundary1", "songBoundary1");
        controller.remove("Artist", "artistBoundary1");
        controller.insert("artistBoundary2", "songBoundary2");
        controller.remove("Song", "songBoundary1");

        assertEquals("|artistBoundary1| is added to the Artist database.\n"
            + "|songBoundary1| is added to the Song database.\n"
            + "|artistBoundary1| is removed from the Artist database.\n"
            + "|artistBoundary2| is added to the Artist database.\n"
            + "|songBoundary2| is added to the Song database.\n"
            + "|songBoundary1| is removed from the Song database.\n",
            systemOut().getHistory());

        assertTrue(controller.getSizeOfTable("Artist") == 1);
        assertTrue(controller.getSizeOfTable("Song") == 1);
    }


    /**
     * Test invalid operation types
     */
    public void testcontrollerInvalidOperation() {
        controller.print("InvalidType");
        controller.remove("InvalidType", "invalidItem");
        controller.getSizeOfTable("InvalidType");

        assertEquals("Invalid type. Use Artist or Song or Graph.\n"
            + "Invalid type. Use Artist or Song.\n"
            + "Invalid type. Use Artist or Song.\n", systemOut().getHistory());
    }


    /**
     * Test inserting, removing and re-adding same artist and song
     */
    public void testcontrollerAddRemoveReAddSame() {
        controller.insert("testArtist", "testSong");
        controller.remove("Artist", "testArtist");
        controller.remove("Song", "testSong");
        controller.insert("testArtist", "testSong");

        assertEquals("|testArtist| is added to the Artist database.\n"
            + "|testSong| is added to the Song database.\n"
            + "|testArtist| is removed from the Artist database.\n"
            + "|testSong| is removed from the Song database.\n"
            + "|testArtist| is added to the Artist database.\n"
            + "|testSong| is added to the Song database.\n", systemOut()
                .getHistory());

        assertTrue(controller.getSizeOfTable("Artist") == 1);
        assertTrue(controller.getSizeOfTable("Song") == 1);
    }

}
