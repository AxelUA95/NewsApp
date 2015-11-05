package com.proj.andoid.localnews.model.flickr_response.flickrgetphotos;

import com.google.gson.annotations.Expose;

/**
 * created by Alex Ivanov on 12.10.15.
 */
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


    public Photo(String id, String owner, String secret, String server, int farm, String title,
                 int ispublic, int isfriend, int isfamily) {
        this.id = id;
        this.owner = owner;
        this.secret = secret;
        this.server = server;
        this.farm = farm;
        this.title = title;
        this.ispublic = ispublic;
        this.isfriend = isfriend;
        this.isfamily = isfamily;
    }

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
