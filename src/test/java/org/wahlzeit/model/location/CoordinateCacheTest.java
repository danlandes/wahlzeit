package org.wahlzeit.model.location;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public class CoordinateCacheTest {

    @Test
    public void shouldBeSameInstance() {
        final CartesianCoordinate first = CoordinateCache.getForCartesian(0,0,0);
        final CartesianCoordinate second = CoordinateCache.getForCartesian(0,0,0);

        assertSame(first, second);
    }

    @Test
    public void shouldBeSameInstance2() {
        final SphericCoordinate first = CoordinateCache.getAsSpheric(1,1,1);
        final SphericCoordinate second = CoordinateCache.getAsSpheric(1,1,1);

        assertSame(first, second);
    }
}
