
package com.moringaschool.patienttracker;

//import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//@Generated("jsonschema2pojo")
public class Data {

    @SerializedName("data")
    @Expose
    private Data__1 data;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data() {
    }

    /**
     * 
     * @param data
     */
    public Data(Data__1 data) {
        super();
        this.data = data;
    }

    public Data__1 getData() {
        return data;
    }

    public void setData(Data__1 data) {
        this.data = data;
    }

}
