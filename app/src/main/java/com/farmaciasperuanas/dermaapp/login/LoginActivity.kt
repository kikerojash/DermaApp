package com.farmaciasperuanas.dermaapp.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.farmaciasperuanas.dermaapp.main.MainActivity
import com.farmaciasperuanas.dermaapp.R
import com.farmaciasperuanas.dermaapp.Utils
import com.farmaciasperuanas.dermaapp.base.Resource
import com.farmaciasperuanas.dermaapp.databinding.ActivityLoginBinding
import com.farmaciasperuanas.dermaapp.model.Productos
import com.farmaciasperuanas.dermaapp.model.UserModel
import kotlinx.android.synthetic.main.activity_login.*
import java.util.logging.Logger

class LoginActivity : AppCompatActivity() {
    lateinit var loginBinding: ActivityLoginBinding
    val TAG = LoginViewModel::class.java.name

    private val loginViewModel: LoginViewModel by viewModels {
        Utils.provideLoginViewModelFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        loginViewModel.userLive.observe(this, Observer { strMensaje ->
            when (strMensaje) {
                "Correcto" -> initStartMenu()
                else -> {
                    mostrarMensaje(strMensaje)
                }
            }
        })
        initViews()
    }

    private fun initStartMenu() {
        Logger.getLogger(TAG).warning("initStartMenu : ")
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
    }

    fun initViews() {
        buttonRegistrar.setOnClickListener { view ->
            var usuario = UserModel()
            loginBinding.userModel = usuario
            usuario.userName = loginBinding.edtUserName.text.toString()
            usuario.userClave = loginBinding.edtPassword.text.toString()
            loginViewModel.onUsuarioConexion(usuario)
        }

        loginViewModel.initData("602139")
       // loginViewModel.matchDetail.observe(this, Observer { res -> setupMatchDetail(res) })
    }
    private fun setupMatchDetail(resource: Resource<Productos>?) {

    }

    private fun mostrarMensaje(strMensaje: String?) {
        Toast.makeText(this.applicationContext, strMensaje, Toast.LENGTH_SHORT).show()
    }
}