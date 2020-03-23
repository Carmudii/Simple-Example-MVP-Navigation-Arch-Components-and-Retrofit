package com.example.mvpproject.views.home

import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.mvpproject.R
import com.example.mvpproject.utils.SharedPreferenceManager
import kotlinx.android.synthetic.main.fragment_home.*

class HomePresenter(private val view: HomeFragment) : HomeView.Presenter {

    lateinit var navController: NavController
    lateinit var sharedPreferenceManager: SharedPreferenceManager

    init {
        view.view?.let {
            navController =  Navigation.findNavController(it)
        }

        sharedPreferenceManager = SharedPreferenceManager(view.context)
    }

    override fun initialize() {
        if(!sharedPreferenceManager.preferenceManager
                .getString(SharedPreferenceManager.TOKEN, "")
                .isNullOrEmpty()) {

            view.welcome?.text = "You has been logged"
            view.buttonAction?.text = "Logout"
            view.buttonAction?.setOnClickListener {
                sharedPreferenceManager.editor
                    .remove(SharedPreferenceManager.TOKEN)
                    .apply()
                sharedPreferenceManager.editor
                    .commit()
                navController.navigate(R.id.action_homeFragment_to_loginFragment)
            }

        } else {

            view.buttonAction?.setOnClickListener {
                navController.navigate(R.id.action_homeFragment_to_loginFragment)
            }

        }
    }
}
