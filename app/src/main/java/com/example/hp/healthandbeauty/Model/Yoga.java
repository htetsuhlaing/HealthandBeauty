package com.example.hp.healthandbeauty.Model;

/**
 * Created by HP on 3/31/2016.
 */
public class Yoga {
    private String id;
    private String title;
    private String description;
    private String image;
    public Yoga(){

    }
    public Yoga(String id,String image,String title,String description){
        this.id=id;
        this.image=image;
        this.title=title;
        this.description=description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
