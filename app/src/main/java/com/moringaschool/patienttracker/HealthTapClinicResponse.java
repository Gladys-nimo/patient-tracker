
package com.moringaschool.patienttracker;

//import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//@Generated("jsonschema2pojo")
public class HealthTapClinicResponse {

    @SerializedName("data")
    @Expose
    private Data data;

    /**
     * No args constructor for use in serialization
     * 
     */
    public HealthTapClinicResponse() {
    }

    /**
     * 
     * @param data
     */
    public HealthTapClinicResponse(Data data) {
        super();
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
