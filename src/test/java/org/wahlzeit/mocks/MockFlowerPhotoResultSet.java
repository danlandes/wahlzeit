package org.wahlzeit.mocks;

import org.wahlzeit.model.location.CartesianCoordinate;
import org.wahlzeit.model.location.SphericCoordinate;
import org.wahlzeit.model.plants.PlantPhotoLabels;

import java.sql.SQLException;

public class MockFlowerPhotoResultSet extends MockResultSet {

    @Override
    public int findColumn(final String columnLabel) throws SQLException {
        if (PlantPhotoLabels.FLOWER_COLOR.label.equals(columnLabel)) {
            return 0;
        }
        if (CartesianCoordinate.TABLENAME_X.equals(columnLabel)) {
            return 0;
        }
        if (SphericCoordinate.TABLENAME_THETA.equals(columnLabel)) {
            return 0;
        }
        throw new SQLException();
    }
}
