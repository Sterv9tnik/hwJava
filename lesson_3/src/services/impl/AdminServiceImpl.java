package services.impl;

import entities.Client;
import services.AdminService;

public class AdminServiceImpl implements AdminService {
    public void ban(Client client) {
        client.setBanned(true);
    }
    public void unban(Client client){
        client.setBanned(false);
    }
}
