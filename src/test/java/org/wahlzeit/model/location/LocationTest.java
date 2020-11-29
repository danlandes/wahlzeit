package org.wahlzeit.model.location;

import org.junit.Test;

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
}
