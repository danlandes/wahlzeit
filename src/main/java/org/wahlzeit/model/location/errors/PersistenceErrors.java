package org.wahlzeit.model.location.errors;

import java.sql.SQLException;

public class PersistenceErrors extends SQLException {

    public static class ResultSetIsNull extends PersistenceErrors {
        public static class OfCoordinateResult extends ResultSetIsNull {}
        public static class OfLocation extends ResultSetIsNull {}
        public static class OfPlantPhoto extends ResultSetIsNull {}
        public static class OfTreePhoto extends ResultSetIsNull {}
        public static class OfFlowerPhoto extends ResultSetIsNull {}
    }

    public static class noRecover extends SQLException {
        public static class OfPhoto extends noRecover {}
    }
}
