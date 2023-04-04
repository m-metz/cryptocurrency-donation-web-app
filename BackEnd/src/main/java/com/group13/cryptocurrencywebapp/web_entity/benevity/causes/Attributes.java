package com.group13.cryptocurrencywebapp.web_entity.benevity.causes;

import java.util.List;

public class Attributes {
    private String parent_id;
    private String parent_name;
    private String name;
    private String description;
    private String city;
    private String logo;
    private List<Category> categories;

    public Attributes() {

    }

    public Attributes(String parent_id, String parent_name, String name, String description, String city, String logo,
            List<Category> categories) {
        this.parent_id = parent_id;
        this.parent_name = parent_name;
        this.name = name;
        this.description = description;
        this.city = city;
        this.logo = logo;
        this.categories = categories;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getParent_name() {
        return parent_name;
    }

    public void setParent_name(String parent_name) {
        this.parent_name = parent_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

}
