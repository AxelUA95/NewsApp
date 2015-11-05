
package com.proj.andoid.localnews.model.flickr_response.flickrgetinfo;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class FlickrGetInfo {

    private Photo photo;
    private String stat;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The photo
     */
    public Photo getPhoto() {
        return photo;
    }

    /**
     * 
     * @param photo
     *     The photo
     */
    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    /**
     * 
     * @return
     *     The stat
     */
    public String getStat() {
        return stat;
    }

    /**
     * 
     * @param stat
     *     The stat
     */
    public void setStat(String stat) {
        this.stat = stat;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
