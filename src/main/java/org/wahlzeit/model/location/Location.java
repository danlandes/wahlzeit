package org.wahlzeit.model.location;

import org.wahlzeit.model.location.errors.LocationStateNotValid;
import org.wahlzeit.model.location.errors.PersistenceErrors;
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

    public Location(AbstractCoordinate coordinate) {
        assertNotNull(coordinate, new LocationStateNotValid.CoordinateWasNull());
        assertCoordinateIsSupportedSubtype(coordinate);
        this.coordinate = coordinate;
    }


    public ICoordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(final AbstractCoordinate coordinate) {
        assertNotNull(coordinate, "Coordinate should not be null!");
        assertCoordinateIsSupportedSubtype(coordinate);
        this.coordinate = coordinate;
    }

    @Override
    public void writeOn(ResultSet resultSet) throws SQLException {
        assertNotNull(resultSet, new PersistenceErrors.ResultSetIsNull.OfLocation());
        this.coordinate.writeOn(resultSet);
    }

    @Override
    public void readFrom(final ResultSet rset) throws SQLException {
        assertNotNull(rset, new PersistenceErrors.ResultSetIsNull.OfLocation());
        if (PersistenceUtils.assertColumnIsPresent(CartesianCoordinate.TABLENAME_X, rset)) {
            final double x = rset.getDouble(CartesianCoordinate.TABLENAME_X);
            final double y = rset.getDouble(CartesianCoordinate.TABLENAME_Y);
            final double z = rset.getDouble(CartesianCoordinate.TABLENAME_Z);
            this.coordinate = new CartesianCoordinate(x, y, z);
            assertCartesianCoordinateIsValid(coordinate);
        } else if (PersistenceUtils.assertColumnIsPresent(SphericCoordinate.TABLENAME_PHI, rset)) {
            final double theta = rset.getDouble(SphericCoordinate.TABLENAME_THETA);
            final double radius = rset.getDouble(SphericCoordinate.TABLENAME_RADIUS);
            final double phi = rset.getDouble(SphericCoordinate.TABLENAME_PHI);
            this.coordinate = new SphericCoordinate(phi, theta, radius);
            assertSphericCoordinateIsValid(coordinate);
        } else {
            throw new SQLException("Could not extract Location");
        }
    }
}
