package maze;

import java.awt.Color;

public class GreenDoor extends Door {

    public GreenDoor(String id, Room r1, Room r2) {
        super(id, r1, r2);
    }

    @Override
	public Color getColor()
	{
		return Color.GREEN;
    }
}