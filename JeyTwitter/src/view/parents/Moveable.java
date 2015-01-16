package view.parents;

import java.awt.Point;

import javax.swing.ImageIcon;

public interface Moveable {

	public Point getInitialClick();

	public void setInitialClick(Point initialClick);

	public Point getLastPosition();

	public void setLastPosition(Point lastPosition);
	
	public Point getLocation();

	public void setLocation(Point currentLocation);

	public boolean isDisposeWindow();

	public void setVisible(boolean b);

	public void dispose();

	public void setImagenIconos(ImageIcon imageIcon);
	
	public void setOpacity(float op);

	public int getHeight();

	public void setExtendedState(int iconified);

	public void setLocation(int x, int y);
}
