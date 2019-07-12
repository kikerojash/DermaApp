package com.farmaciasperuanas.dermaapp.api


import androidx.lifecycle.LiveData
import com.farmaciasperuanas.dermaapp.base.ApiResponse
import com.farmaciasperuanas.dermaapp.login.service.LoginService
import com.farmaciasperuanas.dermaapp.main.categorias.remote.CategoriasResponse
import com.farmaciasperuanas.dermaapp.main.promocion.json.SchedulesResponse
import com.farmaciasperuanas.dermaapp.model.Categorias
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/*
interface DermaService {

    fun loginService(): LoginService;

}
*/
interface DermaService {


    @GET("eventspastleague.php")
    fun getLastMatch(@Query("id") leagueId: String): LiveData<ApiResponse<SchedulesResponse>>

    @GET("lookupevent.php")
    fun getMatchDetail(@Query("id") matchId: String): LiveData<ApiResponse<SchedulesResponse>>

    @GET("productoLista")
    fun obtenerProductoListaService(): LiveData<ApiResponse<SchedulesResponse>>

    @GET("categoriasLista")
    fun obtenerCategoriasListaService(): LiveData<ApiResponse<CategoriasResponse>>
}
