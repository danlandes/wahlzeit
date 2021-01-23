package org.wahlzeit.model.plants;

import java.util.HashMap;

public class PlantType {
    public final String name;
    private final static HashMap instances = new HashMap<String, PlantType>();
    public final static PlantType TREE = new PlantType("tree");
    public final static PlantType FLOWER = new PlantType("flower");
    public PlantType parent = null;

    private PlantType(final String name) {
        this.name = name;
        instances.put("flower", PlantType.FLOWER);
        instances.put("tree", PlantType.TREE);
    }

    public static PlantType instanceOf(final String name) {
        if(instances.containsKey(name)) {
            return (PlantType) instances.get(name);
        }
        final PlantType type = new PlantType(name);
        instances.put(name, type);
        return type;
    }

    @Override
    public String toString() {
        return "PlantType{" +
                "name='" + name + '\'' +
                '}';
    }

    public boolean isSubtype(PlantType type) {
        return type.equals(parent);
    }
}
