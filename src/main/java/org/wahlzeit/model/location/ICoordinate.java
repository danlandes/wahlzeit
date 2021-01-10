package org.wahlzeit.model.location;

import org.wahlzeit.services.persistence.PersistenceWritable;

public interface ICoordinate
        extends Equality, CartesianFeatures, SphericFeatures, ConvertibleToCartesianCoordinate,
        ConvertibleToSphericCoordinate, PersistenceWritable {
}

interface Equality {
    boolean isEqual(ICoordinate coordinate);
}

interface CartesianFeatures {
    double getCartesianDistance(ICoordinate coordinate);
}

interface SphericFeatures {
    double getCentralAngle(ICoordinate coordinate);
}

interface ConvertibleToCartesianCoordinate {
    CartesianCoordinate asCartesianCoordinate();
}

interface ConvertibleToSphericCoordinate {
    SphericCoordinate asSphericCoordinate();
}
