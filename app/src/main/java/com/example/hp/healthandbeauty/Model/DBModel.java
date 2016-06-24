package com.example.hp.healthandbeauty.Model;

import com.example.hp.healthandbeauty.Activity.Ace;
import com.example.hp.healthandbeauty.Activity.Fav_deatail;
import com.example.hp.healthandbeauty.Activity.Goodskin;
import com.example.hp.healthandbeauty.Activity.Health1;
import com.example.hp.healthandbeauty.Activity.Soft;
import com.example.hp.healthandbeauty.Activity.Yogadetail;

/**
 * Created by HP on 4/4/2016.
 */
public class DBModel {
    private String ID;
    private String JID;
    private String Title;
    private String Description;
    private String Image;
   // private  Date date;
    private String Filter;
public DBModel(){

}
//    public DBModel(String id){
//        this.ID=id;
//    }
    public DBModel(String id,String jsonid, String title, String descr, String img, String tablefilter) {
        this.ID=id;
        this.JID=jsonid;
        this.Title=title;
        this.Description=descr;
        this.Image=img;
       // this.date=format;
        this.Filter=tablefilter;
    }

    public DBModel(Ace ace) {

    }

    public DBModel(String data) {

    }

    public DBModel(Fav_deatail fav_deatail) {

    }

    public DBModel(Goodskin goodskin) {

    }

    public DBModel(Soft soft) {

    }

    public DBModel(Health1 health1) {

    }

    public DBModel(Yogadetail yogadetail) {

    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getJID() {
        return JID;
    }

    public void setJID(String JID) {
        this.JID = JID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }

    public String getFilter() {
        return Filter;
    }

    public void setFilter(String filter) {
        Filter = filter;
    }

}
