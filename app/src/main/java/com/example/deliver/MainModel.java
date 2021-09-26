package com.example.deliver;

public class MainModel {

    String address,email,id,image,name,tpno;
    MainModel()
    {

    }

    public MainModel(String address, String email, String id, String image, String name, String tpno) {
        this.address = address;
        this.email = email;
        this.id = id;
        this.image = image;
        this.name = name;
        this.tpno = tpno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTpno() {
        return tpno;
    }

    public void setTpno(String tpno) {
        this.tpno = tpno;
    }
}
