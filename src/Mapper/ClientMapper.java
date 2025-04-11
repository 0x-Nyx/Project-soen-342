package Mapper;

import java.sql.SQLException;

import Model.Client;
import TDG.ClientTDG;

public class ClientMapper {
    public static boolean save(Client client) {
        if (client.getClientId() == 0) {
            int newId = ClientTDG.insert(client);
            return newId != -1;
        } else {
            return ClientTDG.update(client);
        }
    }

    public static boolean delete(Client client) throws SQLException {
        return ClientTDG.delete(client.getClientId());
    }

    public static Client load(int id) {
        return ClientTDG.findById(id);
    }
}
