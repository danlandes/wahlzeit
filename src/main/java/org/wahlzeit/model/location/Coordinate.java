package org.wahlzeit.model.location;

public class Coordinate {
    public static String TABLENAME_X = "location_x";
    public static String TABLENAME_Y = "location_y";
    public static String TABLENAME_Z = "location_z";
    private double x;

    public Coordinate(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    private double y;
    private double z;
    private static final double toleratedDeviation = 5;

    public double getX() {
        return x;
    }

    public void setX(final double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(final double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(final double z) {
        this.z = z;
    }

    public double getDistance(Coordinate other) {
        final double diffX = other.x - this.x;
        final double diffY = other.y - this.y;
        final double diffZ = other.z - this.z;
        return Math.sqrt((diffX * diffX) + (diffY * diffY) + (diffZ * diffZ));
    }

    @Override
    public boolean equals(final Object other) {
        if (other == null || getClass() != other.getClass()) return false;
        final Coordinate that = (Coordinate) other;
       return isEqual(that);
    }

    public boolean isEqual(final Coordinate other) {
        if (this == other) return true;
        return this.getDistance(other) <= toleratedDeviation;
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
}
