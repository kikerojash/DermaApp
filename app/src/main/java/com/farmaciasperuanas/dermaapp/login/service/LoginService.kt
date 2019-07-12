package com.farmaciasperuanas.dermaapp.login.service

import androidx.lifecycle.LiveData
import com.farmaciasperuanas.dermaapp.base.ApiResponse
import com.farmaciasperuanas.dermaapp.main.promocion.json.SchedulesResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface LoginService {


    @GET("eventspastleague.php")
    fun getLastMatch(@Query("id") leagueId: String): LiveData<ApiResponse<SchedulesResponse>>
}