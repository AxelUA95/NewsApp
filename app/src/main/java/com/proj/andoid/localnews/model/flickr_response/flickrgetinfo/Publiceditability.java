
package com.proj.andoid.localnews.model.flickr_response.flickrgetinfo;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Publiceditability {

    private Integer cancomment;
    private Integer canaddmeta;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The cancomment
     */
    public Integer getCancomment() {
        return cancomment;
    }

    /**
     * 
     * @param cancomment
     *     The cancomment
     */
    public void setCancomment(Integer cancomment) {
        this.cancomment = cancomment;
    }

    /**
     * 
     * @return
     *     The canaddmeta
     */
    public Integer getCanaddmeta() {
        return canaddmeta;
    }

    /**
     * 
     * @param canaddmeta
     *     The canaddmeta
     */
    public void setCanaddmeta(Integer canaddmeta) {
        this.canaddmeta = canaddmeta;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
