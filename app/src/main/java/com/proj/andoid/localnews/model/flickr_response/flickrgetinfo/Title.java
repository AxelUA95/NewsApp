
package com.proj.andoid.localnews.model.flickr_response.flickrgetinfo;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Title {

    private String Content;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
