package org.wahlzeit.services.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface PersistenceWritable {
    public void writeOn(ResultSet rset) throws SQLException;
}
