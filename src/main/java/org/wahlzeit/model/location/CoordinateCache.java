package org.wahlzeit.model.location;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;

import java.util.HashMap;

public class CoordinateCache {
    private static final HashMap<Triple<Double, Double, Double>, CartesianCoordinate> cartesians = new HashMap();
    private static final HashMap<Triple<Double, Double, Double>, SphericCoordinate> spheric = new HashMap();

    public static CartesianCoordinate getForCartesian(final double x, final double y, final double z) {
        final Triple<Double, Double, Double> values = new ImmutableTriple(x, y, z);
        final CartesianCoordinate result = CoordinateCache.cartesians.get(values);
        if (result == null) {
            final CartesianCoordinate created =
                    new CartesianCoordinate(values.getLeft(), values.getMiddle(), values.getRight());
            CoordinateCache.cartesians.put(values, created);
            return created;
        }
        return result;
    }

    public static SphericCoordinate getAsSpheric(final double phi, final double theta, final double radius) {
        return genericGet(CoordinateCache.spheric, phi, theta, radius);
    }

    private static SphericCoordinate genericGet(
            final HashMap spheric, final double left, final double middle, final double right) {
        final Triple<Double, Double, Double> values = new ImmutableTriple(left, middle, right);
        final SphericCoordinate result = CoordinateCache.spheric.get(values);
        if (result == null) {
            final SphericCoordinate created =
                    new SphericCoordinate(values.getLeft(), values.getMiddle(), values.getRight());
            CoordinateCache.spheric.put(values, created);
            return created;
        }
        return result;
    }
}
