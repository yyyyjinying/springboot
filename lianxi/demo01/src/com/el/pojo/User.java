package com.el.pojo;

public class User {

    private String name;
    private String fav;
    private Address addr;

    public User() {
    }

    public User(String name, String fav, Address addr) {
        this.name = name;
        this.fav = fav;
        this.addr = addr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFav() {
        return fav;
    }

    public void setFav(String fav) {
        this.fav = fav;
    }

    public Address getAddr() {
        return addr;
    }

    public void setAddr(Address addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", fav='" + fav + '\'' +
                ", addr=" + addr +
                '}';
    }
}
