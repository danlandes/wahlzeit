package org.wahlzeit.model.location;

import org.junit.Test;
import org.wahlzeit.model.location.errors.CoordinateStateNotValid;

import static java.lang.Double.NaN;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CoordinatesTest {

    @Test
    public void coordinatesThatDistanceIsSMALLERThanTheToleratedDeviationShouldBeEqual() {
        final CartesianCoordinate sameSpotFirstFloor = new CartesianCoordinate(1000, 3000, 0);
        final CartesianCoordinate sameSpoSecondFloor = new CartesianCoordinate(1000, 3000, 2);
        assertTrue(sameSpoSecondFloor.isEqual(sameSpotFirstFloor));
    }

    @Test
    public void coordinatesThatDistanceIsHIGHERThanTheToleratedDeviationShouldNOTBeEqual() {
        final CartesianCoordinate sameSpotFirstFloor = new CartesianCoordinate(1000, 3000, 0);
        final CartesianCoordinate sameSpoSecondFloor = new CartesianCoordinate(1000, 3200, 0);
        assertFalse(sameSpoSecondFloor.isEqual(sameSpotFirstFloor));
    }

    @Test
    public void getDistanceShouldCalculateTheCartesianDistance1() {
        final CartesianCoordinate sameSpotFirstFloor = new CartesianCoordinate(1000, 3000, 0);
        final CartesianCoordinate sameSpoSecondFloor = new CartesianCoordinate(1000, 3200, 0);
        assertEquals(200, sameSpoSecondFloor.getCartesianDistance(sameSpotFirstFloor), 0.0);
    }

    @Test
    public void getDistanceShouldCalculateTheCartesianDistance2() {
        final CartesianCoordinate sameSpotFirstFloor = new CartesianCoordinate(1000, 3000, 0);
        final CartesianCoordinate sameSpoSecondFloor = new CartesianCoordinate(1200, 3000, 0);
        assertEquals(200, sameSpoSecondFloor.getCartesianDistance(sameSpotFirstFloor), 0.0);
    }

    @Test
    public void getDistanceShouldCalculateTheCartesianDistance3() {
        final CartesianCoordinate sameSpotFirstFloor = new CartesianCoordinate(1000, 3000, 0);
        final CartesianCoordinate sameSpoSecondFloor = new CartesianCoordinate(1000, 3200, 200);
        assertEquals(282, sameSpoSecondFloor.getCartesianDistance(sameSpotFirstFloor), 1.0);
    }

    @Test(expected = CoordinateStateNotValid.CoordinateCreationFailed.OfInstanceCartesian.class)
    public void createCoordinateShouldFail1() {
        new CartesianCoordinate(NaN, 3000, 0);
    }

    @Test(expected = CoordinateStateNotValid.CoordinateCreationFailed.OfInstanceCartesian.class)
    public void createCoordinateShouldFail2() {
        new CartesianCoordinate(0, NaN, 0);
    }

    @Test(expected = CoordinateStateNotValid.CoordinateCreationFailed.OfInstanceCartesian.class)
    public void createCoordinateShouldFail3() {
        new CartesianCoordinate(0, 0, NaN);
    }

    @Test(expected = CoordinateStateNotValid.CoordinateCreationFailed.OfInstanceSpheric.class)
    public void createCoordinateShouldFail4() {
        new SphericCoordinate(NaN, 3000, 0);
    }

    @Test(expected = CoordinateStateNotValid.CoordinateCreationFailed.OfInstanceSpheric.class)
    public void createCoordinateShouldFail5() {
        new SphericCoordinate(0, NaN, 0);
    }

    @Test(expected = CoordinateStateNotValid.CoordinateCreationFailed.OfInstanceSpheric.class)
    public void createCoordinateShouldFail6() {
        new SphericCoordinate(0, 0, NaN);
    }

    @Test(expected = CoordinateStateNotValid.CalculationError.ZeroValueForbidden.class)
    public void convertingToSphericShouldFail() {
        new CartesianCoordinate(0, 0, 0).asSphericCoordinate();
    }
}
