package Mapper;

import Model.Availability;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AvailabilityMapper {

    public static Availability mapToAvailability(ResultSet rs) throws SQLException {
        int availabilityId = rs.getInt("Availability_ID");
        String day = rs.getString("Day");
        String startTime = rs.getString("StartTime");
        String endTime = rs.getString("EndTime");

        return new Availability(availabilityId, day, startTime, endTime);
    }

}
