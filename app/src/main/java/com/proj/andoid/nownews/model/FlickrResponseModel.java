package com.proj.andoid.nownews.model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

/**
 * created by Alex Ivanov on 08.09.15.
 */
@Generated("org.jsonschema2pojo")
public class FlickrResponseModel {

    @Expose
    private Photos photos;
    @Expose
    private String stat;

    public Photos getPhotos() {
        return photos;
    }

    public String getStat() {
        return stat;
    }

    @Generated("org.jsonschema2pojo")
    public class Photo {

        @Expose
        private String id;
        @Expose
        private String owner;
        @Expose
        private String secret;
        @Expose
        private String server;
        @Expose
        private int farm;
        @Expose
        private String title;
        @Expose
        private int ispublic;
        @Expose
        private int isfriend;
        @Expose
        private int isfamily;


        public String getId() {
            return id;
        }

        public String getOwner() {
            return owner;
        }

        public String getSecret() {
            return secret;
        }

        public String getServer() {
            return server;
        }

        public int getFarm() {
            return farm;
        }

        public String getTitle() {
            return title;
        }

        public int getIspublic() {
            return ispublic;
        }

        public int getIsfriend() {
            return isfriend;
        }

        public int getIsfamily() {
            return isfamily;
        }
    }

    @Generated("org.jsonschema2pojo")
    public class Photos {

        @Expose
        private int page;
        @Expose
        private String pages;
        @Expose
        private int perpage;
        @Expose
        private String total;
        @Expose
        private List<Photo> photo = new ArrayList<Photo>();

        public int getPage() {
            return page;
        }

        public String getPages() {
            return pages;
        }

        public int getPerpage() {
            return perpage;
        }

        public String getTotal() {
            return total;
        }

        public List<Photo> getPhoto() {
            return photo;
        }
    }

}
