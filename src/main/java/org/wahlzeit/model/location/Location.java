package org.wahlzeit.model.location;

public class Location {
    private Coordinate coordinate;

    public Location(final double x, final double y, final double z) {
        this.coordinate = new Coordinate(x, y, z);
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(final Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
