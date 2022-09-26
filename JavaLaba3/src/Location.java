/**
 * This class represents a specific location in a 2D map. Coordinates are
 * integer values.
 **/
public class Location {
    /** X coordinate of this location. **/
    public int xCoord;

    /** Y coordinate of this location. **/
    public int yCoord;

    /** Creates a new location with the specified integer coordinates. **/
    public Location(int x, int y) {
        xCoord = x;
        yCoord = y;
    }

    /** Creates a new location with coordinates (0, 0). **/
    public Location() {
        this(0, 0);
    }

    /** Return true if Location equals **/
    public boolean equals(Object obj) {

        if (obj instanceof Location) {
            Location loc = (Location) obj;
            return (xCoord == loc.xCoord && yCoord == loc.yCoord) ? true : false;
        }
        return false;
    }

    /** Provides a hashCode for each Location **/
    public int hashCode() {
        // some prime value
        int result = 13;
        // use another prime value
        result = 11 * result + xCoord;
        result = 11 * result + yCoord;

        return result;
    }
}