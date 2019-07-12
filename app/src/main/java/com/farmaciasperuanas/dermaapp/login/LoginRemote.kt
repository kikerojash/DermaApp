package com.farmaciasperuanas.dermaapp.login

import com.farmaciasperuanas.dermaapp.model.UserModel

class LoginRemote {

    val TAG = LoginRemote::class.java.name

    fun statusConectionSesion(usuario: UserModel): MutableList<String> {
        val userModelLive: MutableList<String> = ArrayList()
        if (usuario.userName.equals("admin") && usuario.userClave.equals("admin")) {
            userModelLive.add("Correcto")
        }else{
            userModelLive.add("Incorrecto")
        }
        return userModelLive
    }

}