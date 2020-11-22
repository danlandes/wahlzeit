package org.wahlzeit.model.plants;

import org.wahlzeit.model.PhotoId;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TreePhoto extends PlantPhoto {

    private String treeFruits = "no fruits";

    public TreePhoto(PhotoId myId) {
        super(myId);
    }

    public TreePhoto(final ResultSet resultSet) throws SQLException {
        super(resultSet);
        readFrom(resultSet);
    }

    @Override
    public void readFrom(ResultSet rset) throws SQLException {
        super.readFrom(rset);
        this.treeFruits = rset.getString(PlantPhotoLabels.TREE_FRUITS.label);
    }

    @Override
    public void writeOn(final ResultSet resultSet) throws SQLException {
        super.writeOn(resultSet);
        resultSet.updateString(PlantPhotoLabels.TREE_FRUITS.label, treeFruits);
    }
}
