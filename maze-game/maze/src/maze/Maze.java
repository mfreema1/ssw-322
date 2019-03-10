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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.text.ParseException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author Sunny
 * @version 1.0
 * @since 1.0
 */
public class Maze implements Iterable<Room>
{
    private final Map<Integer, Room> rooms = new HashMap<Integer, Room>();
    private final Map<String, Door> doors = new HashMap<>();
    private Room current;
    
    //keep track of the number of arguments each tag gets when parsing a file
    private static final Map<String, Integer> argMap = new HashMap<>() {{
        put("room", 6);
        put("door", 5);
    }};
	
	public Maze() {}

    //parse a ".maze" file and construct a maze from it
    public Maze(Scanner scanner) throws ParseException {
        int lineNumber = 0;
        ArrayList<String[]> contentsOfFile = new ArrayList<>();
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            StringTokenizer lineTokens = new StringTokenizer(line);
            lineNumber++;
            //it it isn't a blank line, go collect the tokens according to the type of object we are creating
            if(lineTokens.hasMoreTokens()) {
                try {
                    String objectType = lineTokens.nextToken();
                    Integer numberOfArguments = argMap.get(objectType);
                    String[] tokenArr;
                    if(numberOfArguments == null)
                        throw new ParseException("Object type '" + objectType + "' not understood on line: ", lineNumber);
                    else
                        tokenArr = gatherUpTokens(objectType, lineTokens, numberOfArguments);
                    if(lineTokens.hasMoreTokens())
                        throw new ParseException("Too many tokens found on line: ", lineNumber);
                    contentsOfFile.add(tokenArr);
                }
                catch(NoSuchElementException e) {
                    throw new ParseException("Too few tokens found on line: ", lineNumber);
                }
            }
        }
        //TODO: We can do much better than this, just a proof of concept now that I understand the problem
        for(String[] input : contentsOfFile) {
            if(input[0].equals("room"))
                initializeRoom(input);
            else
                initializeDoor(input);
        }
        for(String[] input : contentsOfFile) {
            if(input[0].equals("room"))
                fillInRoom(input);
        }
        System.out.println(rooms);
        System.out.println(doors);
        scanner.close();
    }

    //make a pass over all of the objects and just initialize all of the room instances
    private void initializeRoom(String[] arr) {
        if(arr[0].equals("room")) {
            Room room = new Room(Integer.parseInt(arr[1]));
            addRoom(room);
        }
    }

    //now go through all of the doors and link them to the rooms
    private void initializeDoor(String[] arr) {
        if(arr[0].equals("door")) {
            Door d = new Door(arr[1], rooms.get(Integer.parseInt(arr[2])), rooms.get(Integer.parseInt(arr[3])));
            if(arr[4].equals("open"))
                d.setOpen(true);
            addDoor(d);
        }
    }

    //finally, fill in the rooms now that we have references to the doors
    private void fillInRoom(String[] arr) {
        if(arr[0].equals("room")) {
            Room room = rooms.get(Integer.parseInt(arr[1]));
            //there's really no good way I can think of to do this with the direction changing
            //TODO: find a way to get rid of this Directions tidbit and simplify this logic
            Direction[] directions = new Direction[] { Direction.North, Direction.South, Direction.East, Direction.West };
            for(int i = 0; i < directions.length; i++) {
                if(arr[i + 2].equals("wall"))
                    room.setSide(directions[i], new Wall());
                //we assume all door ids start with a "d"
                else if(arr[i + 2].startsWith("d")) {
                    System.out.println(doors.get(arr[i+2]));
                    room.setSide(directions[i], doors.get(arr[i + 2]));
                }
                else
                    room.setSide(directions[i], rooms.get(Integer.parseInt(arr[i + 2])));
            }
        }
    }

    //journey through the tokens and place them into an array
    private String[] gatherUpTokens(String type, StringTokenizer tokens, int numberOfTokens) {
        String[] arr = new String[numberOfTokens];
        arr[0] = type;
        for(int i = 1; i < numberOfTokens; i++) {
            arr[i] = tokens.nextToken();
        }
        return arr;
    }

	public final void addRoom(final Room r)
	{
		rooms.put(r.getNumber(), r);
    }
    
    //keep track of all of our doors
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
