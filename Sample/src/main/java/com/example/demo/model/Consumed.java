package com.example.demo.model;

public class Consumed<T extends Consumable> {
    private T consumable;
    private String message;
    private Boolean deleted;

    public Consumed() {
    }

    public Consumed(T consumable, String message, Boolean deleted) {
        this.consumable = consumable;
        this.message = message;
        this.deleted = deleted;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public T getConsumable() {
        return consumable;
    }

    public void setConsumable(T consumable) {
        this.consumable = consumable;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
