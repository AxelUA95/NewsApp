
package com.proj.andoid.localnews.model.flickr_response.flickrgetinfo;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Usage {

    private Integer candownload;
    private Integer canblog;
    private Integer canprint;
    private Integer canshare;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The candownload
     */
    public Integer getCandownload() {
        return candownload;
    }

    /**
     * 
     * @param candownload
     *     The candownload
     */
    public void setCandownload(Integer candownload) {
        this.candownload = candownload;
    }

    /**
     * 
     * @return
     *     The canblog
     */
    public Integer getCanblog() {
        return canblog;
    }

    /**
     * 
     * @param canblog
     *     The canblog
     */
    public void setCanblog(Integer canblog) {
        this.canblog = canblog;
    }

    /**
     * 
     * @return
     *     The canprint
     */
    public Integer getCanprint() {
        return canprint;
    }

    /**
     * 
     * @param canprint
     *     The canprint
     */
    public void setCanprint(Integer canprint) {
        this.canprint = canprint;
    }

    /**
     * 
     * @return
     *     The canshare
     */
    public Integer getCanshare() {
        return canshare;
    }

    /**
     * 
     * @param canshare
     *     The canshare
     */
    public void setCanshare(Integer canshare) {
        this.canshare = canshare;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
