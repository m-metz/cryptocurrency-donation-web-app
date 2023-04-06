package com.group13.cryptocurrencywebapp.web_entity.benevity.causes.detail;

import java.util.List;

public class Attributes {

    private String name;
    private String government_name;
    private String phone;
    private String website;
    private Social social;
    private Address address;
    private String caption;
    private String description;
    private boolean active;
    private boolean disbursable;
    private List<Tag> tags;
    private List<Category> categories;
    private Certification certification;
    private String claimed;
    private OwnerVerified owner_verified;
    private List<Logo> logos;

    public Attributes() {

    }

    public Attributes(String name, String government_name, String phone, String website, Social social, Address address,
            String caption, String description, boolean active, boolean disbursable, List<Tag> tags,
            List<Category> categories, Certification certification, String claimed, OwnerVerified owner_verified,
            List<Logo> logos) {
        this.name = name;
        this.government_name = government_name;
        this.phone = phone;
        this.website = website;
        this.social = social;
        this.address = address;
        this.caption = caption;
        this.description = description;
        this.active = active;
        this.disbursable = disbursable;
        this.tags = tags;
        this.categories = categories;
        this.certification = certification;
        this.claimed = claimed;
        this.owner_verified = owner_verified;
        this.logos = logos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGovernment_name() {
        return government_name;
    }

    public void setGovernment_name(String government_name) {
        this.government_name = government_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Social getSocial() {
        return social;
    }

    public void setSocial(Social social) {
        this.social = social;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isDisbursable() {
        return disbursable;
    }

    public void setDisbursable(boolean disbursable) {
        this.disbursable = disbursable;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Certification getCertification() {
        return certification;
    }

    public void setCertification(Certification certification) {
        this.certification = certification;
    }

    public String getClaimed() {
        return claimed;
    }

    public void setClaimed(String claimed) {
        this.claimed = claimed;
    }

    public OwnerVerified getOwner_verified() {
        return owner_verified;
    }

    public void setOwner_verified(OwnerVerified owner_verified) {
        this.owner_verified = owner_verified;
    }

    public List<Logo> getLogos() {
        return logos;
    }

    public void setLogos(List<Logo> logos) {
        this.logos = logos;
    }

}