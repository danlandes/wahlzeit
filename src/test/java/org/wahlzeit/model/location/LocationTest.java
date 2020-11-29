package org.wahlzeit.model.location;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LocationTest {

    @Test
    public void creationOfLocation() {
        final Location location = new Location(1, 2, 3);
        assertEquals(1, location.getCoordinate().getX(), 0.0);
        assertEquals(2, location.getCoordinate().getY(), 0.0);
        assertEquals(3, location.getCoordinate().getZ(), 0.0);
    }
}
