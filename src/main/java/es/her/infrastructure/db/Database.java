package es.her.infrastructure.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    // Se guardará en la carpeta del proyecto (raíz)
    private static final String URL = "jdbc:sqlite:gym.db";

    private Database() {}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
