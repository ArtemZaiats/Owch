package com.owch.owch.network

import com.owch.owch.data.CardResponse
import com.owch.owch.data.DefectRequestBody
import com.owch.owch.data.DefectResponse
import com.owch.owch.data.FormResponse
import com.owch.owch.data.Token
import com.owch.owch.data.VehicleResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface InspectionApiService {

    @Headers("Content-Type: application/json")
    @GET("/api/inspections/form_vehicles/")
    suspend fun getFormVehicles(
        @Header("Authorization") authorization: String,
    ): Response<VehicleResponse>

    @Headers(
        "accept: application/json",
        "Content-Type: application/x-www-form-urlencoded"
    )
    @FormUrlEncoded
    @POST("/api/login/mobile")
    suspend fun getUserToken(
        @Field("username") login: String,
        @Field("password") password: String
    ): Response<Token>

    @Headers("Content-Type: application/json")
    @GET("/api/inspections/form_list")
    suspend fun getFormList(
        @Header("Authorization") authorization: String,
        @Query("vehicle_id") vehicleId: String
    ): Response<FormResponse>

    @Headers("Content-Type: application/json")
    @GET("/api/inspections/form/{form_id}")
    suspend fun getCards(
        @Header("Authorization") authorization: String,
        @Path("form_id") formId: String
    ): Response<CardResponse>

    @Headers("Content-Type: application/json")
    @POST("/api/inspections/defect")
    suspend fun createDefect(
        @Header("Authorization") authorization: String,
        @Body body: DefectRequestBody
    ): Response<DefectResponse>
}