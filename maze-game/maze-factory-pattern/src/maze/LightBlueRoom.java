package maze;

import java.awt.Color;

public class LightBlueRoom extends Room {

    public LightBlueRoom(int num) {
        super(num);
    }

    @Override
	public Color getColor()
	{
	    return new Color(173, 216, 230);
    }
}