package org.wahlzeit.model.location;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartesianCoordinate extends AbstractCoordinate {
    public static String TABLENAME_X = "location_x";
    public static String TABLENAME_Y = "location_y";
    public static String TABLENAME_Z = "location_z";
    private double x;
    private double y;
    private double z;


    public CartesianCoordinate(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getZ() {
        return z;
    }

    private double getDistance(CartesianCoordinate other) {
        final double diffX = other.getX() - this.x;
        final double diffY = other.getY() - this.y;
        final double diffZ = other.getZ() - this.z;
        return Math.sqrt((diffX * diffX) + (diffY * diffY) + (diffZ * diffZ));
    }

    @Override
    public double getCartesianDistance(final ICoordinate coordinate) {
        return this.getDistance(coordinate.asCartesianCoordinate());
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return this;
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        double r = sqrtSumOfSquaredXSquaredYSquaredZ();
        double theta = r == 0 ? Double.MAX_VALUE: Math.acos(z / r);
        double phi = 1 / Math.tan(y / x);
        return new SphericCoordinate(phi, theta, r);
    }

    private double sqrtSumOfSquaredXSquaredYSquaredZ() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    @Override
    public void readFrom(final ResultSet rset) throws SQLException {
        this.x = rset.getDouble(CartesianCoordinate.TABLENAME_X);
        this.y = rset.getDouble(CartesianCoordinate.TABLENAME_Y);
        this.z = rset.getDouble(CartesianCoordinate.TABLENAME_Z);
    }

    @Override
    public void writeOn(final ResultSet rset) throws SQLException {
        rset.updateDouble(CartesianCoordinate.TABLENAME_X, this.getX());
        rset.updateDouble(CartesianCoordinate.TABLENAME_Y, this.getY());
        rset.updateDouble(CartesianCoordinate.TABLENAME_Z, this.getZ());
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(z);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "CartesianCoordinate{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
