package org.wahlzeit.model.location;

import org.wahlzeit.services.persistence.PersistenceUtils;
import org.wahlzeit.services.persistence.SimplePersistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.wahlzeit.model.location.AssertionUtils.assertCartesianCoordinateIsValid;
import static org.wahlzeit.model.location.AssertionUtils.assertCoordinateIsSupportedSubtype;
import static org.wahlzeit.model.location.AssertionUtils.assertNotNull;
import static org.wahlzeit.model.location.AssertionUtils.assertSphericCoordinateIsValid;

public class Location implements SimplePersistence {
    private ICoordinate coordinate;

    public Location(CartesianCoordinate coordinate) {
        assertNotNull(coordinate, "Coordinate should not be null!");
        this.coordinate = coordinate;
    }

    public Location(SphericCoordinate coordinate) {
        this.coordinate = coordinate;
    }

    public ICoordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(final CartesianCoordinate coordinate) {
        assertNotNull(coordinate, "Coordinate should not be null!");
        this.coordinate = coordinate;
    }

    @Override
    public void writeOn(ResultSet resultSet) throws SQLException {
        assertNotNull(resultSet, "Can not writeOn resultSet: null");
        this.coordinate.writeOn(resultSet);
    }

    @Override
    public void readFrom(final ResultSet rset) throws SQLException {
        assertNotNull(rset, "Can not writeOn resultSet: null");
        if (PersistenceUtils.assertColumnIsPresent(CartesianCoordinate.TABLENAME_X, rset)) {
            this.coordinate = new CartesianCoordinate(0, 0, 0);
            this.coordinate.readFrom(rset);
            assertCartesianCoordinateIsValid(coordinate);
            assertCoordinateIsSupportedSubtype(coordinate);
        } else if (PersistenceUtils.assertColumnIsPresent(SphericCoordinate.TABLENAME_PHI, rset)) {
            this.coordinate = new SphericCoordinate(0, 0, 0);
            this.coordinate.readFrom(rset);
            assertSphericCoordinateIsValid(coordinate);
            assertCoordinateIsSupportedSubtype(coordinate);
        } else {
            throw new SQLException("Could not extract Location");
        }
    }
}
