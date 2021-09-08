package com.appssquare.androidtask.UI.views.Login

import android.app.Application
import androidx.lifecycle.*
import com.appssquare.androidtask.Utils.Event
import com.appssquare.androidtask.Utils.Resource
import com.appssquare.androidtask.UI.views.Login.data.LoginInput
import com.appssquare.androidtask.UI.views.Login.data.LoginResponse
import com.appssquare.androidtask.network.Repository.MainRepository
import kotlinx.coroutines.launch
import retrofit2.Response


class LoginViewModel(app: Application,
     private val mainRepository: MainRepository
) : AndroidViewModel(app) , ILogin {

private val _loginResponse = MutableLiveData<Event<Resource<LoginResponse>>>()
    val loginResponse: LiveData<Event<Resource<LoginResponse>>> = _loginResponse


    fun loginUser(body: LoginInput) = viewModelScope.launch {
        login(body)
    }
     override suspend fun login(body: LoginInput) {
        _loginResponse.postValue(Event(Resource.Loading()))
        val response = mainRepository.loginUser(body)
        _loginResponse.postValue(handleResponse(response))
//        try {
//            if (Utils.hasInternetConnection(getApplication<MyApplication>())) {
//                val response = mainRepository.loginUser(body)
//                _loginResponse.postValue(handleResponse(response))
//            } else {
//                _loginResponse.postValue(Event(Resource.Error(getApplication<MyApplication>().getString(R.string.no_internet_connection))))
//            }
//        } catch (t: Throwable) {
//            when (t) {
//                is IOException -> {
//                    _loginResponse.postValue(
//                        Event(Resource.Error(
//                            getApplication<Application>().getString(
//                                R.string.network_failure
//                            )
//                        ))
//                    )
//                }
//                else -> {
//                    _loginResponse.postValue(
//                        Event(Resource.Error(
//                            getApplication<Application>().getString(
//                                R.string.conversion_error
//                            )
//                        ))
//                    )
//                }
//            }
//        }
    }
    private fun handleResponse(response: Response<LoginResponse>): Event<Resource<LoginResponse>>? {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Event(Resource.Success(resultResponse))
            }
        }
        return Event(Resource.Error(response.message()))
    }
}