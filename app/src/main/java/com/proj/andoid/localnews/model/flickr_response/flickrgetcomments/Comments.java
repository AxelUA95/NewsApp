
package com.proj.andoid.localnews.model.flickr_response.flickrgetcomments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Comments {

    private String photoId;
    private List<Comment> comment = new ArrayList<Comment>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The photoId
     */
    public String getPhotoId() {
        return photoId;
    }

    /**
     * 
     * @param photoId
     *     The photo_id
     */
    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    /**
     * 
     * @return
     *     The comment
     */
    public List<Comment> getComment() {
        return comment;
    }

    /**
     * 
     * @param comment
     *     The comment
     */
    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
