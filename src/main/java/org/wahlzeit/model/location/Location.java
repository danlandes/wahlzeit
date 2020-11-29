package org.wahlzeit.model.location;

public class Location {
    private CartesianCoordinate coordinate;

    public Location(final double x, final double y, final double z) {
        this.coordinate = new CartesianCoordinate(x, y, z);
    }

    public CartesianCoordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(final CartesianCoordinate coordinate) {
        this.coordinate = coordinate;
    }
}
