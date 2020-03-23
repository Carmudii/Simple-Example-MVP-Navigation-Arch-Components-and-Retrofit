package com.example.mvpproject.views.register

import android.util.Log
import com.example.mvpproject.models.AuthResponse
import com.example.mvpproject.services.ApiBuilder
import com.example.mvpproject.services.ApiService
import com.example.mvpproject.views.login.RegisterView
import kotlinx.android.synthetic.main.fragment_register.*
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterPresenter(private val view: RegisterFragment): RegisterView.Presenter {

    override fun onStartRegister() {

        if(view.edFullname?.text.isNullOrEmpty()) {

            view.fullname.error = "fullname cannot be empty"

        } else if(view.edHHobby?.text.isNullOrEmpty()) {

            view.hobby.error = "hobby cannot be empty"

        } else if(view.edUsername?.text.isNullOrEmpty()) {

            view.username.error = "username cannot be empty"

        } else if(view.edPassword?.text.isNullOrEmpty()) {

            view.password.error = "password cannot be empty"

        } else {

            view.onProcessing()

            val jsonData = JSONObject()
            jsonData.put("fullname", view.edFullname?.text)
                .put("hobby", view.edHHobby?.text)
                .put("username", view.edUsername?.text)
                .put("password", view.edPassword?.text)

            val requestBody = RequestBody
                .create(
                    MediaType
                        .parse("application/json"), jsonData.toString())

            val retrofit = ApiBuilder()
                .build()
                .create(ApiService::class.java)

            retrofit.register(requestBody).enqueue(object : Callback<AuthResponse> {

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    view.onFailed(t.message.toString())
                    Log.d("DEBUG", t.message.toString())
                    view.onDoneProcessing()
                }

                override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                    Log.d("DEBUG", response.code().toString())
                    if(response.isSuccessful) {
                        if(response.body()!!.status) {
                            view.onSuccess(response.body()!!.message)
                            view.onDoneProcessing()
                        } else {
                            view.onFailed(response.body()!!.message)
                            view.onDoneProcessing()
                        }
                    }
                }

            })
        }
    }
}