package org.wahlzeit.services.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface PersistenceReadable {
    public void readFrom(ResultSet rset) throws SQLException;
}
