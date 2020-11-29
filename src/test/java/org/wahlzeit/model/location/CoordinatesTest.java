package org.wahlzeit.model.location;

import org.junit.Test;

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
}
