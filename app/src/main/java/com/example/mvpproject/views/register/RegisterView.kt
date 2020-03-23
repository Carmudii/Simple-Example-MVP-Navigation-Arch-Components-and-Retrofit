package com.example.mvpproject.views.login

interface RegisterView {

    interface Presenter {
        fun onStartRegister()
    }

    fun onSuccess(message: String)

    fun onFailed(message: String)

    fun onProcessing()

    fun onDoneProcessing()

}