package com.appssquare.androidtask.UI.views.Login

import com.appssquare.androidtask.UI.views.Login.data.LoginInput

interface ILogin {
    suspend fun login(body: LoginInput)
}