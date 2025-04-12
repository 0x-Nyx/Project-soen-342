package Mapper;

import java.sql.SQLException;
import java.util.List;

import Model.ArtObject;
import TDG.ObjectTDG;

public class ObjectMapper {
    public static List<ArtObject> getObjectsByType(String type) throws SQLException {
        return ObjectTDG.getObjectsByType(type);
    }

}
