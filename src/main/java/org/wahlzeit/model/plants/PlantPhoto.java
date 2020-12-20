package org.wahlzeit.model.plants;

import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;
import org.wahlzeit.model.location.errors.PersistenceErrors;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.wahlzeit.model.location.AssertionUtils.assertNotNull;

public class PlantPhoto extends Photo {

    public String speciesLatin = "Nihil omnino mercedis";
    public String speciesEN = "not specified";

    public PlantPhoto(PhotoId myId) {
        super(myId);
    }

    public PlantPhoto(final ResultSet resultSet) throws SQLException {
        super(resultSet);
        readFrom(resultSet);
    }

    @Override
    public void readFrom(ResultSet rset) throws SQLException {
        assertNotNull(rset, new PersistenceErrors.ResultSetIsNull.OfPlantPhoto());
        super.readFrom(rset);
        this.speciesLatin = rset.getString(PlantPhotoLabels.PLANT_SPECIES_LATIN.label);
        this.speciesEN = rset.getString(PlantPhotoLabels.PLANT_SPECIES_EN.label);
    }

    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        assertNotNull(rset, new PersistenceErrors.ResultSetIsNull.OfPlantPhoto());
        super.writeOn(rset);
        rset.updateString(PlantPhotoLabels.PLANT_SPECIES_LATIN.label, speciesLatin);
        rset.updateString(PlantPhotoLabels.PLANT_SPECIES_EN.label, speciesEN);
    }

    public String getSpeciesEN() {
        return speciesEN;
    }

    public void setSpeciesEN(final String speciesEN) {
        this.speciesEN = speciesEN;
    }

    public String getSpeciesLatin() {
        return speciesLatin;
    }

    public void setSpeciesLatin(final String speciesLatin) {
        this.speciesLatin = speciesLatin;
    }
}
