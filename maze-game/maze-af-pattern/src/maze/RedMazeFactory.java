package maze;

public class RedMazeFactory extends MazeFactory {

    @Override
    public Wall makeWall() {
        return new RedWall();
    }

    @Override
    public Door makeDoor(String id, Room r1, Room r2) {
        return new GreenDoor(id, r1, r2);
    }

    @Override
    public Room makeRoom(int num) {
        return new PinkRoom(num);
    }
}