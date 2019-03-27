package maze;

import java.awt.Color;

public class BrownDoor extends Door {

    public BrownDoor(String id, Room r1, Room r2) {
        super(id, r1, r2);
    }

    @Override
	public Color getColor()
	{
	    return new Color(101, 67, 33);
    }
}