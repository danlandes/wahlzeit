package org.wahlzeit.model.plants;

import org.wahlzeit.model.PhotoId;
import org.wahlzeit.model.location.errors.PersistenceErrors;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.wahlzeit.model.location.AssertionUtils.assertNotNull;

public class TreePhoto extends PlantPhoto {

    private String treeFruits = "no fruits";

    public TreePhoto(PhotoId myId) {
        super(myId);
    }

    public TreePhoto(final ResultSet resultSet) throws SQLException {
        super(resultSet);
        readFrom(resultSet);
    }

    public String getTreeFruits() {
        return treeFruits;
    }

    public void setTreeFruits(final String treeFruits) {
        this.treeFruits = treeFruits;
    }

    @Override
    public void readFrom(ResultSet rset) throws SQLException {
        assertNotNull(rset, new PersistenceErrors.ResultSetIsNull.OfTreePhoto());
        super.readFrom(rset);
        this.treeFruits = rset.getString(PlantPhotoLabels.TREE_FRUITS.label);
    }

    @Override
    public void writeOn(final ResultSet resultSet) throws SQLException {
        assertNotNull(resultSet, new PersistenceErrors.ResultSetIsNull.OfTreePhoto());
        super.writeOn(resultSet);
        resultSet.updateString(PlantPhotoLabels.TREE_FRUITS.label, treeFruits);
    }
}
