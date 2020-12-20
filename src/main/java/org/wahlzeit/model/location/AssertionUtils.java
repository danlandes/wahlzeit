package org.wahlzeit.model.location;

import java.util.Arrays;

public class AssertionUtils {

    protected static void assertSphericCoordinateIsValid(final ICoordinate coordinate) {
        assertNotNull(coordinate, "null is not a valid SphericCoordinate!");
        if (!isSphericCoordinate(coordinate)) {
            throw new IllegalStateException("Coordinate should be SphericCoordinate");
        }
    }

    protected static void assertCartesianCoordinateIsValid(final ICoordinate coordinate) {
        assertNotNull(coordinate, "null is not a valid CartesianCoordinate!");
        if (!isCartesianCoordinate(coordinate)) {
            throw new IllegalStateException("Coordinate should be CartesianCoordinate");
        }
    }

    protected static void assertNotNull(final Object coordinate, String message) {
        if (coordinate == null) {
            throw new IllegalArgumentException(message);
        }
    }

    protected static void assertCoordinateIsSupportedSubtype(ICoordinate coordinate) {
        if (isCartesianCoordinate(coordinate) || isSphericCoordinate(coordinate)) {
            return;
        }
        throw new IllegalStateException("You introduced a new CoordinateClass that is currently not supported by database!");
    }

    private static boolean isCartesianCoordinate(final ICoordinate coordinate) {
        return coordinate instanceof CartesianCoordinate;
    }

    private static boolean isSphericCoordinate(final ICoordinate coordinate) {
        return coordinate instanceof SphericCoordinate;
    }

    protected static void assertNotInfinity(double number) {
        if (Double.isInfinite(number)) {
            throw new IllegalArgumentException("value should not be infinity");
        }
    }

    protected static void assertValidNumber(double number) {
        if (Double.isNaN(number)) {
            throw new IllegalArgumentException("value is not a number");
        }
    }

    protected static void isValidCoordinateArgument(double number) {
        try {
            assertNotInfinity(number);
            assertValidNumber(number);
        } catch (Exception e) {
            throw new IllegalStateException("Argument for Coordinate not valid, cause: " + e.getMessage());
        }
    }

    protected static void assertValidCoordinateArguments(double... arguments) {
        Arrays.stream(arguments).forEach(AssertionUtils::isValidCoordinateArgument);
    }

    protected static void assertShouldNoZero(double value) {
        if (value == 0) {
            throw new IllegalStateException("Value should not be zero!");
        }
    }

    protected static void assertShouldBePositive(double value) {
        if (value < 0) {
            throw new IllegalStateException("Value should not be negativ!");
        }
    }

    protected static void assertValueIsIn360DegreeSpectrum(double value) {
        if (value < -360 || value > 360 ) {
            throw new IllegalStateException("Value should be in 360 degree spectrum!");
        }
    }
}
