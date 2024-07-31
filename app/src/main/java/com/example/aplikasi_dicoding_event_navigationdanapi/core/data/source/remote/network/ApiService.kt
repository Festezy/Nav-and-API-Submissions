package com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.network

import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.response.EventResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("events")
    fun getEvent(
        @Query("active") active: String
    ): Call<EventResponse>

//    @GET("detail/{id}")
//    fun getRestaurant(
//            @Path("id") id: String
//    ): Call<RestaurantResponse>
//    @FormUrlEncoded
//    @Headers("Authorization: token 12345")
//    @POST("review")
//    fun postReview(
//        @Field("id") id: String,
//        @Field("name") name: String,
//        @Field("review") review: String
//    ): Call<PostReviewResponse>
}