package org.wahlzeit.model.location;

import java.sql.ResultSet;

public class Location {
    private ICoordinate coordinate;

    public Location(CartesianCoordinate coordinate) {
        this.coordinate = coordinate;
    }

    public ICoordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(final CartesianCoordinate coordinate) {
        this.coordinate = coordinate;
    }

    public void writeOn(ResultSet resultSet) {

    }
}
