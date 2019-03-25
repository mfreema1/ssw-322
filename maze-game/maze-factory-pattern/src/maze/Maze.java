/*
 * Maze.java
 * Copyright (c) 2008, Drexel University.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the Drexel University nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY DREXEL UNIVERSITY ``AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL DREXEL UNIVERSITY BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package maze;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.text.ParseException;
import java.util.StringTokenizer;
import java.util.ArrayList;

/**
 *
 * @author Sunny
 * @version 1.0
 * @since 1.0
 */
public class Maze implements Iterable<Room>
{
    private final Map<Integer, Room> rooms = new HashMap<>();
    private final Map<String, Door> doors = new HashMap<>();
    private Room current;
    
    //keep track of which object types we accept in a .maze file as well as how many
    //arguments each of these types accepts
    private static final Map<String, Integer> argMap = new HashMap<>() {{
        put("room", 5);
        put("door", 4);
    }};
	
	public Maze() {}

    /**
     * Construct a maze from a ".maze" file
     */
    public Maze(Scanner scanner) throws ParseException {
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
        setCurrentRoom(0); //set to first room created, this is arbitrary for right now
        scanner.close();
    }

    /**
     * Begin the creation of a set of rooms.  The rooms cannot be fully created because we do not yet have
     * references to the doors that the room may need to reference
     */
    private void initializeRoom(String[] arr) {
        Room room = new Room(Integer.parseInt(arr[0]));
        addRoom(room);
    }

    /**
     * Create a door instance from a line of file input
     */
    private void initializeDoor(String[] arr) {
        //note that a door only has 4 arguments
        Door d = new Door(arr[0], rooms.get(Integer.parseInt(arr[1])), rooms.get(Integer.parseInt(arr[2])));
        if(arr[3].equals("open"))
            d.setOpen(true);
        addDoor(d);
    }

    /**
     * After the rooms and doors have been initialized, finalize the creation of the rooms by setting
     * their sides
     */
    private void fillInRoom(String[] arr) {
        Room room = rooms.get(Integer.parseInt(arr[0]));
        Direction[] directions = Direction.values();
        //the directions begin at index 1 and go to index 4, just lining them up in this loop
        for(int i = 0; i < directions.length; i++) {
            String token = arr[i + 1];
            if(token.equals("wall"))
                room.setSide(directions[i], new Wall());
            else if(token.startsWith("d")) //we assume all door ids start with a "d"
                room.setSide(directions[i], doors.get(token));
            else
                room.setSide(directions[i], rooms.get(Integer.parseInt(token)));
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

	public final void addRoom(final Room r)
	{
		rooms.put(r.getNumber(), r);
    }
    
    public final void addDoor(final Door d) {
        doors.put(d.getId(), d);
    }

	public final Room getRoom(int number)
	{
		return rooms.get(number);
	}
	
	@Override
	public Iterator<Room> iterator()
	{
		return rooms.values().iterator();
	}

	public int getNumberOfRooms()
	{
		return rooms.size();
	}

	public final Room getCurrentRoom()
	{
		return current;
	}

	public final void setCurrentRoom(final Room room)
	{
		current = room;
	}
	
	public final void setCurrentRoom(int number)
	{
		current = rooms.get(number);
	}
}
