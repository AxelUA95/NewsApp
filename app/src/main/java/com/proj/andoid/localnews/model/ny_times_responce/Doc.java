
package com.proj.andoid.localnews.model.ny_times_responce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Doc {

    private String webUrl;
    private String snippet;
    private String leadParagraph;
    private String _abstract;
    private String printPage;
    private List<Object> blog = new ArrayList<Object>();
    private String source;
    private List<Object> multimedia = new ArrayList<Object>();
    private Headline headline;
    private List<Keyword> keywords = new ArrayList<Keyword>();
    private String pubDate;
    private String documentType;
    private String newsDesk;
    private String sectionName;
    private Object subsectionName;
    private Byline byline;
    private String typeOfMaterial;
    private String Id;
    private int wordCount;
    private Object slideshowCredits;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Doc() {
    }

    /**
     * 
     * @param headline
     * @param webUrl
     * @param pubDate
     * @param slideshowCredits
     * @param keywords
     * @param documentType
     * @param leadParagraph
     * @param newsDesk
     * @param sectionName
     * @param snippet
     * @param wordCount
     * @param blog
     * @param typeOfMaterial
     * @param byline
     * @param multimedia
     * @param printPage
     * @param source
     * @param _abstract
     * @param Id
     * @param subsectionName
     */
    public Doc(String webUrl, String snippet, String leadParagraph, String _abstract, String printPage, List<Object> blog, String source, List<Object> multimedia, Headline headline, List<Keyword> keywords, String pubDate, String documentType, String newsDesk, String sectionName, Object subsectionName, Byline byline, String typeOfMaterial, String Id, int wordCount, Object slideshowCredits) {
        this.webUrl = webUrl;
        this.snippet = snippet;
        this.leadParagraph = leadParagraph;
        this._abstract = _abstract;
        this.printPage = printPage;
        this.blog = blog;
        this.source = source;
        this.multimedia = multimedia;
        this.headline = headline;
        this.keywords = keywords;
        this.pubDate = pubDate;
        this.documentType = documentType;
        this.newsDesk = newsDesk;
        this.sectionName = sectionName;
        this.subsectionName = subsectionName;
        this.byline = byline;
        this.typeOfMaterial = typeOfMaterial;
        this.Id = Id;
        this.wordCount = wordCount;
        this.slideshowCredits = slideshowCredits;
    }

    /**
     * 
     * @return
     *     The webUrl
     */
    public String getWebUrl() {
        return webUrl;
    }

    /**
     * 
     * @param webUrl
     *     The web_url
     */
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    /**
     * 
     * @return
     *     The snippet
     */
    public String getSnippet() {
        return snippet;
    }

    /**
     * 
     * @param snippet
     *     The snippet
     */
    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    /**
     * 
     * @return
     *     The leadParagraph
     */
    public String getLeadParagraph() {
        return leadParagraph;
    }

    /**
     * 
     * @param leadParagraph
     *     The lead_paragraph
     */
    public void setLeadParagraph(String leadParagraph) {
        this.leadParagraph = leadParagraph;
    }

    /**
     * 
     * @return
     *     The _abstract
     */
    public String getAbstract() {
        return _abstract;
    }

    /**
     * 
     * @param _abstract
     *     The abstract
     */
    public void setAbstract(String _abstract) {
        this._abstract = _abstract;
    }

    /**
     * 
     * @return
     *     The printPage
     */
    public String getPrintPage() {
        return printPage;
    }

    /**
     * 
     * @param printPage
     *     The print_page
     */
    public void setPrintPage(String printPage) {
        this.printPage = printPage;
    }

    /**
     * 
     * @return
     *     The blog
     */
    public List<Object> getBlog() {
        return blog;
    }

    /**
     * 
     * @param blog
     *     The blog
     */
    public void setBlog(List<Object> blog) {
        this.blog = blog;
    }

    /**
     * 
     * @return
     *     The source
     */
    public String getSource() {
        return source;
    }

    /**
     * 
     * @param source
     *     The source
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 
     * @return
     *     The multimedia
     */
    public List<Object> getMultimedia() {
        return multimedia;
    }

    /**
     * 
     * @param multimedia
     *     The multimedia
     */
    public void setMultimedia(List<Object> multimedia) {
        this.multimedia = multimedia;
    }

    /**
     * 
     * @return
     *     The headline
     */
    public Headline getHeadline() {
        return headline;
    }

    /**
     * 
     * @param headline
     *     The headline
     */
    public void setHeadline(Headline headline) {
        this.headline = headline;
    }

    /**
     * 
     * @return
     *     The keywords
     */
    public List<Keyword> getKeywords() {
        return keywords;
    }

    /**
     * 
     * @param keywords
     *     The keywords
     */
    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

    /**
     * 
     * @return
     *     The pubDate
     */
    public String getPubDate() {
        return pubDate;
    }

    /**
     * 
     * @param pubDate
     *     The pub_date
     */
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    /**
     * 
     * @return
     *     The documentType
     */
    public String getDocumentType() {
        return documentType;
    }

    /**
     * 
     * @param documentType
     *     The document_type
     */
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    /**
     * 
     * @return
     *     The newsDesk
     */
    public String getNewsDesk() {
        return newsDesk;
    }

    /**
     * 
     * @param newsDesk
     *     The news_desk
     */
    public void setNewsDesk(String newsDesk) {
        this.newsDesk = newsDesk;
    }

    /**
     * 
     * @return
     *     The sectionName
     */
    public String getSectionName() {
        return sectionName;
    }

    /**
     * 
     * @param sectionName
     *     The section_name
     */
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    /**
     * 
     * @return
     *     The subsectionName
     */
    public Object getSubsectionName() {
        return subsectionName;
    }

    /**
     * 
     * @param subsectionName
     *     The subsection_name
     */
    public void setSubsectionName(Object subsectionName) {
        this.subsectionName = subsectionName;
    }

    /**
     * 
     * @return
     *     The byline
     */
    public Byline getByline() {
        return byline;
    }

    /**
     * 
     * @param byline
     *     The byline
     */
    public void setByline(Byline byline) {
        this.byline = byline;
    }

    /**
     * 
     * @return
     *     The typeOfMaterial
     */
    public String getTypeOfMaterial() {
        return typeOfMaterial;
    }

    /**
     * 
     * @param typeOfMaterial
     *     The type_of_material
     */
    public void setTypeOfMaterial(String typeOfMaterial) {
        this.typeOfMaterial = typeOfMaterial;
    }

    /**
     * 
     * @return
     *     The Id
     */
    public String getId() {
        return Id;
    }

    /**
     * 
     * @param Id
     *     The _id
     */
    public void setId(String Id) {
        this.Id = Id;
    }

    /**
     * 
     * @return
     *     The wordCount
     */
    public int getWordCount() {
        return wordCount;
    }

    /**
     * 
     * @param wordCount
     *     The word_count
     */
    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    /**
     * 
     * @return
     *     The slideshowCredits
     */
    public Object getSlideshowCredits() {
        return slideshowCredits;
    }

    /**
     * 
     * @param slideshowCredits
     *     The slideshow_credits
     */
    public void setSlideshowCredits(Object slideshowCredits) {
        this.slideshowCredits = slideshowCredits;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
