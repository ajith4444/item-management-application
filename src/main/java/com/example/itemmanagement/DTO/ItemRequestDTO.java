package com.example.itemmanagement.DTO;

public class ItemRequestDTO {

    private  String name;
    private  double price;

    public ItemRequestDTO(String name, double price){
        this.name = name;
        this.price=price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
