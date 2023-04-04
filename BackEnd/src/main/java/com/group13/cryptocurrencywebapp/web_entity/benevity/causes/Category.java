package com.group13.cryptocurrencywebapp.web_entity.benevity.causes;

public class Category {
    private String category_name;
    private String category_code;
    private String subcategory_name;
    private String subcategory_code;

    public Category() {

    }

    public Category(String category_name, String category_code, String subcategory_name, String subcategory_code) {
        this.category_name = category_name;
        this.category_code = category_code;
        this.subcategory_name = subcategory_name;
        this.subcategory_code = subcategory_code;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_code() {
        return category_code;
    }

    public void setCategory_code(String category_code) {
        this.category_code = category_code;
    }

    public String getSubcategory_name() {
        return subcategory_name;
    }

    public void setSubcategory_name(String subcategory_name) {
        this.subcategory_name = subcategory_name;
    }

    public String getSubcategory_code() {
        return subcategory_code;
    }

    public void setSubcategory_code(String subcategory_code) {
        this.subcategory_code = subcategory_code;
    }

}
