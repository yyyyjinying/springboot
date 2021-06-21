package com.monkey1024.autoconfigure;

public class UserService {

    private UserProperties userProperties;

    public UserService(UserProperties userProperties) {
        this.userProperties = userProperties;
    }

    public UserService() {
    }

    public boolean validate() {
        if ("admin".equals(userProperties.getName()) && "123".equals(userProperties.getPassword())){
            return true;
        }
        return false;
    }
}
