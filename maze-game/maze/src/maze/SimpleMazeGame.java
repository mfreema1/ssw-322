/*
 * SimpleMazeGame.java
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

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.io.File;
import java.util.Scanner;
import maze.ui.MazeViewer;

/**
 * 
 * @author Sunny
 * @version 1.0
 * @since 1.0
 */
public class SimpleMazeGame
{
	/**
	 * Creates a small maze if no input file is provided
	 */
	public static Maze createMaze()
	{
        Maze maze = new Maze();
        Room first = new Room(0);
        Room second = new Room(1);
        Door door = new Door("d0", first, second);
        //first room to the left
        first.setSide(Direction.North, new Wall());
        first.setSide(Direction.East, door);
        first.setSide(Direction.South, new Wall());
        first.setSide(Direction.West, new Wall());
        //second room to the right
        second.setSide(Direction.North, new Wall());
        second.setSide(Direction.East, new Wall());
        second.setSide(Direction.South, new Wall());
        second.setSide(Direction.West, door);
        maze.addRoom(first);
        maze.addRoom(second);
        maze.addDoor(door);
        maze.setCurrentRoom(first);
		return maze;
	}

    /**
     * Start the file loading process for a maze and pass exceptions over to main to handle
     */
	public static Maze loadMaze(final String path) throws ParseException, FileNotFoundException
	{
        File file = new File(path);
        if(file.exists())
            return new Maze(new Scanner(file));
        else
            throw new FileNotFoundException();
	}

	public static void main(String[] args)
	{
        try {
            Maze maze;
            if(args.length > 0) //if we are supplied a file, load it
                maze = loadMaze(args[0]);
            else
                maze = createMaze();
            MazeViewer viewer = new MazeViewer(maze);
            viewer.run();
        }
        catch(ParseException e) {
            System.out.println(e.getMessage() + e.getErrorOffset());
        }
        catch(FileNotFoundException e) {
            System.out.println("File " + args[0] + " not found");
        }
	}
}
