package com.moringaschool.patienttracker;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HealthTapApi {
    @GET("/clinics/{id}/schedule_entries")
    Call<HealthTapClinicResponse> getClinics(
            String location, @Query("attributes") String attributes

    );

}
