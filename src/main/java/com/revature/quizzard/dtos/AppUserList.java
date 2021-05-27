package com.revature.quizzard.dtos;

import com.revature.quizzard.models.AppUser;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="users")
public class AppUserList {
    
    private List<AppUser> users;
    
    public AppUserList() {
        super();
    }
    
    public AppUserList(List<AppUser> users) {
        this.users = users;
    }
    
    public List<AppUser> getUsers() {
        return users;
    }
    
    public void setUsers(List<AppUser> users) {
        this.users = users;
    }
}
