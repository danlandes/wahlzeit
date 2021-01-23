package org.wahlzeit.model.plant;

import org.junit.Test;
import org.wahlzeit.mocks.MockFlowerPhotoResultSet;
import org.wahlzeit.mocks.MockTreePhotoResultSet;
import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoFactory;
import org.wahlzeit.model.PhotoId;
import org.wahlzeit.model.plants.PlantPhoto;
import org.wahlzeit.model.plants.PlantPhotoFactory;
import org.wahlzeit.model.plants.PlantType;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlantPhotoFactoryTest {

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
        final PlantPhoto result = (PlantPhoto) factory.createPhoto(new MockTreePhotoResultSet());
        assertEquals( result.plantType, PlantType.TREE);
    }

    @Test
    public void createFlowerPhotoWithResultSetShouldReturnDomainSpecificClass() throws SQLException {
        final PhotoFactory factory = PhotoFactory.getInstance();
        final PlantPhoto result = (PlantPhoto) factory.createPhoto(new MockFlowerPhotoResultSet());
        assertEquals(result.plantType, PlantType.FLOWER);
    }
}
