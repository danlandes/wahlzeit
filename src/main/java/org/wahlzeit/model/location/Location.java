package org.wahlzeit.model.location;

import org.wahlzeit.services.persistence.PersistenceUtils;
import org.wahlzeit.services.persistence.SimplePersistence;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Location implements SimplePersistence {
    private ICoordinate coordinate;

    public Location(CartesianCoordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Location(SphericCoordinate coordinate) {
        this.coordinate = coordinate;
    }

    public ICoordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(final CartesianCoordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public void writeOn(ResultSet resultSet) throws SQLException {
        this.coordinate.writeOn(resultSet);
    }

    @Override
    public void readFrom(final ResultSet rset) throws SQLException {
        if (PersistenceUtils.assertColumnIsPresent(CartesianCoordinate.TABLENAME_X, rset)) {
            this.coordinate = new CartesianCoordinate(0, 0, 0);
            this.coordinate.readFrom(rset);
        } else if (PersistenceUtils.assertColumnIsPresent(SphericCoordinate.TABLENAME_PHI, rset)) {
            this.coordinate = new SphericCoordinate(0, 0, 0);
            this.coordinate.readFrom(rset);
        } else {
            this.coordinate = null;
        }
    }
}
