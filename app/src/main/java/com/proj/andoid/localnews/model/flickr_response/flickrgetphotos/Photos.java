package com.proj.andoid.localnews.model.flickr_response.flickrgetphotos;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Alex Ivanov on 12.10.15.
 */
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
    private List<Photo> photo = new ArrayList<>();

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

    public void setPage(int page) {
        this.page = page;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public void setPerpage(int perpage) {
        this.perpage = perpage;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setPhoto(List<Photo> photo) {
        this.photo = photo;
    }
}
