package se2203b.assignments.adminapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Handles interactions with the product database
 *
 * @author Gabriel Olivotto
 */
public class ProductAdapter {

    Connection connection;

    /**
     * Creates product database or provides access to it
     * @param conn
     * @param reset
     * @throws SQLException
     */
    public ProductAdapter(Connection conn, boolean reset) throws SQLException {
        connection = conn;
        if (reset) {
            Statement stmt = connection.createStatement();
            try {
                // Remove tables if database tables have been created.
                // This will throw an exception if the tables do not exist
                stmt.execute("DROP TABLE Products");
                // then do finally
            } catch (SQLException ex) {
                // No need to report an error.
                // The table simply did not exist.
                // do finally to create it
            } finally {
                // Create the table of products
                stmt.execute("CREATE TABLE Products ("
                        + "Id INT NOT NULL PRIMARY KEY, "
                        + "Name CHAR(15), "
                        + "Brand CHAR(15), "
                        + "Price DOUBLE, "
                        + "Stock INT, "
                        + "Catalog CHAR(15) "
                        + ")");
            }
        }
    }

    /**
     * Inserts product into the database
     * @param product
     * @throws SQLException
     */
    public void insertProduct(Product product) throws SQLException {
        Statement stmt = connection.createStatement();
        String sqlStatement = "INSERT INTO Products (Id,Name,Brand,Price,Stock,Catalog) VALUES (" +
                product.getId() + ", '" +
                product.getName() + "', '" +
                product.getBrand() + "', " +
                product.getPrice() + ", " +
                product.getStock() + ", '" +
                product.getCatalog().getName() + "' )";
        stmt.executeUpdate(sqlStatement);
    }

    /**
     * Updates a product in the database
     * @param oldName
     * @param oldCatalog
     * @param newName
     * @param newBrand
     * @param price
     * @param stock
     * @param catalog
     * @throws SQLException
     */
    public void updateProduct(String oldName, String oldCatalog, String newName, String newBrand, double price, int stock, Catalog catalog) throws SQLException {
        Statement stmt = connection.createStatement();
        String sqlStatement = "UPDATE Products SET " +
                "Name = '" + newName + "', " +
                "Brand = '" + newBrand + "', " +
                "Price = " + price + ", " +
                "Stock = " + stock + ", " +
                "Catalog = '" + catalog.getName() + "' "+
                "WHERE Name = '" + oldName + "' AND Catalog = '" + oldCatalog + "'";
        stmt.executeUpdate(sqlStatement);
    }

    /**
     * Delete catalog in database
     * @param name
     * @throws SQLException
     */
    public void deleteProduct(String name, String catalog) throws SQLException {
        Statement stmt = connection.createStatement();
        String sqlStatement = "DELETE FROM Products WHERE Name = '" + name + "' AND Catalog = '" + catalog + "'";
        stmt.executeUpdate(sqlStatement);
    }

    /**
     * Gets the product with that name and catalog
     * @param name
     * @param catalog
     * @return
     * @throws SQLException
     */
    public Product getProduct(String name, String catalog) throws SQLException {
        Product product = null;

        Statement stmt = connection.createStatement();
        ResultSet rs;

        // Getting the max id from database
        rs = stmt.executeQuery("SELECT * FROM Products WHERE Name = '" + name + "' AND Catalog = '" + catalog + "'");

        while(rs.next()) {
            product = new Product(
                    rs.getInt("Id"),
                    rs.getString("Name"),
                    rs.getString("Brand"),
                    rs.getDouble("Price"),
                    rs.getInt("Stock"),
                    new Catalog(rs.getString("Catalog")));
        }

        return product;
    }

    /**
     * Finds the max product ID in the database and adds 1
     * @return max ID + 1
     * @throws SQLException
     */
    public int getNextId() throws SQLException {
        int num = 0;

        Statement stmt = connection.createStatement();
        ResultSet rs;

        // Getting the max id from database
        rs = stmt.executeQuery("SELECT MAX(Id) FROM Products");

        // Setting the max to num
        while(rs.next()) {
            num = rs.getInt(1);
        }

        // Returning next Id
        return num + 1;
    }

    /**
     * Checks to see if a product's catalog matches the input catalog
     * @param catalog
     * @return true when a product uses that catalog
     * @throws SQLException
     */
    public boolean isCatalogInUse(String catalog) throws SQLException {
        Statement stmt  = connection.createStatement();
        String sqlStatement = "SELECT * FROM Products WHERE Catalog = '" + catalog + "'";

        ResultSet rs = stmt.executeQuery(sqlStatement);

        if(rs.next()) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Updates the catalog of products to a new name
     * @param oldName
     * @param newName
     * @throws SQLException
     */
    public void updateCatalogNames(String oldName, String newName) throws SQLException {
        Statement stmt = connection.createStatement();
        String sqlStatement ="UPDATE Products SET Catalog = '" + newName + "' WHERE Catalog = '" + oldName + "'";
        stmt.executeUpdate(sqlStatement);
    }

    /**
     * Gets all products names part of that catalog
     * @param catalog
     * @return
     * @throws SQLException
     */
    public ObservableList<String> getNamesWithCatalogList(String catalog) throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();
        ResultSet rs;

        // Create a Statement object
        Statement stmt = connection.createStatement();

        // Create a string with a SELECT statement
        String sqlStatement = "SELECT Name FROM Products WHERE Catalog = '" + catalog + "'";

        // Execute the statement and return the result
        rs = stmt.executeQuery(sqlStatement);

        while (rs.next()) {
            list.add(rs.getString("Name"));
        }

        return list;
    }
}
