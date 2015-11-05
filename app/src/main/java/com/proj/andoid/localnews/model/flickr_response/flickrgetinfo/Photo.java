
package com.proj.andoid.localnews.model.flickr_response.flickrgetinfo;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Photo {

    private String id;
    private String secret;
    private String server;
    private Integer farm;
    private String dateuploaded;
    private Integer isfavorite;
    private Integer license;
    private Integer safetyLevel;
    private Integer rotation;
    private String originalsecret;
    private String originalformat;
    private Owner owner;
    private Title title;
    private Description description;
    private Visibility visibility;
    private Dates dates;
    private Integer views;
    private Editability editability;
    private Publiceditability publiceditability;
    private Usage usage;
    private Comments comments;
    private Notes notes;
    private People people;
    private Tags tags;
    private Urls urls;
    private String media;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The secret
     */
    public String getSecret() {
        return secret;
    }

    /**
     * 
     * @param secret
     *     The secret
     */
    public void setSecret(String secret) {
        this.secret = secret;
    }

    /**
     * 
     * @return
     *     The server
     */
    public String getServer() {
        return server;
    }

    /**
     * 
     * @param server
     *     The server
     */
    public void setServer(String server) {
        this.server = server;
    }

    /**
     * 
     * @return
     *     The farm
     */
    public Integer getFarm() {
        return farm;
    }

    /**
     * 
     * @param farm
     *     The farm
     */
    public void setFarm(Integer farm) {
        this.farm = farm;
    }

    /**
     * 
     * @return
     *     The dateuploaded
     */
    public String getDateuploaded() {
        return dateuploaded;
    }

    /**
     * 
     * @param dateuploaded
     *     The dateuploaded
     */
    public void setDateuploaded(String dateuploaded) {
        this.dateuploaded = dateuploaded;
    }

    /**
     * 
     * @return
     *     The isfavorite
     */
    public Integer getIsfavorite() {
        return isfavorite;
    }

    /**
     * 
     * @param isfavorite
     *     The isfavorite
     */
    public void setIsfavorite(Integer isfavorite) {
        this.isfavorite = isfavorite;
    }

    /**
     * 
     * @return
     *     The license
     */
    public Integer getLicense() {
        return license;
    }

    /**
     * 
     * @param license
     *     The license
     */
    public void setLicense(Integer license) {
        this.license = license;
    }

    /**
     * 
     * @return
     *     The safetyLevel
     */
    public Integer getSafetyLevel() {
        return safetyLevel;
    }

    /**
     * 
     * @param safetyLevel
     *     The safety_level
     */
    public void setSafetyLevel(Integer safetyLevel) {
        this.safetyLevel = safetyLevel;
    }

    /**
     * 
     * @return
     *     The rotation
     */
    public Integer getRotation() {
        return rotation;
    }

    /**
     * 
     * @param rotation
     *     The rotation
     */
    public void setRotation(Integer rotation) {
        this.rotation = rotation;
    }

    /**
     * 
     * @return
     *     The originalsecret
     */
    public String getOriginalsecret() {
        return originalsecret;
    }

    /**
     * 
     * @param originalsecret
     *     The originalsecret
     */
    public void setOriginalsecret(String originalsecret) {
        this.originalsecret = originalsecret;
    }

    /**
     * 
     * @return
     *     The originalformat
     */
    public String getOriginalformat() {
        return originalformat;
    }

    /**
     * 
     * @param originalformat
     *     The originalformat
     */
    public void setOriginalformat(String originalformat) {
        this.originalformat = originalformat;
    }

    /**
     * 
     * @return
     *     The owner
     */
    public Owner getOwner() {
        return owner;
    }

    /**
     * 
     * @param owner
     *     The owner
     */
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    /**
     * 
     * @return
     *     The title
     */
    public Title getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(Title title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The description
     */
    public Description getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(Description description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The visibility
     */
    public Visibility getVisibility() {
        return visibility;
    }

    /**
     * 
     * @param visibility
     *     The visibility
     */
    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    /**
     * 
     * @return
     *     The dates
     */
    public Dates getDates() {
        return dates;
    }

    /**
     * 
     * @param dates
     *     The dates
     */
    public void setDates(Dates dates) {
        this.dates = dates;
    }

    /**
     * 
     * @return
     *     The views
     */
    public Integer getViews() {
        return views;
    }

    /**
     * 
     * @param views
     *     The views
     */
    public void setViews(Integer views) {
        this.views = views;
    }

    /**
     * 
     * @return
     *     The editability
     */
    public Editability getEditability() {
        return editability;
    }

    /**
     * 
     * @param editability
     *     The editability
     */
    public void setEditability(Editability editability) {
        this.editability = editability;
    }

    /**
     * 
     * @return
     *     The publiceditability
     */
    public Publiceditability getPubliceditability() {
        return publiceditability;
    }

    /**
     * 
     * @param publiceditability
     *     The publiceditability
     */
    public void setPubliceditability(Publiceditability publiceditability) {
        this.publiceditability = publiceditability;
    }

    /**
     * 
     * @return
     *     The usage
     */
    public Usage getUsage() {
        return usage;
    }

    /**
     * 
     * @param usage
     *     The usage
     */
    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    /**
     * 
     * @return
     *     The comments
     */
    public Comments getComments() {
        return comments;
    }

    /**
     * 
     * @param comments
     *     The comments
     */
    public void setComments(Comments comments) {
        this.comments = comments;
    }

    /**
     * 
     * @return
     *     The notes
     */
    public Notes getNotes() {
        return notes;
    }

    /**
     * 
     * @param notes
     *     The notes
     */
    public void setNotes(Notes notes) {
        this.notes = notes;
    }

    /**
     * 
     * @return
     *     The people
     */
    public People getPeople() {
        return people;
    }

    /**
     * 
     * @param people
     *     The people
     */
    public void setPeople(People people) {
        this.people = people;
    }

    /**
     * 
     * @return
     *     The tags
     */
    public Tags getTags() {
        return tags;
    }

    /**
     * 
     * @param tags
     *     The tags
     */
    public void setTags(Tags tags) {
        this.tags = tags;
    }

    /**
     * 
     * @return
     *     The urls
     */
    public Urls getUrls() {
        return urls;
    }

    /**
     * 
     * @param urls
     *     The urls
     */
    public void setUrls(Urls urls) {
        this.urls = urls;
    }

    /**
     * 
     * @return
     *     The media
     */
    public String getMedia() {
        return media;
    }

    /**
     * 
     * @param media
     *     The media
     */
    public void setMedia(String media) {
        this.media = media;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
