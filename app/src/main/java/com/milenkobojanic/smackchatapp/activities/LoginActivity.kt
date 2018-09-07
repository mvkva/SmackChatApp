package com.milenkobojanic.smackchatapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.milenkobojanic.smackchatapp.R
import com.milenkobojanic.smackchatapp.services.AuthService
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginProgressBar.visibility = View.INVISIBLE
    }

    fun loginLoginButtonClicked(view: View) {

        enableProgressBar(true)
        val email = loginEmailText.text.toString()
        val password = loginPasswordText.text.toString()
        hideKeyboard()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            AuthService.loginUser(email, password) { loginSuccesss ->
                if (loginSuccesss) {
                    AuthService.findUserByEmail(this) { findSuccess ->
                        if (findSuccess) {
                            enableProgressBar(false)
                            finish()
                        } else {
                            errorToast()
                        }
                    }
                } else {
                    errorToast()
                }
            }
        } else {
            Toast.makeText(this, getString(R.string.login_empty_string_message), Toast.LENGTH_LONG).show()
        }
    }

    fun loginCreateUserButtonClicked(view: View) {
        val createUserIntent = Intent(this, CreateUserActivity::class.java)
        startActivity(createUserIntent)
        finish()
    }

    fun errorToast() {
        Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_LONG).show()
        enableProgressBar(false)
    }

    fun enableProgressBar(enable: Boolean) {
        if (enable) {
            loginProgressBar.visibility = View.VISIBLE
        } else {
            loginProgressBar.visibility = View.INVISIBLE
        }

        loginLoginButton.isEnabled = !enable
        loginCreateUserButton.isEnabled = !enable
    }

    fun hideKeyboard() {
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        if (inputManager.isAcceptingText) {
            inputManager.hideSoftInputFromWindow(currentFocus.windowToken, 0)
        }
    }
}
