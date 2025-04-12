package Mapper;

import Model.ServiceRequest;
import java.time.LocalDate;
import java.time.LocalTime;

public class ServiceRequestMapper {

    public static ServiceRequest createRequest(int clientId, int serviceTypeId, LocalDate requestDate,
            LocalTime requestStartTime, LocalTime requestEndTime) {
        String defaultStatus = "Pending";

        return new ServiceRequest(
                clientId,
                0,
                requestDate,
                requestStartTime,
                requestEndTime,
                serviceTypeId,
                defaultStatus);
    }
}
