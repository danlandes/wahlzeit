package org.wahlzeit.model.location;

import org.wahlzeit.model.location.errors.PersistenceErrors;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.wahlzeit.model.location.AssertionUtils.assertArgumentsOfSphericCoordinate;
import static org.wahlzeit.model.location.AssertionUtils.assertNotNull;
import static org.wahlzeit.model.location.AssertionUtils.assertShouldBePositive;
import static org.wahlzeit.model.location.AssertionUtils.assertValidCoordinateArguments;
import static org.wahlzeit.model.location.AssertionUtils.assertValueIsIn360DegreeSpectrum;

public class SphericCoordinate extends AbstractCoordinate {
    public static String TABLENAME_PHI = "location_phi";
    public static String TABLENAME_THETA = "location_theta";
    public static String TABLENAME_RADIUS = "location_radius";

    private double phi; // longitude: Angle between x-axis and vertical line between point and the area between x- and y-axis
    private double theta; // latitude: the Angle between the Z-Axsis and the the given line between sphere centre and point (Polar Angle)
    private double radius; // Distance between centre of sphere and point (usually radius)

    public SphericCoordinate(final double phi, final double theta, final double radius) {
        assertArgumentsOfSphericCoordinate(phi, theta, radius);
        this.phi = phi;
        this.theta = theta;
        this.radius = radius;
    }

    public double getPhi() { return phi; }
    public double getTheta() { return theta; }
    public double getRadius() { return radius; }
    public double getLatitude() { return theta; }
    public double getLongitude() { return phi; }

    @Override
    public double getCentralAngle(final ICoordinate coordinate) {
        assertNotNull(coordinate, "Can not calculate central angle of null");
        final SphericCoordinate first = this;
        final SphericCoordinate second = coordinate.asSphericCoordinate();
        double deltaLongitude = Math.abs(first.getLongitude() - second.getLongitude());

        final double result = Math.acos(Math.sin(first.getLatitude()) * Math.sin(second.getLatitude())
                + Math.cos(first.getLatitude()) * Math.cos(second.getLatitude()) * Math.cos(Math.abs(deltaLongitude)));
        assertValueIsIn360DegreeSpectrum(result);
        return result;
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        assertShouldBePositive(radius);
        double x = rCosTheta() * Math.cos(phi);
        double y = rCosTheta() * Math.sin(phi);
        return new CartesianCoordinate(x, y, rCosTheta());
    }

    private double rCosTheta() {
        assertShouldBePositive(radius);
        return radius * Math.cos(theta);
    }

    @Override
    public  SphericCoordinate asSphericCoordinate() {
        return this;
    }

    @Override
    public void readFrom(final ResultSet rset) throws SQLException {
        assertNotNull(rset, new PersistenceErrors.ResultSetIsNull.OfCoordinateResult());
        this.theta = rset.getDouble(SphericCoordinate.TABLENAME_THETA);
        this.radius = rset.getDouble(SphericCoordinate.TABLENAME_RADIUS);
        this.phi = rset.getDouble(SphericCoordinate.TABLENAME_PHI);
    }

    @Override
    public void writeOn(final ResultSet rset) throws SQLException {
        assertNotNull(rset, new PersistenceErrors.ResultSetIsNull.OfCoordinateResult());
        rset.updateDouble(SphericCoordinate.TABLENAME_THETA, this.getTheta());
        rset.updateDouble(SphericCoordinate.TABLENAME_RADIUS, this.getRadius());
        rset.updateDouble(SphericCoordinate.TABLENAME_RADIUS, this.getRadius());
    }

    @Override
    public String toString() {
        return "SphericCoordinate{" +
                "phi=" + phi +
                ", theta=" + theta +
                ", radius=" + radius +
                '}';
    }
}
