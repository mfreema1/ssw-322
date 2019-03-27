package maze;

import maze.ui.MazeViewer;

import java.io.FileNotFoundException;
import java.text.ParseException;

public class MazeGame {

    public static void main(String[] args) {
        try {
            MazeFactory factory;
            if(args.length < 2)
                throw new ParseException("Must supply a file and a color for abstract factory", 0);
            switch(args[1]) {
                case "red":
                    factory = new RedMazeFactory();
                    break;
                case "blue":
                    factory = new BlueMazeFactory();
                    break;
                default:
                    throw new ParseException("Invalid color '" + args[1] + "' given", 0);
            }
            MazeViewer viewer = new MazeViewer(createMaze(factory, args[0]));
            viewer.run();
        }
        catch(ParseException e) {
            System.out.println(e.getMessage() + e.getErrorOffset());
        }
        catch(FileNotFoundException e) {
            System.out.println("File " + args[0] + " not found");
        }
    }

    public static Maze createMaze(MazeFactory factory, String path) throws ParseException, FileNotFoundException {
        return factory.makeMaze(path);
    }
}