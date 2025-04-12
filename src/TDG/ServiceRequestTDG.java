package TDG;

import Model.ServiceRequest;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import DB.Connect;

public class ServiceRequestTDG {
    private static final String INSERT_REQUEST_SQL = "INSERT INTO Request (Client_ID, Request_date, Request_start_time, Request_end_time, Request_type_ID, Status) VALUES (?, ?, ?, ?, ?,?)";

    public static boolean insertRequest(ServiceRequest request) {
        try (Connection conn = Connect.connect();
                PreparedStatement stmt = conn.prepareStatement(INSERT_REQUEST_SQL,
                        PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, request.getClientId());
            stmt.setDate(2, Date.valueOf(request.getRequestDate()));
            stmt.setTime(3, Time.valueOf(request.getRequestStartTime()));
            stmt.setTime(4, Time.valueOf(request.getRequestEndTime()));
            stmt.setInt(5, request.getRequestTypeId());
            stmt.setString(6, request.getStatus());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Inserting request failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    request.setRequestId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Inserting request failed, no ID obtained.");
                }
            }

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<String> findAllServices() {
        List<String> services = new ArrayList<>();

        String sql = "SELECT * FROM Service";

        try (Connection conn = Connect.connect();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int serviceId = rs.getInt("Service_ID");
                String type = rs.getString("Type");
                services.add("ID: " + serviceId + " | Type: " + type);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return services;
    }

    public static List<ServiceRequest> findAllPendingRequests() {
        List<ServiceRequest> requests = new ArrayList<>();
        String sql = """
                    SELECT r.Request_ID, r.Client_ID, r.Expert_ID, r.Request_date, r.Request_start_time, r.Request_end_time, r.Request_type_ID, r.Status, s.Type AS service_type
                    FROM Request r
                    JOIN Service s ON r.Request_type_ID = s.Service_ID
                    WHERE r.Status = 'pending'
                """;

        try (Connection conn = Connect.connect();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int requestId = rs.getInt("Request_ID");
                int clientId = rs.getInt("Client_ID");
                int expertId = rs.getInt("Expert_ID");
                LocalDate requestDate = rs.getDate("Request_date").toLocalDate();
                LocalTime requestStartTime = rs.getTime("Request_start_time").toLocalTime();
                LocalTime requestEndTime = rs.getTime("Request_end_time").toLocalTime();
                int requestTypeId = rs.getInt("Request_type_ID");
                String status = rs.getString("Status");
                String serviceType = rs.getString("service_type");

                ServiceRequest request = new ServiceRequest(clientId, expertId, requestDate, requestStartTime,
                        requestEndTime, requestTypeId, status);
                request.setRequestId(requestId);

                System.out.printf(
                        "Request ID: %d, Date: %s, Start Time: %s, End Time: %s, Service Type: %s%n",
                        requestId, clientId, requestDate, requestStartTime,
                        requestEndTime, serviceType);

                requests.add(request);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return requests;
    }

    public static boolean acceptRequest(int requestId, int expertId) {
        String sql = "UPDATE Request SET Status = 'accepted', Expert_ID = ? WHERE Request_ID = ?";

        try (Connection conn = Connect.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, expertId);
            stmt.setInt(2, requestId);
            int rowsUpdated = stmt.executeUpdate();

            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean hasOverlappingRequest(int clientId, LocalDate date, LocalTime startTime, LocalTime endTime) {
        String query = """
                    SELECT Request_start_time, Request_end_time FROM Request
                    WHERE Client_ID = ? AND Request_date = ?
                """;

        try (Connection conn = Connect.connect();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, clientId);
            stmt.setDate(2, java.sql.Date.valueOf(date));

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                LocalTime existingStart = rs.getTime("Request_start_time").toLocalTime();
                LocalTime existingEnd = rs.getTime("Request_end_time").toLocalTime();

                boolean overlaps = !(endTime.isBefore(existingStart) || startTime.isAfter(existingEnd));
                if (overlaps) {
                    return true;
                }
            }

        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }

        return false;
    }

    public static List<ServiceRequest> findAllRequests(int expertId) {
        List<ServiceRequest> requests = new ArrayList<>();
        String sql = """
                    SELECT r.Request_ID, r.Client_ID, r.Expert_ID, r.Request_date,
                           r.Request_start_time, r.Request_end_time, r.Request_type_ID,
                           r.Status, s.Type AS service_type
                    FROM Request r
                    JOIN Service s ON r.Request_type_ID = s.Service_ID
                    WHERE r.Expert_ID = ?
                """;

        try (Connection conn = Connect.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, expertId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int requestId = rs.getInt("Request_ID");
                    int clientId = rs.getInt("Client_ID");
                    int expert = rs.getInt("Expert_ID");
                    LocalDate requestDate = rs.getDate("Request_date").toLocalDate();
                    LocalTime requestStartTime = rs.getTime("Request_start_time").toLocalTime();
                    LocalTime requestEndTime = rs.getTime("Request_end_time").toLocalTime();
                    int requestTypeId = rs.getInt("Request_type_ID");
                    String status = rs.getString("Status");
                    String serviceType = rs.getString("service_type");

                    ServiceRequest request = new ServiceRequest(clientId, expert, requestDate, requestStartTime,
                            requestEndTime, requestTypeId, status);
                    request.setRequestId(requestId);

                    System.out.printf(
                            "Request ID: %d | Client ID: %d | Date: %s | Start Time: %s | End Time: %s | Service Type: %s%n",
                            requestId, clientId, requestDate, requestStartTime, requestEndTime, serviceType);

                    requests.add(request);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return requests;
    }

}
