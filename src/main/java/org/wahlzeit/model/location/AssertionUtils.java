package org.wahlzeit.model.location;

import org.wahlzeit.model.location.errors.CoordinateStateNotValid;
import org.wahlzeit.model.location.errors.LocationStateNotValid;

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

    protected static <T extends Throwable> void assertNotNull(final Object coordinate, T t) throws T {
        if (coordinate == null) {
            throw t;
        }
    }

    protected static void assertCoordinateIsSupportedSubtype(ICoordinate coordinate) throws LocationStateNotValid.CoordinateIsNotSupportedSubtype {
        if (isCartesianCoordinate(coordinate) || isSphericCoordinate(coordinate)) {
            return;
        }
        throw new LocationStateNotValid.CoordinateIsNotSupportedSubtype();
    }

    private static boolean isCartesianCoordinate(final ICoordinate coordinate) {
        return coordinate instanceof CartesianCoordinate;
    }

    private static boolean isSphericCoordinate(final ICoordinate coordinate) {
        return coordinate instanceof SphericCoordinate;
    }

    protected static void assertNotInfinity(double number) {
        if (Double.isInfinite(number)) {
            throw new CoordinateStateNotValid.CalculationError.InfinityValueForbidden();
        }
    }

    protected static void asserIsNotNaN(double number) {
        if (Double.isNaN(number)) {
            throw new CoordinateStateNotValid.CalculationError.NotAValue();
        }
    }

    protected static void isValidCoordinateArgument(double number) {
        try {
            assertNotInfinity(number);
            asserIsNotNaN(number);
        } catch (Exception e) {
            throw new CoordinateStateNotValid.CoordinateCreationFailed();
        }
    }

    protected static void assertValidCoordinateArguments(double... arguments) {
        Arrays.stream(arguments).forEach(AssertionUtils::isValidCoordinateArgument);
    }

    protected static void assertArgumentsOfCartesianCoordinate(final double x, final double y, final double z) {
        try {
            assertValidCoordinateArguments(x, y, z);
        } catch (CoordinateStateNotValid.CoordinateCreationFailed e) {
            throw new CoordinateStateNotValid.CoordinateCreationFailed.OfInstanceCartesian();
        }
    }

    protected static void assertArgumentsOfSphericCoordinate(final double phi, final double theta, final double radius) {
        try {
            assertValidCoordinateArguments(phi, theta, radius);
        } catch (CoordinateStateNotValid.CoordinateCreationFailed e) {
            throw new CoordinateStateNotValid.CoordinateCreationFailed.OfInstanceSpheric();
        }
    }

    protected static void assertShouldNotBeZero(double value) {
        if (value == 0) {
            throw new CoordinateStateNotValid.CalculationError.ZeroValueForbidden();
        }
    }

    protected static void assertShouldBePositive(double value) {
        if (value < 0) {
            throw new CoordinateStateNotValid.CalculationError.NegativeValueForbidden();
        }
    }

    protected static void assertValueIsIn360DegreeSpectrum(double value) {
        if (value < -360 || value > 360) {
            throw new IllegalStateException("Value should be in 360 degree spectrum!");
        }
    }
}
