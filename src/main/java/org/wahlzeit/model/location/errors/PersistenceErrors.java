package org.wahlzeit.model.location.errors;

import java.sql.SQLException;

public class PersistenceErrors extends SQLException {

    public static class ResultSetIsNull extends PersistenceErrors {
        public static class OfCoordinateResult extends ResultSetIsNull {}
        public static class OfLocation extends ResultSetIsNull {}
    }

}
