package org.wahlzeit.model.plants;

import org.wahlzeit.model.Photo;

import java.sql.ResultSet;
import java.sql.SQLException;

abstract class PlantPhoto extends Photo {

    public final String speciesLatin;
    public final String speciesEN;

    protected PlantPhoto(final String speciesLatin, final String speciesEN) {
        this.speciesLatin = speciesLatin;
        this.speciesEN = speciesEN;
    }

    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        super.writeOn(rset);
        rset.updateString(PlantPhotoLabels.PLANT_SPECIES_LATIN.label, speciesLatin);
        rset.updateString(PlantPhotoLabels.PLANT_SPECIES_EN.label, speciesEN);
    }

    protected abstract void writeOnPlant(ResultSet resultSet) throws SQLException;
}
