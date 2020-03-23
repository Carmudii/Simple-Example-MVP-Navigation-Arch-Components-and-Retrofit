package com.example.mvpproject.views.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.example.mvpproject.R
import com.example.mvpproject.utils.SharedPreferenceManager
import com.example.mvpproject.views.MainActivity
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment(), LoginView, View.OnClickListener, View.OnFocusChangeListener {

    lateinit var navController: NavController
    lateinit var loginPresenter: LoginPresenter
    lateinit var sharedPreferenceManager: SharedPreferenceManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        loginPresenter = LoginPresenter(this)


        edUsername.setOnFocusChangeListener(this)
        edPassword.setOnFocusChangeListener(this)

        login.setOnClickListener(this)
        register.setOnClickListener(this)
        back.setOnClickListener(this)
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if(hasFocus) {
            when(v!!.id) {
                R.id.edUsername -> username.error = null
                R.id.edPassword -> password.error = null
            }
        }
    }

    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.login -> loginPresenter.onStartLogin()
            R.id.register -> navController.navigate(R.id.action_loginFragment_to_registerFragment)
            R.id.back -> activity?.onBackPressed()
        }
    }

    override fun onSuccess(token: String) {
        activity?.onBackPressed()

        Toast.makeText(context, "Succesfully Login", Toast.LENGTH_LONG)
            .show()
    }

    override fun onFailed(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG)
            .show()
    }

    override fun onProcessing() {
        progressBar?.visibility = View.VISIBLE
        login?.visibility = View.INVISIBLE
        register?.isEnabled = false
    }

    override fun onDoneProcessing() {
        progressBar?.visibility = View.GONE
        login?.visibility = View.VISIBLE
        register?.isEnabled = true
    }

}
