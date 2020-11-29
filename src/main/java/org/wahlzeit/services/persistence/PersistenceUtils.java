package org.wahlzeit.services.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersistenceUtils {
    public static boolean assertColumnIsPresent(String label, ResultSet resultSet) {
        try {
            resultSet.findColumn(label);
            return true;
        } catch (SQLException throwables) {
            return false;
        }
    }
}
