package org.wahlzeit.model.plants;

import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoFactory;
import org.wahlzeit.model.PhotoId;
import org.wahlzeit.services.persistence.PersistenceUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlantPhotoFactory extends PhotoFactory {

    public Photo createPhoto(PhotoId id) {
        return new PlantPhoto(id);
    }

    public Photo createPhoto(ResultSet rs) throws SQLException {
        if (PersistenceUtils.assertColumnIsPresent(PlantPhotoLabels.FLOWER_COLOR.label, rs)) {
            return new PlantPhoto(rs, PlantType.FLOWER);
        } else if (PersistenceUtils.assertColumnIsPresent(PlantPhotoLabels.TREE_FRUITS.label, rs)) {
            return new PlantPhoto(rs, PlantType.TREE);
        }
        return new PlantPhoto(rs);
    }
}
