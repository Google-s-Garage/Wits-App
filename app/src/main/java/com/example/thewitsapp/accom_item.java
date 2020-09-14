package com.example.thewitsapp;

public class accom_item {

    int background,accomLogo , accomPrice;
    String accomName;

    public accom_item() {
    }

    public accom_item(int background, int accomLogo, int accomPrice, String accomName) {
        this.background = background;
        this.accomLogo = accomLogo;
        this.accomPrice = accomPrice;
        this.accomName = accomName;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public int getAccomLogo() {
        return accomLogo;
    }

    public void setAccomLogo(int accomLogo) {
        this.accomLogo = accomLogo;
    }

    public int getAccomPrice() {
        return accomPrice;
    }

    public void setAccomPrice(int accomPrice) {
        this.accomPrice = accomPrice;
    }

    public String getAccomName() {
        return accomName;
    }

    public void setAccomName(String accomName) {
        this.accomName = accomName;
    }
}
