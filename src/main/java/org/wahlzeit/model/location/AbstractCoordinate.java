package org.wahlzeit.model.location;

abstract class AbstractCoordinate implements ICoordinate {
    private static final double toleratedDeviation = 5;

    @Override
    public boolean isEqual(final ICoordinate coordinate) {
        if (this == coordinate) return true;
        // a location is equal when the distance between them is lower than the tolerated deviation
        return this.getCartesianDistance(coordinate.asCartesianCoordinate()) <= toleratedDeviation;
    }

    @Override
    public boolean equals(final Object other) {
        if (other == null || getClass() != other.getClass()) return false;
        final CartesianCoordinate that = (CartesianCoordinate) other;
        return isEqual(that);
    }

    @Override
    public double getCartesianDistance(final ICoordinate coordinate) {
        return coordinate.asCartesianCoordinate().getCartesianDistance(coordinate);
    }

    @Override
    public double getCentralAngle(final ICoordinate coordinate) {
        return coordinate.asSphericCoordinate().getCentralAngle(coordinate);
    }
}
