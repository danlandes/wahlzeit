package org.wahlzeit.model.plants;

import org.wahlzeit.model.PhotoId;
import org.wahlzeit.model.location.errors.PersistenceErrors;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.wahlzeit.model.location.AssertionUtils.assertNotNull;

public class FlowerPhoto extends PlantPhoto {
    private String flowerColor = "not defined";

    public FlowerPhoto(PhotoId myId) {
        super(myId);
    }

    public String getFlowerColor() {
        return flowerColor;
    }

    public void setFlowerColor(final String flowerColor) {
        this.flowerColor = flowerColor;
    }

    public FlowerPhoto(final ResultSet resultSet) throws SQLException {
        super(resultSet);
        readFrom(resultSet);
    }

    @Override
    public void readFrom(ResultSet rset) throws SQLException {
        assertNotNull(rset, new PersistenceErrors.ResultSetIsNull.OfFlowerPhoto());
        super.readFrom(rset);
        this.flowerColor = rset.getString(PlantPhotoLabels.FLOWER_COLOR.label);
    }

    @Override
    public void writeOn(final ResultSet resultSet) throws SQLException {
        assertNotNull(resultSet, new PersistenceErrors.ResultSetIsNull.OfFlowerPhoto());
        super.writeOn(resultSet);
        resultSet.updateString(PlantPhotoLabels.FLOWER_COLOR.label, flowerColor);
    }
}
