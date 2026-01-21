package es.her.infrastructure.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInit {

    private DatabaseInit() {}

    public static void init() {
        try (Connection conn = Database.getConnection()) {
            createTables(conn);
            seedAdmin(conn);
            System.out.println("BD inicializada correctamente.");
        } catch (SQLException e) {
            throw new RuntimeException("Error inicializando la BD", e);
        }
    }

    private static void createTables(Connection conn) throws SQLException {

        String sqlUsers = """
            CREATE TABLE IF NOT EXISTS users (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                email TEXT NOT NULL UNIQUE,
                password_hash TEXT NOT NULL,
                role TEXT NOT NULL,
                full_name TEXT NOT NULL,
                active INTEGER NOT NULL DEFAULT 1,
                created_at TEXT NOT NULL DEFAULT (datetime('now'))
            );
        """;

        try (Statement st = conn.createStatement()) {
            st.execute(sqlUsers);
        }
    }

    private static void seedAdmin(Connection conn) throws SQLException {
        // Comprobar si existe el admin
        String existsSql = "SELECT id FROM users WHERE email = ? LIMIT 1";

        try (PreparedStatement ps = conn.prepareStatement(existsSql)) {
            ps.setString(1, "admin@gym.com");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Ya existe
                    return;
                }
            }
        }

        // Insertar admin inicial
        // NOTA: por ahora dejamos un hash “dummy” para el paso 3 (login real con bcrypt)
        String insertSql = """
            INSERT INTO users(email, password_hash, role, full_name, active)
            VALUES(?, ?, ?, ?, 1)
        """;

        try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
            ps.setString(1, "admin@gym.com");
            ps.setString(2, "admin123"); // en Paso 3 lo cambiaremos por bcrypt REAL
            ps.setString(3, "ADMIN");
            ps.setString(4, "Administrador");
            ps.executeUpdate();
        }

        System.out.println("Admin inicial creado: admin@gym.com / admin123");
    }
}
