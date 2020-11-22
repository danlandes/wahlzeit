package org.wahlzeit.model.plants;

import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoFactory;
import org.wahlzeit.model.PhotoId;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlantPhotoFactory extends PhotoFactory {

    public Photo createPhoto(PhotoId id) {
        return new PlantPhoto(id);
    }

    public Photo createPhoto(ResultSet rs) throws SQLException {
        if (assertColumnIsPresent(PlantPhotoLabels.FLOWER_COLOR.label, rs)) {
            return new FlowerPhoto(rs);
        } else if (assertColumnIsPresent(PlantPhotoLabels.TREE_FRUITS.label, rs)) {
            return new TreePhoto(rs);
        }
        return new PlantPhoto(rs);
    }

    private boolean assertColumnIsPresent(String label, ResultSet resultSet) {
        try {
            resultSet.findColumn(label);
            return true;
        } catch (SQLException throwables) {
            return false;
        }
    }
}
