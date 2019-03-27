package maze;

import java.awt.Color;

public class DarkBlueWall extends Wall {

    public DarkBlueWall() {
        super();
    }

    @Override
	public Color getColor()
	{
	    return new Color(0, 51, 102);
    }
}