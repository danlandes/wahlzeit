package org.wahlzeit.model.plants;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TreePhoto extends PlantPhoto {

    private final String treeFruits;

    protected TreePhoto(final String speciesLatin, final String speciesEN, final String treeFruits) {
        super(speciesLatin, speciesEN);
        this.treeFruits = treeFruits;
    }

    @Override
    protected void writeOnPlant(final ResultSet resultSet) throws SQLException {
        resultSet.updateString(PlantPhotoLabels.TREE_FRUITS.label, treeFruits);
    }
}
