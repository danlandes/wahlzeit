package org.wahlzeit.model.location;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SphericCoordinateTest {

    @Test
    public void createSpericCoordinate() {
        double r = 10;
        double theta = 15;
        double phi = 20;

        final SphericCoordinate coordinate = new SphericCoordinate(phi, theta, r);

        assertEquals(r, coordinate.getRadius(), 0);
        assertEquals(phi, coordinate.getPhi(), 0);
        assertEquals(theta, coordinate.getTheta(), 0);
        assertEquals(phi, coordinate.getLongitude(), 0);
        assertEquals(theta, coordinate.getLatitude(), 0);
    }

    @Test
    public void shouldBeConvertibleToCartesianCoordinate() {
        final SphericCoordinate coordinate = new SphericCoordinate(10, 15, 20);
        final CartesianCoordinate result = coordinate.asCartesianCoordinate();
        assertNotNull(result);
    }

    @Test
    public void shouldBeConvertibleToCartesianCoordinateViseVersa() {
        double r = 10;
        double theta = 15;
        double phi = 20;

        final SphericCoordinate coordinate = new SphericCoordinate(phi, theta, r);
        final CartesianCoordinate cartesian = coordinate.asCartesianCoordinate();
        final SphericCoordinate result = cartesian.asSphericCoordinate();
        assertNotNull(result);
        assertEquals(r, coordinate.getRadius(), 0);
        assertEquals(phi, coordinate.getPhi(), 0);
        assertEquals(theta, coordinate.getTheta(), 0);
    }

    @Test
    public void shouldBeEqual() {
        final SphericCoordinate first = new SphericCoordinate(10, 10, 10);
        final SphericCoordinate second = new SphericCoordinate(10, 10, 10);
        assertTrue(first.isEqual(second));
    }

    @Test
    public void shouldNOTBeEqual() {
        final SphericCoordinate first = new SphericCoordinate(10, 10, 10);
        final SphericCoordinate second = new SphericCoordinate(10, 30, 90);
        assertFalse(first.isEqual(second));
    }

    @Test
    public void centralAngleTest() {
        final SphericCoordinate first = new SphericCoordinate(Math.toRadians(60), Math.toRadians(120), 0);
        final SphericCoordinate second = new SphericCoordinate(Math.toRadians(30), Math.toRadians(60), 0);
        final double result = first.getCentralAngle(second);
        System.out.println(result);
        assertEquals(centralAngleCheck(first, second), result, 0.2);
    }

    public double centralAngleCheck(SphericCoordinate first, SphericCoordinate second) {
        double deltaLongitude = Math.abs(first.getLongitude() - second.getLongitude());
        double deltaLatitude = Math.abs(first.getLatitude() - second.getLatitude());

        double body = sinSqr(deltaLatitude / 2)
                + Math.cos(first.getLatitude()) * Math.cos(second.getLatitude()) * sinSqr(deltaLongitude / 2);
        final double result = 2 * Math.asin(Math.sqrt(body));
        return result;
    }

    private double sinSqr(double value) {
        return Math.pow(Math.sin(value), 2);
    }


}
