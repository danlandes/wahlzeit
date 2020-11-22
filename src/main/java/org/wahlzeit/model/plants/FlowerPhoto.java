package org.wahlzeit.model.plants;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FlowerPhoto extends PlantPhoto {
    private String flowerColor;

    public FlowerPhoto(final String speciesLatin, final String speciesEN, final String flowerColor) {
        super(speciesLatin, speciesEN);
        this.flowerColor = flowerColor;
    }

    @Override
    protected void writeOnPlant(final ResultSet resultSet) throws SQLException {
        resultSet.updateString(PlantPhotoLabels.FLOWER_COLOR.label, flowerColor);
    }
}
