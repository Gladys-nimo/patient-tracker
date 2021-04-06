
package com.moringaschool.patienttracker;

//import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//@Generated("jsonschema2pojo")
public class Attributes {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address_line_1")
    @Expose
    private String addressLine1;
    @SerializedName("address_line_2")
    @Expose
    private String addressLine2;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("phone_country_code")
    @Expose
    private String phoneCountryCode;
    @SerializedName("vendor")
    @Expose
    private String vendor;
    @SerializedName("in_person_appointment_availability_status")
    @Expose
    private String inPersonAppointmentAvailabilityStatus;
    @SerializedName("walkin_availability_status")
    @Expose
    private String walkinAvailabilityStatus;
    @SerializedName("wait_time")
    @Expose
    private Integer waitTime;
    @SerializedName("distance")
    @Expose
    private Double distance;
    @SerializedName("temporary_closure")
    @Expose
    private String temporaryClosure;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Attributes() {
    }

    /**
     * 
     * @param country
     * @param distance
     * @param city
     * @param latitude
     * @param temporaryClosure
     * @param phoneCountryCode
     * @param phone
     * @param inPersonAppointmentAvailabilityStatus
     * @param walkinAvailabilityStatus
     * @param vendor
     * @param imageUrl
     * @param name
     * @param addressLine1
     * @param addressLine2
     * @param state
     * @param waitTime
     * @param longitude
     */
    public Attributes(String name, String addressLine1, String addressLine2, String city, String state, String country, Double latitude, Double longitude, String imageUrl, String phone, String phoneCountryCode, String vendor, String inPersonAppointmentAvailabilityStatus, String walkinAvailabilityStatus, Integer waitTime, Double distance, String temporaryClosure) {
        super();
        this.name = name;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imageUrl = imageUrl;
        this.phone = phone;
        this.phoneCountryCode = phoneCountryCode;
        this.vendor = vendor;
        this.inPersonAppointmentAvailabilityStatus = inPersonAppointmentAvailabilityStatus;
        this.walkinAvailabilityStatus = walkinAvailabilityStatus;
        this.waitTime = waitTime;
        this.distance = distance;
        this.temporaryClosure = temporaryClosure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneCountryCode() {
        return phoneCountryCode;
    }

    public void setPhoneCountryCode(String phoneCountryCode) {
        this.phoneCountryCode = phoneCountryCode;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getInPersonAppointmentAvailabilityStatus() {
        return inPersonAppointmentAvailabilityStatus;
    }

    public void setInPersonAppointmentAvailabilityStatus(String inPersonAppointmentAvailabilityStatus) {
        this.inPersonAppointmentAvailabilityStatus = inPersonAppointmentAvailabilityStatus;
    }

    public String getWalkinAvailabilityStatus() {
        return walkinAvailabilityStatus;
    }

    public void setWalkinAvailabilityStatus(String walkinAvailabilityStatus) {
        this.walkinAvailabilityStatus = walkinAvailabilityStatus;
    }

    public Integer getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(Integer waitTime) {
        this.waitTime = waitTime;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getTemporaryClosure() {
        return temporaryClosure;
    }

    public void setTemporaryClosure(String temporaryClosure) {
        this.temporaryClosure = temporaryClosure;
    }

}
