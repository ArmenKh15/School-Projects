import java.io.File;
import java.util.Scanner;

/**
 * CommandProcessor Class
 * Declaration of file and controller
 * 
 * @author {Armen Khachatryan}
 * @version {09/13/2024}
 */
public class CommandProcessor {

    private String file; // File name
    private Controller controller; // controller

    /**
     * CommandProcessor Constructor
     * 
     * @param controller
     *            controller object
     * @param filename
     *            name of input file
     */
    public CommandProcessor(Controller controller, String filename) {
        this.controller = controller;
        this.file = filename;
    }


    /**
     * readCommands Method:
     * Goes through input file with commands and runs valid commands through
     * controller
     */
    public void readCommands() {
        try {
            Scanner sc = new Scanner(new File(file));
            Scanner scancmd; // Declare scanner for line parsing

            while (sc.hasNextLine()) {
                String line = sc.nextLine(); // Get the next line
                scancmd = new Scanner(line); // Create a scanner for this line
                String cmd = scancmd.next().trim(); // Get the first word (the
                                                    // command)

                String type;

                switch (cmd) {
                    case "insert":
                        scancmd.useDelimiter("<SEP>"); // Change delimiter for
                                                       // insert command
                        String artist = scancmd.next().trim(); // Get artist
                        String song = scancmd.next().trim(); // Get song title
                        controller.insert(artist, song);
                        break;

                    case "remove":
                        type = scancmd.next().trim(); // Get the mode of
                                                      // deletion
                        // (artist/song)
                        String token = scancmd.nextLine().trim(); // Get the
                                                                  // rest of the
                                                                  // line for
                                                                  // the name
                        switch (type) {
                            case "artist":
                                controller.remove("Artist", token);
                                break;
                            case "song":
                                controller.remove("Song", token);
                                break;
                            default:
                                System.out.println("Error: bad remove type "
                                    + type);
                                break;
                        }
                        break;

                    case "print":
                        type = scancmd.next().trim(); // Get the type of print
                                                      // command
                        switch (type) {
                            case "artist":
                                controller.print("Artist");
                                break;
                            case "song":
                                controller.print("Song");
                                break;
                            case "graph":
                                controller.print("Graph");
                                break;
                            default:
                                System.out.println("Error: bad print type "
                                    + type);
                                break;
                        }
                        break;

                    default:
                        break;
                }
            }
            sc.close(); // Close the scanner
        }
        catch (Exception e) {
            System.out.println("An error occurred: file could not be found");
            return;
        }
    }
}
