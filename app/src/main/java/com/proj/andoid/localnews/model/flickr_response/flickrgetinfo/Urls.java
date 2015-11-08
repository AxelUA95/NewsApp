
package com.proj.andoid.localnews.model.flickr_response.flickrgetinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Urls {

    private List<Url> url = new ArrayList<Url>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The urlSearch
     */
    public List<Url> getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The urlSearch
     */
    public void setUrl(List<Url> url) {
        this.url = url;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
