package maze;

import maze.ui.MazeViewer;

import java.io.FileNotFoundException;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) {
        try {
            Maze maze;
            MazeGameCreator creator;
            if(args.length > 1) { //a file and a color
                switch(args[1]) {
                    case "red":
                        creator = new RedMazeGameCreator();
                        break;
                    case "blue":
                        creator = new BlueMazeGameCreator();
                        break;
                    default:
                        throw new ParseException("Color (2nd argument) must be either 'red' or 'blue'", 0);
                }
                maze = creator.makeMaze(args[0]);
            }
            else if(args.length > 0) { //just a file
                creator = new MazeGameCreator();
                maze = creator.makeMaze(args[0]);
            }
            else { //no arguments, just a regular maze
                creator = new MazeGameCreator();
                maze = creator.createMaze();
            }
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