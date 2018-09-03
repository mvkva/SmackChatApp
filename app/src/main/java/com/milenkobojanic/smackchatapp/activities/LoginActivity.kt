package com.milenkobojanic.smackchatapp.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.milenkobojanic.smackchatapp.R
import com.milenkobojanic.smackchatapp.services.AuthService
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun loginLoginButtonClicked(view: View) {

        val email = loginEmailText.text.toString()
        val password = loginPasswordText.text.toString()

        AuthService.loginUser(this, email, password) { loginSuccesss ->
            if (loginSuccesss) {
                AuthService.findUserByEmail(this) { findSuccess ->
                    if (findSuccess) {
                        finish()
                    }
                }
            }
        }

    }

    fun loginCreateUserButtonClicked(view: View) {
        val createUserIntent = Intent(this, CreateUserActivity::class.java)
        startActivity(createUserIntent)
        finish()
    }
}
