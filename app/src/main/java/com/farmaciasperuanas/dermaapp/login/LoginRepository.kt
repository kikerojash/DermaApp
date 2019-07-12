package com.farmaciasperuanas.dermaapp.login


import com.farmaciasperuanas.dermaapp.base.ContextProviders
import com.farmaciasperuanas.dermaapp.api.DermaService
import com.farmaciasperuanas.dermaapp.db.DermaDb
import com.farmaciasperuanas.dermaapp.login.dao.LoginDao
import com.farmaciasperuanas.dermaapp.model.UserModel
import com.farmaciasperuanas.dermaapp.model.Usuario


class LoginRepository private constructor(
    private val db: DermaDb,
    private val loginDao: LoginDao,
    private val dermaService: DermaService,
    private val coroutineContext: ContextProviders
) {

    val TAG = LoginRepository::class.java.name

    //  fun onValidarSesion(usuario: UserModel) = remoteLogin.statusConectionSesion(usuario)

    fun onValidarSesion(usuario: UserModel): MutableList<String> {
        val userModelLive: MutableList<String> = ArrayList()
        if (usuario.userName.equals("admin") && usuario.userClave.equals("admin")) {

            val thread = object : Thread() {
                override fun run() {
                    //your code
                    var usuario = Usuario("1", "admin")
                    loginDao.guardarUsuario(usuario)

                    // dermaService.loginService().getLastMatch("602139")
                    // dermaService.getLastMatch("602139")
                }
            }

            thread.start()

            userModelLive.add("Correcto")
        } else {
            userModelLive.add("Incorrecto")
        }
        return userModelLive
    }

   /* fun getEventDetail(matchId: String): LiveData<Resource<Productos>> {
        return object : NetworkBoundResource<Productos, SchedulesResponse>(coroutineContext) {
            override fun saveCallResult(item: SchedulesResponse) {

                Log.d(TAG, "saveCallresultt : " + item.events?.size + "")
                item.events?.let { matches ->
                    matches.forEach { match ->
                        match?.let {
                            if (match.isNextMatch()) {
                                match.matchType = "type_next_match"
                            } else {
                                match.matchType = "type_prev_match"
                            }
                        }
                    }
                    db.runInTransaction {
                        loginDao.saveMatches(matches)
                    }

                }
            }

            override fun createCall(): LiveData<ApiResponse<SchedulesResponse>> = dermaService.getMatchDetail(matchId)

            override fun shouldFetch(data: Productos?): Boolean = data == null

            override fun loadFromDb(): LiveData<Productos> = loginDao.getMatchDetail(matchId)

        }.asLiveData()
    }
*/

    companion object {
        private var INSTANCE: LoginRepository? = null

        fun getInstance(
            dermaDb: DermaDb,
            dermaService: DermaService
        ): LoginRepository = INSTANCE
            ?: synchronized(LoginRepository::class.java) {
                LoginRepository(
                    dermaDb,
                    dermaDb.loginDao(),
                    dermaService,
                    ContextProviders.getInstance()
                )
                    .also { INSTANCE = it }
            }
    }
}
