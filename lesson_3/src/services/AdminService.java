package services;

import entities.Client;

public interface AdminService {
    void ban(Client client);
    void unban(Client client);
}
