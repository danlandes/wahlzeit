package org.wahlzeit.model.plants;

import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;

import java.sql.ResultSet;
import java.sql.SQLException;

class PlantPhoto extends Photo {

    public String speciesLatin = "Nihil omnino mercedis";
    public String speciesEN = "not specified";

    protected PlantPhoto(final String speciesLatin, final String speciesEN) {
        this.speciesLatin = speciesLatin;
        this.speciesEN = speciesEN;
    }

    public PlantPhoto(PhotoId myId) {
        super(myId);
    }

    public PlantPhoto(final ResultSet resultSet) throws SQLException {
        super(resultSet);
        readFrom(resultSet);
    }

    @Override
    public void readFrom(ResultSet rset) throws SQLException {
        super.readFrom(rset);
        this.speciesLatin = rset.getString(PlantPhotoLabels.PLANT_SPECIES_LATIN.label);
        this.speciesEN = rset.getString(PlantPhotoLabels.PLANT_SPECIES_EN.label);
    }

    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        super.writeOn(rset);
        rset.updateString(PlantPhotoLabels.PLANT_SPECIES_LATIN.label, speciesLatin);
        rset.updateString(PlantPhotoLabels.PLANT_SPECIES_EN.label, speciesEN);
    }
}
