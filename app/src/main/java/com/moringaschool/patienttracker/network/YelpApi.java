package com.moringaschool.patienttracker.network;

import com.moringaschool.patienttracker.models.YelpBusinessesSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YelpApi {
    @GET("businesses/search")
    Call<YelpBusinessesSearchResponse> getClinics(
            @Query("location") String location,
            @Query("term") String term
    );
}
