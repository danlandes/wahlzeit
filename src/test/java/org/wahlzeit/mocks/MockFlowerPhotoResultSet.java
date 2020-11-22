package org.wahlzeit.mocks;

import org.wahlzeit.model.plants.PlantPhotoLabels;

import java.sql.SQLException;

public class MockFlowerPhotoResultSet extends MockResultSet {

    @Override
    public int findColumn(final String columnLabel) throws SQLException {
        if (PlantPhotoLabels.FLOWER_COLOR.label.equals(columnLabel)) {
            return 0;
        }
        throw new SQLException();
    }
}
