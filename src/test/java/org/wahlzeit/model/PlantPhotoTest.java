package org.wahlzeit.model;

import org.junit.Test;
import org.wahlzeit.mocks.MockFlowerPhotoResultSet;
import org.wahlzeit.mocks.MockTreePhotoResultSet;
import org.wahlzeit.model.plants.FlowerPhoto;
import org.wahlzeit.model.plants.PlantPhoto;
import org.wahlzeit.model.plants.PlantPhotoFactory;
import org.wahlzeit.model.plants.TreePhoto;

import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

public class PlantPhotoTest {

    @Test
    public void factoryInstanceShouldBePlantFactory() {
        final PhotoFactory factory = PhotoFactory.getInstance();
        assertTrue(factory instanceof PlantPhotoFactory);
    }

    @Test
    public void createPhotoWithIdShouldReturnDomainSpecificClass() {
        final PhotoFactory factory = PhotoFactory.getInstance();
        final Photo result = factory.createPhoto(PhotoId.NULL_ID);
        assertTrue(result instanceof PlantPhoto);
    }

    @Test
    public void createTreePhotoWithResultSetShouldReturnDomainSpecificClass() throws SQLException {
        final PhotoFactory factory = PhotoFactory.getInstance();
        final Photo result = factory.createPhoto(new MockTreePhotoResultSet());
        assertTrue(result instanceof TreePhoto);
    }

    @Test
    public void createFlowerPhotoWithResultSetShouldReturnDomainSpecificClass() throws SQLException {
        final PhotoFactory factory = PhotoFactory.getInstance();
        final Photo result = factory.createPhoto(new MockFlowerPhotoResultSet());
        assertTrue(result instanceof FlowerPhoto);
    }
}
