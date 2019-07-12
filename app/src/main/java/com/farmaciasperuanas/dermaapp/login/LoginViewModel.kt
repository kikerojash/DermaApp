package com.farmaciasperuanas.dermaapp.login

import android.app.Application
import androidx.lifecycle.*
import com.farmaciasperuanas.dermaapp.model.UserModel

class LoginViewModel(
    application: Application,
    val loginRepository: LoginRepository
) : ViewModel() {

    val TAG = LoginViewModel::class.java.name

    val userLive = MutableLiveData<String>()
    val userMessageValidation: LiveData<String> = Transformations.map(userLive, ::temp)

    fun onUsuarioConexion(usuario: UserModel) {
        //Logger.getLogger(TAG).warning("userNameResult : " +userMessageValidation)
        var strUsuario: String = usuario.userName
        var strClave: String = usuario.userClave
        if (strUsuario.isEmpty()) {
            userLive.postValue("Usuario Vacio")
            return
        }
        if (strClave.isEmpty()) {
            userLive.postValue("Clave Vacio")
            return
        }
        initLogin(usuario)
    }


    private val idEvent = MutableLiveData<String>()

    /*val matchDetail: LiveData<Resource<Productos>> = Transformations.switchMap(idEvent) { id ->
        if (id.isNullOrBlank()) {
            AbsentLiveData.create()
        } else {
            loginRepository.getEventDetail(id)
        }
    }*/


    fun initData(idEvent: String) {
        this.idEvent.value = idEvent
    }

    private fun temp(name: String) = name

    fun initLogin(usuario: UserModel) {
        var strResultado = loginRepository.onValidarSesion(usuario).get(0)
        when (strResultado) {
            "Correcto" -> userLive.postValue("Correcto")
            "Incorrecto" -> userLive.postValue("InCorrecto")
        }
    }
}