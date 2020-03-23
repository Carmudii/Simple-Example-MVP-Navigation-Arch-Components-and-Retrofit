package com.example.mvpproject.views.login

interface LoginView {

    interface Presenter {
        fun onStartLogin()
    }

    fun onSuccess(token: String)

    fun onFailed(message: String)

    fun onProcessing()

    fun onDoneProcessing()

}