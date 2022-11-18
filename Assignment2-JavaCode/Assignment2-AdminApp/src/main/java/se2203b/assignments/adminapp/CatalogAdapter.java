package se2203b.assignments.adminapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Handles interactions with the catalog database
 *
 * @author Gabriel Olivotto
 */
public class CatalogAdapter {

    Connection connection;

    /**
     * Creates catalog database or provides access to it
     * @param conn
     * @param reset
     * @throws SQLException
     */
    public CatalogAdapter(Connection conn, boolean reset) throws SQLException {
        connection = conn;
        if (reset) {
            Statement stmt = connection.createStatement();
            try {
                // Remove tables if database tables have been created.
                // This will throw an exception if the tables do not exist
                stmt.execute("DROP TABLE Catalogs");
                // then do finally
            } catch (SQLException ex) {
                // No need to report an error.
                // The table simply did not exist.
                // do finally to create it
            } finally {
                // Create the table of catalogs
                stmt.execute("CREATE TABLE Catalogs ("
                        + "Name CHAR(15) NOT NULL PRIMARY KEY "
                        + ")");
            }
        }
    }

    /**
     * Inserts catalog into the database
     * @param name
     * @throws SQLException
     */
    public void insertCatalog(String name) throws SQLException {
        Statement stmt = connection.createStatement();
        String sqlStatement = "INSERT INTO Catalogs (Name) VALUES ('" + name + "')";
        stmt.executeUpdate(sqlStatement);
    }

    /**
     * Updates the catalog in the database
     * @param oldName
     * @param newName
     * @throws SQLException
     */
    public void updateCatalog(String oldName, String newName) throws SQLException {
        Statement stmt = connection.createStatement();
        String sqlStatement = "UPDATE Catalogs SET Name = '" + newName + "' WHERE Name = '" + oldName + "'";
        stmt.executeUpdate(sqlStatement);
    }

    /**
     * Delete catalog in database
     * @param name
     * @throws SQLException
     */
    public void deleteCatalog(String name) throws SQLException {
        Statement stmt = connection.createStatement();
        String sqlStatement = "DELETE FROM Catalogs WHERE Name = '" + name + "'";
        stmt.executeUpdate(sqlStatement);
    }

    /**
     * Gets the names of all catalogs in the database
     * @return
     * @throws SQLException
     */
    public ObservableList<String> getNamesList() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();
        ResultSet rs;

        Statement stmt = connection.createStatement();
        String sqlStatement = "SELECT Name FROM Catalogs";
        rs = stmt.executeQuery(sqlStatement);

        // loop for the all rs rows and update list
        while (rs.next()) {
            list.add(rs.getString("Name"));
        }

        return list;
    }
}
