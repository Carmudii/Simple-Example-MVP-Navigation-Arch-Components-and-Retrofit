package com.example.mvpproject.views.login

import android.util.Log
import com.example.mvpproject.models.AuthResponse
import com.example.mvpproject.services.ApiBuilder
import com.example.mvpproject.services.ApiService
import com.example.mvpproject.utils.SharedPreferenceManager
import kotlinx.android.synthetic.main.fragment_login.*
import retrofit2.Callback
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

class LoginPresenter(private val view: LoginFragment) : LoginView.Presenter {

    lateinit var sharedPreferenceManager: SharedPreferenceManager

    init {
        sharedPreferenceManager = SharedPreferenceManager(view.context)
    }

    override fun onStartLogin() {

        view.onProcessing()

        if(view.edUsername?.text.isNullOrEmpty()) {

            view.username.error = "username cannot be empty"
            view.onDoneProcessing()

        } else if(view.edPassword?.text.isNullOrEmpty()) {

            view.password.error = "password cannot be empty"
            view.onDoneProcessing()

        } else {

            val jsonData = JSONObject()
            jsonData.put("username", view.edUsername?.text)
                .put("password", view.edPassword?.text)

            val requestBody = RequestBody
                .create(
                    MediaType
                        .parse("application/json"), jsonData.toString())

            val retrofit = ApiBuilder()
                .build()
                .create(ApiService::class.java)

            retrofit.login(requestBody).enqueue(object : Callback<AuthResponse> {

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    view.onFailed(t.message.toString())
                    Log.d("DEBUG", t.message.toString())
                    view.onDoneProcessing()
                }

                override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                    Log.d("DEBUG", response.code().toString())
                    if(response.isSuccessful) {
                        if(response.body()!!.status) {
                            sharedPreferenceManager.editor
                                .putString(SharedPreferenceManager.TOKEN, response.body()!!.token)
                                .apply()
                            sharedPreferenceManager.editor
                                .commit()

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