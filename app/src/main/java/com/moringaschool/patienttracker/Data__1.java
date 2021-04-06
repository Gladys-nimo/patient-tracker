
package com.moringaschool.patienttracker;

//import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//@Generated("jsonschema2pojo")
public class Data__1 {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("attributes")
    @Expose
    private Attributes attributes;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data__1() {
    }

    /**
     * 
     * @param attributes
     * @param id
     * @param type
     */
    public Data__1(String type, String id, Attributes attributes) {
        super();
        this.type = type;
        this.id = id;
        this.attributes = attributes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

}
