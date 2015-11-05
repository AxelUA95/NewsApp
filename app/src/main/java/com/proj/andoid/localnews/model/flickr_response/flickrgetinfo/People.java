
package com.proj.andoid.localnews.model.flickr_response.flickrgetinfo;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class People {

    private Integer haspeople;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The haspeople
     */
    public Integer getHaspeople() {
        return haspeople;
    }

    /**
     * 
     * @param haspeople
     *     The haspeople
     */
    public void setHaspeople(Integer haspeople) {
        this.haspeople = haspeople;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
