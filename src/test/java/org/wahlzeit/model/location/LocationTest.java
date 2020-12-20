package org.wahlzeit.model.location;

import org.junit.Test;
import org.wahlzeit.model.location.errors.LocationStateNotValid;

import static org.junit.Assert.assertEquals;

public class LocationTest {

    @Test
    public void creationOfLocation() {
        final Location location = new Location(new CartesianCoordinate(1, 2, 3));
        final CartesianCoordinate c = (CartesianCoordinate) location.getCoordinate();
        assertEquals(1, c.getX(), 0.0);
        assertEquals(2, c.getY(), 0.0);
        assertEquals(3, c.getZ(), 0.0);
    }

    @Test(expected = LocationStateNotValid.CoordinateWasNull.class)
    public void creationOfLocationShouldFail1() {
        final SphericCoordinate coordinate = null;
        new Location(coordinate);
    }

    @Test(expected = LocationStateNotValid.CoordinateWasNull.class)
    public void creationOfLocationShouldFail2() {
        final CartesianCoordinate coordinate = null;
        new Location(coordinate);
    }

}
