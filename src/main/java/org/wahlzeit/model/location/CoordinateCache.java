package org.wahlzeit.model.location;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;

import java.util.HashMap;

public class CoordinateCache {
    private static final HashMap<Triple<Double, Double, Double>, CartesianCoordinate> store = new HashMap();

    public static CartesianCoordinate getForCartesian(final double x, final double y, final double z) {
        final Triple<Double, Double, Double> values = new ImmutableTriple(x, y, z);
        final CartesianCoordinate result = CoordinateCache.store.get(values);
        if (result == null) {
            final CartesianCoordinate created = new CartesianCoordinate(values.getLeft(), values.getMiddle(), values.getRight());
            CoordinateCache.store.put(values, created);
            return created;
        }
        return result;
    }

    public static SphericCoordinate getAsSpheric(final double phi, final double theta, final double radius) {
        // this reuses the the conversion between spheric and cartesian of the cost of an unnecessary object creation
        // could be improved by making the calculation betwenn spheric and cartesian reusable outside of coordinate
        // for the moment I dont want to share this implementation freely so I go with this implementation
        final CartesianCoordinate asCartesian = new SphericCoordinate(phi, theta, radius).asCartesianCoordinate();
        return CoordinateCache.getForCartesian(asCartesian.getX(), asCartesian.getY(), asCartesian.getZ()).asSphericCoordinate();
    }
}
