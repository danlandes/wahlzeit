package org.wahlzeit.mocks;

import org.wahlzeit.model.plants.PlantPhotoLabels;

import java.sql.SQLException;

public class MockTreePhotoResultSet extends MockResultSet {

    @Override
    public int findColumn(final String columnLabel) throws SQLException {
        if (PlantPhotoLabels.TREE_FRUITS.label.equals(columnLabel)) {
            return 0;
        }
        throw new SQLException();
    }
}
