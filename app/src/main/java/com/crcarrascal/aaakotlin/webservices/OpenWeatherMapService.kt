package com.crcarrascal.aaakotlin.webservices

import com.crcarrascal.aaakotlin.webservices.models.OpenWeatherMapServiceModel
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by crcarrascal on 10/07/2018.
 */

interface OpenWeatherMapService{

    @GET("data/2.5/forecast/daily")
    fun search(@Query("APPID") APPID: String,
               @Query("id") id: Int,
               @Query("mode") mode : String,
               @Query("units") units : String,
               @Query("cnt") cnt: Int) : Observable<OpenWeatherMapServiceModel>

    companion object Factory{
        fun create() : OpenWeatherMapService{
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://api.openweathermap.org")
                    .build()

            return retrofit.create(OpenWeatherMapService::class.java)
        }
    }

}
