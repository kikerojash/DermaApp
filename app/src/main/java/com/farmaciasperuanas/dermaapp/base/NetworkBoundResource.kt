package com.farmaciasperuanas.dermaapp.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

abstract class NetworkBoundResource<ResultType, RequestType>
constructor(private val contextProviders: ContextProviders) {

    val TAG = NetworkBoundResource::class.java.name

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)
        val dbSource = loadFromDb()
        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    setValue(Resource.success(newData))
                }
            }
        }
    }

    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()
        Log.d(TAG,"apiResponse " +apiResponse.toString())
        result.addSource(dbSource) { newData ->
            Log.d(TAG,"newData " +newData.toString())
            setValue(Resource.loading(newData))
        }

        result.addSource(apiResponse) { response ->
            Log.d(TAG,"response " +response.toString())
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when (response) {
                is ApiSuccessResponse -> {
                    Log.d(TAG,"ApiSuccessResponse " +ApiSuccessResponse.toString())
                    GlobalScope.launch(contextProviders.IO) {
                        Log.d(TAG,"saveCallResult " +processResponse(response).toString())
                        saveCallResult(processResponse(response))
                        GlobalScope.launch(contextProviders.Main) {
                            result.addSource(loadFromDb()) { newData ->
                                Log.d(TAG,"addSource " +newData.toString())
                                setValue(Resource.success(newData))
                            }
                        }
                    }
                }
                is ApiEmptyResponse -> {
                    Log.d(TAG,"ApiEmptyResponse " )
                    GlobalScope.launch(contextProviders.Main) {
                        result.addSource(loadFromDb()) { newData ->
                            setValue(Resource.success(newData))
                        }
                    }
                }
                is ApiErrorResponse -> {
                    onFetchFailed()
                    Log.d(TAG,"ApiErrorResponse " )
                    result.addSource(dbSource) { newData ->
                        setValue(Resource.error(response.errorMessage, newData))
                    }
                }
            }
        }
    }

    protected open fun onFetchFailed() {}

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    abstract fun saveCallResult(item: RequestType)

    protected fun processResponse(response: ApiSuccessResponse<RequestType>) = response.body

    abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun loadFromDb(): LiveData<ResultType>
}
/*
abstract class NetworkBoundResource<ResultType, RequestType>
constructor(private val contextProviders: ContextProviders) {

    val TAG = NetworkBoundResource::class.java.name

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        Log.d(TAG,"NetworkBoundResource init")
        result.value = Resource.loading(null)
        Log.d(TAG,"result.value : "+result.value)
        val dbSource = loadFromDb()
        Log.d(TAG,"result.value : "+dbSource.value)
        result.addSource(dbSource) { data ->
            Log.d(TAG,"data : "+data.toString())
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                Log.d(TAG,"shouldFetch(data) : "+shouldFetch(data).toString())
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    Log.d(TAG,"elseshouldFetch(data) : "+newData.toString())
                    setValue(Resource.success(newData))
                }
            }
        }
    }

    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()
        Log.d(TAG,"apiResponse")
        result.addSource(dbSource) { newData ->
            Log.d(TAG,"apiResponse::newData "+newData.toString())
            setValue(Resource.loading(newData))
        }

        result.addSource(apiResponse) { response ->
            Log.d(TAG,"apiResponse::apiResponse "+response.toString())
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when (response) {
                is ApiSuccessResponse -> {
                    Log.d(TAG,"apiResponse::ApiSuccessResponse : "+ApiSuccessResponse.toString())
                    GlobalScope.launch(contextProviders.IO) {
                        Log.d(TAG,"apiResponse::contextProviders.IO")
                        saveCallResult(processResponse(response))
                        GlobalScope.launch(contextProviders.Main) {
                            result.addSource(loadFromDb()) { newData ->
                                Log.d(TAG,"apiResponse::newData "+newData.toString())
                                setValue(Resource.success(newData))
                            }
                        }
                    }
                }
                is ApiEmptyResponse -> {
                    Log.d(TAG,"ApiEmptyResponse")
                    GlobalScope.launch(contextProviders.Main) {
                        result.addSource(loadFromDb()) { newData ->
                            setValue(Resource.success(newData))
                        }
                    }
                }
                is ApiErrorResponse -> {
                    Log.d(TAG,"onFetchFailed")
                    onFetchFailed()
                    result.addSource(dbSource) { newData ->
                        setValue(
                            Resource.error(
                                response.errorMessage,
                                newData
                            )
                        )
                    }
                }
            }
        }
    }

    protected open fun onFetchFailed() {}

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    abstract fun saveCallResult(item: RequestType)

    protected fun processResponse(response: ApiSuccessResponse<RequestType>) = response.body

    abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun loadFromDb(): LiveData<ResultType>
}*/