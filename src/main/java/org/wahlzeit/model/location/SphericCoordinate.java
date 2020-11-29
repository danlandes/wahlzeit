package org.wahlzeit.model.location;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SphericCoordinate implements ICoordinate {
    public static String TABLENAME_PHI = "location_PHI";
    public static String TABLENAME_THETA = "location_THETA";
    public static String TABLENAME_RADIUS = "location_radius";

    private double phi; // longitude: Angle between x-axis and vertical line between point and the area between x- and y-axis
    private double theta; // latitude: the Angle between the Z-Axsis and the the given line between sphere centre and point (Polar Angle)
    private double radius; // Distance between centre of sphere and point (usually radius)

    public SphericCoordinate(final double phi, final double theta, final double radius) {
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
    public boolean isEqual(final ICoordinate coordinate) {
        return this.asCartesianCoordinate().isEqual(coordinate);
    }

    @Override
    public double getCartesianDistance(final ICoordinate coordinate) {
        return this.asCartesianCoordinate().getCartesianDistance(coordinate);
    }

    @Override
    public double getCentralAngle(final ICoordinate coordinate) {
        final SphericCoordinate first = this;
        final SphericCoordinate second = coordinate.asSphericCoordinate();
        double deltaLongitude = Math.abs(first.getLongitude() - second.getLongitude());

        return   Math.acos(Math.sin(first.getLatitude()) * Math.sin(second.getLatitude())
                + Math.cos(first.getLatitude()) * Math.cos(second.getLatitude()) * Math.cos(Math.abs(deltaLongitude)));
    }

    private double sinSqr(double value) {
        return Math.pow(Math.sin(value), 2);
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        double x = rCosTheta() * Math.cos(phi);
        double y = rCosTheta() * Math.sin(phi);
        return new CartesianCoordinate(x, y, rCosTheta());
    }

    private double rCosTheta() {
        return radius * Math.cos(theta);
    }

    @Override
    public  SphericCoordinate asSphericCoordinate() {
        return this;
    }

    @Override
    public void readFrom(final ResultSet rset) throws SQLException {
        this.theta = rset.getDouble(SphericCoordinate.TABLENAME_THETA);
        this.radius = rset.getDouble(SphericCoordinate.TABLENAME_RADIUS);
        this.phi = rset.getDouble(SphericCoordinate.TABLENAME_PHI);
    }

    @Override
    public void writeOn(final ResultSet rset) throws SQLException {
        rset.updateDouble(SphericCoordinate.TABLENAME_THETA, this.getTheta());
        rset.updateDouble(SphericCoordinate.TABLENAME_RADIUS, this.getRadius());
        rset.updateDouble(SphericCoordinate.TABLENAME_RADIUS, this.getRadius());
    }
}
