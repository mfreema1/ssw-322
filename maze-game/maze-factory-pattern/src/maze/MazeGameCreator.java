package maze;

import java.io.File;
import java.text.ParseException;
import java.io.FileNotFoundException;
import java.util.*;

public class MazeGameCreator {

    //keep track of which object types we accept in a .maze file as well as how many
    //arguments each of these types accepts
    protected Maze maze;
    private static final Map<String, Integer> argMap = new HashMap<>() {{
        put("room", 5);
        put("door", 4);
    }};

    public MazeGameCreator() {
        maze = new Maze();
    }

    public Maze createMaze() {
        Room first = makeRoom(0);
        Room second = makeRoom(1);
        Door door = makeDoor("d0", first, second);
        //first room to the left
        first.setSide(Direction.North, makeWall());
        first.setSide(Direction.East, door);
        first.setSide(Direction.South, makeWall());
        first.setSide(Direction.West, makeWall());
        //second room to the right
        second.setSide(Direction.North, makeWall());
        second.setSide(Direction.East, makeWall());
        second.setSide(Direction.South, makeWall());
        second.setSide(Direction.West, door);
        maze.addRoom(first);
        maze.addRoom(second);
        maze.addDoor(door);
        maze.setCurrentRoom(first);
		return maze;
	}

    public Maze makeMaze(final String path) throws ParseException, FileNotFoundException {
        File file = new File(path);
        if(file.exists())
            return parseMaze(new Scanner(file));
        else
            throw new FileNotFoundException();
    }
    
    /**
     * Construct a maze from a ".maze" file
     */
    public Maze parseMaze(Scanner scanner) throws ParseException {
        int lineNumber = 0;
        //We only have two object types, so I didn't pull it out into a map, but if more objects come along,
        //may want to do that
        ArrayList<String[]> parsedRooms = new ArrayList<>();
        ArrayList<String[]> parsedDoors = new ArrayList<>();
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            StringTokenizer lineTokens = new StringTokenizer(line);
            lineNumber++;
            //it it isn't a blank line, go collect the tokens according to the type of object we are creating
            if(lineTokens.hasMoreTokens()) {
                try {
                    String objectType = lineTokens.nextToken();
                    Integer numberOfArguments = argMap.get(objectType);
                    if(numberOfArguments == null)
                        throw new ParseException("Object type '" + objectType + "' not understood on line: ", lineNumber);
                    if(objectType.equals("room"))
                        parsedRooms.add(gatherUpTokens(lineTokens, numberOfArguments));
                    if(objectType.equals("door"))
                        parsedDoors.add(gatherUpTokens(lineTokens, numberOfArguments));
                }
                catch(NoSuchElementException e) {
                    throw new ParseException("Too few tokens found on line: ", lineNumber);
                }
            }
        }
        //TODO: Perhaps we can polymorph this behavior?
        //firstly, go and start creating the rooms, then make the doors and finish creating the rooms.  We do it as
        //such because of the dependencies between the two objects.  The rooms may need references to doors, but not when
        //being constructed.  Doors do need references to rooms at the constructor call, so the current solution is to
        //partially make the rooms, make the doors, and then go back and fill the rooms in with the doors.
        for(String[] input : parsedRooms)
            initializeRoom(input);
        for(String[] input : parsedDoors)
            initializeDoor(input);
        for(String[] input : parsedRooms)
            fillInRoom(input);
        maze.setCurrentRoom(0); //set to first room created, this is arbitrary for right now
        scanner.close();
        return maze;
    }

    /**
     * Begin the creation of a set of rooms.  The rooms cannot be fully created because we do not yet have
     * references to the doors that the room may need to reference
     */
    private void initializeRoom(String[] arr) {
        Room room = makeRoom(Integer.parseInt(arr[0]));
        maze.addRoom(room);
    }

    /**
     * Create a door instance from a line of file input
     */
    private void initializeDoor(String[] arr) {
        //note that a door only has 4 arguments
        Door d = makeDoor(arr[0], maze.getRoom(Integer.parseInt(arr[1])), maze.getRoom(Integer.parseInt(arr[2])));
        if(arr[3].equals("open"))
            d.setOpen(true);
        maze.addDoor(d);
    }

    /**
     * After the rooms and doors have been initialized, finalize the creation of the rooms by setting
     * their sides
     */
    private void fillInRoom(String[] arr) {
        Room room = maze.getRoom(Integer.parseInt(arr[0]));
        Direction[] directions = Direction.values();
        //the directions begin at index 1 and go to index 4, just lining them up in this loop
        for(int i = 0; i < directions.length; i++) {
            String token = arr[i + 1];
            if(token.equals("wall"))
                room.setSide(directions[i], makeWall());
            else if(token.startsWith("d")) //we assume all door ids start with a "d"
                room.setSide(directions[i], maze.getDoor(token));
            else
                room.setSide(directions[i], maze.getRoom(Integer.parseInt(token)));
        }
    }

    /**
     * Take a line from a file input and parse it into an array of the arguments on that line.  We do this to
     * avoid having to iterate over the parsed file multiple times
     */
    private String[] gatherUpTokens(StringTokenizer tokens, int numberOfTokens) {
        String[] arr = new String[numberOfTokens];
        for(int i = 0; i < numberOfTokens; i++) {
            arr[i] = tokens.nextToken();
        }
        return arr;
    }

    public Wall makeWall() {
        return new Wall();
    }

    public Door makeDoor(String id, Room r1, Room r2) {
        return new Door(id, r1, r2);
    }

    public Room makeRoom(int id) {
        return new Room(id);
    }
}