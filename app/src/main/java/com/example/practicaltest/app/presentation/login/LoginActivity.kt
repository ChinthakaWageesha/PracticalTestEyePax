package com.example.practicaltest.app.presentation.login

import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.practicaltest.app.data.database.UserDatabase
import com.example.practicaltest.core.extenstion.*
import com.example.practicaltest.core.general.GoTo
import com.example.practicaltest.core.presentation.BaseActivity
import com.example.practicaltest.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity() {

    override val toolBarTitle: String
        get() = ""

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLayout()
        validateLogin()
    }

    private fun initLayout() {
        binding.etEmail.addTextChangedListener(GenericTextWatcher())
        binding.etPassword.addTextChangedListener(GenericTextWatcher())

        binding.btnLogin.setOnClickListener { login() }
        binding.txtSignUp.setOnClickListener { GoTo.register(this) }
    }

    private fun validateLogin() {

        binding.etEmail.validateOnTextChange(isCheckValidateIcon = true) { s -> s.isNotEmpty() }
        binding.etPassword.validateOnTextChange(isCheckValidateIcon = true) { s -> s.isNotEmpty() }

        binding.etEmail.clearTextOnRightDrawableClick()
        binding.etPassword.clearTextOnRightDrawableClick()
    }

    private fun login() {
        val userDatabase = UserDatabase.getDatabase(this)
        val userDao = userDatabase.userDao()
        Thread {
            if (userDao.login(
                    binding.etEmail.text.toString(),
                    binding.etPassword.text.toString()
                )
            ) {
                runOnUiThread {
                    "User logged in successfully".showToast(this)
                    GoTo.main(this)
                }
            } else {
                runOnUiThread {
                    "Invalid login credentials".showToast(this)
                }
            }
        }.start()
    }

    inner class GenericTextWatcher() : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(p0: Editable?) {}

        override fun onTextChanged(ch: CharSequence?, p1: Int, p2: Int, p3: Int) {
            binding.btnLogin.isEnabled =
                binding.etEmail.text!!.toString()
                    .isValidEmail() && binding.etPassword.text!!.toString().isValidPassword()
        }
    }
}