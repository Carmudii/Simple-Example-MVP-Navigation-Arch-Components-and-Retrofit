package com.example.mvpproject.views.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.mvpproject.R
import com.example.mvpproject.views.login.RegisterView
import kotlinx.android.synthetic.main.fragment_register.*

/**
 * A simple [Fragment] subclass.
 */
class RegisterFragment : Fragment(), RegisterView, View.OnFocusChangeListener, View.OnClickListener {

    lateinit var registerPresenter: RegisterPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerPresenter = RegisterPresenter(this)

        edFullname.setOnFocusChangeListener(this)
        edHHobby.setOnFocusChangeListener(this)
        edUsername.setOnFocusChangeListener(this)
        edPassword.setOnFocusChangeListener(this)

        register.setOnClickListener(this)
        back.setOnClickListener(this)
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if(hasFocus) {
            when(v!!.id) {
                R.id.edFullname -> fullname.error = null
                R.id.edHHobby -> hobby.error = null
                R.id.edUsername -> username.error = null
                R.id.edPassword -> password.error = null
            }
        }
    }

    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.register -> registerPresenter.onStartRegister()
            R.id.back -> activity?.onBackPressed()
        }
    }

    override fun onSuccess(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG)
            .show()
        activity?.onBackPressed()
    }

    override fun onFailed(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG)
            .show()
    }

    override fun onProcessing() {
        progressBar?.visibility = View.VISIBLE
        register?.visibility = View.INVISIBLE
    }

    override fun onDoneProcessing() {
        progressBar?.visibility = View.GONE
        register?.visibility = View.VISIBLE
    }
}
