package com.jprogrammers.core;

import java.util.List;

public class Entity {

    private String name;
    private String packageName;
    private String absolutePath;
    private List<EntityProperty> properties;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<EntityProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<EntityProperty> properties) {
        this.properties = properties;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "name='" + name + '\'' +
                ", packageName='" + packageName + '\'' +
                ", absolutePath='" + absolutePath + '\'' +
                ", properties=" + properties +
                '}';
    }
}
