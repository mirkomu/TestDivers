package ch.hearc.ig.odi.persistance;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import oracle.jdbc.pool.OracleConnectionPoolDataSource;
import oracle.jdbc.pool.OracleDataSource;

public class DBDOracleConnectionPoolDataSource {

    // methode qui retourne une nouvelle connection
    public Connection getConnection() throws SQLException {

        DataSource dataSource = new OracleConnectionPoolDataSource();

        //L'implémentation du DataSource est propre à chaque constructeur
        //L'interface DataSource ne propose qu'une méthode (getConnection() )
        ((OracleDataSource) dataSource).setDriverType("thin");
        ((OracleDataSource) dataSource).setServerName("db.ig.he-arc.ch");
        ((OracleDataSource) dataSource).setDatabaseName("ens3");
        ((OracleDataSource) dataSource).setPortNumber(1521);
        ((OracleDataSource) dataSource).setUser("USER14");
        ((OracleDataSource) dataSource).setPassword("USER14");

        Connection cnn = null;

        cnn = ((OracleConnectionPoolDataSource) dataSource).getPooledConnection().getConnection();
        cnn.setAutoCommit(false);

        return cnn;
    }
}
