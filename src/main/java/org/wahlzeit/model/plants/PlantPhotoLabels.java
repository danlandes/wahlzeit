package org.wahlzeit.model.plants;

public enum PlantPhotoLabels {
    PLANT_SPECIES_EN("plant_species_en"),
    PLANT_SPECIES_LATIN("plant_species_latin"),
    FLOWER_COLOR("plant_flower_color"),
    TREE_FRUITS("plant_tree_fruits")
    ;

    public final String label;

    PlantPhotoLabels(String label) {
        this.label = label;
    }
}
