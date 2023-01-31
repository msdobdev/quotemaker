package com.ms.dob.textonphoto.model;

public class QuotesFrameDetails {
    public String FontName = "";
    public String ImagePath = "";
    public String Quotes = "";
    public String QuotesFrameID = "";
    public String TextColor = "";

    public void setQuotes(String str) {
        this.Quotes = str;
    }

    public void setQuotesFrameID(String str) {
        this.QuotesFrameID = str;
    }

    public void setImagePath(String str) {
        this.ImagePath = str;
    }

    public void setFontName(String str) {
        this.FontName = str;
    }

    public void setTextColor(String str) {
        this.TextColor = str;
    }

    public String getQuotes() {
        return this.Quotes;
    }

    public String getQuotesFrameID() {
        return this.QuotesFrameID;
    }

    public String getImagePath() {
        return this.ImagePath;
    }

    public String getFontName() {
        return this.FontName;
    }

    public String getTextColor() {
        return this.TextColor;
    }
}
