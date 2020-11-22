package org.wahlzeit.model.plants;

import org.wahlzeit.model.PhotoId;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FlowerPhoto extends PlantPhoto {
    private String flowerColor = "not defined";

    public FlowerPhoto(PhotoId myId) {
        super(myId);
    }

    public FlowerPhoto(final ResultSet resultSet) throws SQLException {
        super(resultSet);
        readFrom(resultSet);
    }

    @Override
    public void readFrom(ResultSet rset) throws SQLException {
        super.readFrom(rset);
        this.flowerColor = rset.getString(PlantPhotoLabels.FLOWER_COLOR.label);
    }

    @Override
    public void writeOn(final ResultSet resultSet) throws SQLException {
        super.writeOn(resultSet);
        resultSet.updateString(PlantPhotoLabels.FLOWER_COLOR.label, flowerColor);
    }
}
