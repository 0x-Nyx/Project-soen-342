package TDG;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.ArtObject;

import DB.Connect;

public class ObjectTDG {

    public static int insert(ArtObject obj) throws SQLException {
        String query = "INSERT INTO Object (Name_object, Type, IsOwned, IsAuctioned) VALUES (?, ?, ?, ?)";
        try (Connection conn = Connect.connect();
                PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, obj.getName());
            stmt.setString(2, obj.getType());
            stmt.setBoolean(3, obj.isOwnedByInstitution());
            stmt.setBoolean(4, obj.isAuctioned());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return -1;
    }

    public static void update(ArtObject obj) throws SQLException {
        String query = "UPDATE Object SET Name_object=?, Type=?, IsOwned=?, IsAuctioned=? WHERE Object_ID=?";
        try (Connection conn = Connect.connect();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, obj.getName());
            stmt.setString(2, obj.getType());
            stmt.setBoolean(3, obj.isOwnedByInstitution());
            stmt.setBoolean(4, obj.isAuctioned());
            stmt.setInt(5, obj.getId());
            stmt.executeUpdate();
        }
    }

    public static void delete(int id) throws SQLException {
        String query = "DELETE FROM Object WHERE Object_ID=?";
        try (Connection conn = Connect.connect();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public static ArtObject findById(int id) throws SQLException {
        String query = "SELECT * FROM Object WHERE Object_ID=?";
        try (Connection conn = Connect.connect();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new ArtObject(
                        rs.getInt("Object_ID"),
                        rs.getString("Name_object"),
                        rs.getString("Type"),
                        rs.getBoolean("IsOwned"),
                        rs.getBoolean("IsAuctioned"));
            }
        }
        return null;
    }

    public static List<ArtObject> findAll() throws SQLException {
        List<ArtObject> objects = new ArrayList<>();
        String query = "SELECT * FROM Object";
        try (Connection conn = Connect.connect();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                objects.add(new ArtObject(
                        rs.getInt("Object_ID"),
                        rs.getString("Name_object"),
                        rs.getString("Type"),
                        rs.getBoolean("IsOwned"),
                        rs.getBoolean("IsAuctioned")));
            }
        }
        return objects;
    }

    public static List<ArtObject> getObjectsByType(String type) throws SQLException {

        List<ArtObject> objects = new ArrayList<>();

        String sql = "SELECT Object_ID, Name_object, type FROM Object WHERE type = ? AND IsOwned = true";
        ;

        try (Connection conn = Connect.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, type);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    ArtObject object = new ArtObject(
                            rs.getInt("Object_ID"),
                            rs.getString("Name_object"),
                            rs.getString("type"));
                    objects.add(object);
                }
            }
        }

        return objects;
    }
}
