package maze;

public class BlueMazeGameCreator extends MazeGameCreator {

    @Override
    public Wall makeWall() {
        return new DarkBlueWall();
    }

    @Override
    public Door makeDoor(String id, Room r1, Room r2) {
        return new BrownDoor(id, r1, r2);
    }

    @Override
    public Room makeRoom(int num) {
        return new LightBlueRoom(num);
    }
}