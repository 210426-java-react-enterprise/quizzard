package com.revature.quizzard.dtos;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="users")
public class AppUserList {

    private List<AppUserDTO> users;

    public AppUserList() {
        super();
    }

    public AppUserList(List<AppUserDTO> users) {
        this.users = users;
    }

    public List<AppUserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<AppUserDTO> users) {
        this.users = users;
    }
}
