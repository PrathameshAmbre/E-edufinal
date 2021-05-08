package com.example.eedu.courseDetails;

public class categoryHelperClass {

    int image;
    String title;

    public categoryHelperClass(int image, String title) {
        this.image = image;
        this.title = title;
        //this.description = description;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }
}
