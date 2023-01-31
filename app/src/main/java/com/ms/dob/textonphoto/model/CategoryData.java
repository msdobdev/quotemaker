package com.ms.dob.textonphoto.model;

public class CategoryData {
    public String CategoryName = "";
    public String CategotyImageName = "";
    public String NumberOfStatus = "";
    public String Status = "";

    public String getCategoryName() {
        return this.CategoryName;
    }

    public String getNumberOfStatus() {
        return this.NumberOfStatus;
    }

    public String getCategotyImageName() {
        return this.CategotyImageName;
    }

    public String getStatus() {
        return this.Status;
    }

    public void setCategoryName(String str) {
        this.CategoryName = str;
    }

    public void setNumberOfStatus(String str) {
        this.NumberOfStatus = str;
    }

    public void setCategotyImageName(String str) {
        this.CategotyImageName = str;
    }

    public void setStatus(String str) {
        this.Status = str;
    }
}
