package com.moringaschool.patienttracker;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HealthTapApi {
   @GET("/api/v2.1/clinics/{id}")
   Call<List<HealthTapClinicResponse>> getName();


}
