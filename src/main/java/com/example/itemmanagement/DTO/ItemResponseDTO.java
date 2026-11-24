package com.example.itemmanagement.DTO;

public class ItemResponseDTO {

    private final Integer id;
    private final String name;

    public ItemResponseDTO(Integer id, String name){
        this.id=id;
        this.name=name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
