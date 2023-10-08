package com.jprogrammers.core;

public class EntityProperty {

    private String name;
    private String type;

    public EntityProperty() {
    }

    public EntityProperty(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "EntityProperty{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
