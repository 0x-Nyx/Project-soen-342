package Mapper;

import java.sql.SQLException;

import java.util.List;

import Model.Client;
import Model.ObjectOfInterest;

import TDG.ObjectOfInterestTDG;

public class ObjectOfInterestMapper {

    public static void save(ObjectOfInterest obj) throws Exception {
        ObjectOfInterestTDG.insertObjectOfInterest(obj);
    }

    public static List<ObjectOfInterest> viewAllInterestsWithMatches(int clientId) throws SQLException {

        return ObjectOfInterestTDG.getInterestsWithMatchesByClient(clientId);

    }

    public static List<Client> getClientsForExpert(int expertId) throws SQLException {

        return ObjectOfInterestTDG.getClientsForExpert(expertId);

    }

    public static List<ObjectOfInterest> getInterestsForClient(int expertId) throws SQLException {

        return ObjectOfInterestTDG.getInterestsForClient(expertId);

    }

    public static boolean deleteObjectOfInterest(int objectID) {
        return ObjectOfInterestTDG.deleteObjectOfInterest(objectID);
    }

    public static List<ObjectOfInterest> getInterestsWithMatchesByClient(int objectID) throws SQLException {
        return ObjectOfInterestTDG.getInterestsWithMatchesByClient(objectID);
    }

}
