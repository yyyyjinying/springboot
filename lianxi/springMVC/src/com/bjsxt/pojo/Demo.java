package com.bjsxt.pojo;

public class Demo {
    private Users user;

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "user=" + user +
                '}';
    }
}
