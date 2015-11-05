
package com.proj.andoid.localnews.model.flickr_response.flickrgetcomments;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Comment {

    private String id;
    private String author;
    private String authorname;
    private String iconserver;
    private Integer iconfarm;
    private String datecreate;
    private String permalink;
    private String pathAlias;
    private String realname;
    private String Content;
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
     *     The author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 
     * @param author
     *     The author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 
     * @return
     *     The authorname
     */
    public String getAuthorname() {
        return authorname;
    }

    /**
     * 
     * @param authorname
     *     The authorname
     */
    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    /**
     * 
     * @return
     *     The iconserver
     */
    public String getIconserver() {
        return iconserver;
    }

    /**
     * 
     * @param iconserver
     *     The iconserver
     */
    public void setIconserver(String iconserver) {
        this.iconserver = iconserver;
    }

    /**
     * 
     * @return
     *     The iconfarm
     */
    public Integer getIconfarm() {
        return iconfarm;
    }

    /**
     * 
     * @param iconfarm
     *     The iconfarm
     */
    public void setIconfarm(Integer iconfarm) {
        this.iconfarm = iconfarm;
    }

    /**
     * 
     * @return
     *     The datecreate
     */
    public String getDatecreate() {
        return datecreate;
    }

    /**
     * 
     * @param datecreate
     *     The datecreate
     */
    public void setDatecreate(String datecreate) {
        this.datecreate = datecreate;
    }

    /**
     * 
     * @return
     *     The permalink
     */
    public String getPermalink() {
        return permalink;
    }

    /**
     * 
     * @param permalink
     *     The permalink
     */
    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    /**
     * 
     * @return
     *     The pathAlias
     */
    public String getPathAlias() {
        return pathAlias;
    }

    /**
     * 
     * @param pathAlias
     *     The path_alias
     */
    public void setPathAlias(String pathAlias) {
        this.pathAlias = pathAlias;
    }

    /**
     * 
     * @return
     *     The realname
     */
    public String getRealname() {
        return realname;
    }

    /**
     * 
     * @param realname
     *     The realname
     */
    public void setRealname(String realname) {
        this.realname = realname;
    }

    /**
     * 
     * @return
     *     The Content
     */
    public String getContent() {
        return Content;
    }

    /**
     * 
     * @param Content
     *     The _content
     */
    public void setContent(String Content) {
        this.Content = Content;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
