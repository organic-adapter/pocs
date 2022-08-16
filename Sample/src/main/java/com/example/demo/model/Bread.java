package com.example.demo.model;

public class Bread implements Consumable {

    private String id;
    private String type;
    private String displayName;
    private float quantity;

    public Bread() {
    }

    public Bread(String id, String type, String displayName, float quantity) {
        this.id = id;
        this.type = type;
        this.displayName = displayName;
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public String getDisplayName() {
        return displayName;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setType(String value) {
        type = value;
    }

    public void setDisplayName(String value) {
        displayName = value;
    }

    public void setQuantity(float value) {
        quantity = value;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String value) {
        this.id = value;
    }
}
