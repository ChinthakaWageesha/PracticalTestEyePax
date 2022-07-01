package com.example.practicaltest.app.presentation.register

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.practicaltest.R
import com.example.practicaltest.app.data.database.UserDatabase
import com.example.practicaltest.app.domain.model.DUser
import com.example.practicaltest.core.extenstion.*
import com.example.practicaltest.core.general.GoTo
import com.example.practicaltest.core.presentation.BaseActivity
import com.example.practicaltest.databinding.ActivityRegisterBinding

class RegisterActivity : BaseActivity() {

    override val toolBarTitle: String
        get() = getString(R.string.label_sign_up)

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLayout()
        validateLogin()
        binding.btnSignUp.setOnClickListener { registerUser() }
    }

    private fun initLayout() {
        binding.etFirstName.addTextChangedListener(GenericTextWatcher())
        binding.etLastName.addTextChangedListener(GenericTextWatcher())
        binding.etEmail.addTextChangedListener(GenericTextWatcher())
        binding.etPassword.addTextChangedListener(GenericTextWatcher())
        binding.etConfirmPassword.addTextChangedListener(GenericTextWatcher())

        binding.btnSignUp.setOnClickListener { GoTo.main(this) }
        binding.txtLogin.setOnClickListener { onBackPressed() }
    }

    private fun validateLogin() {

        binding.etFirstName.validateOnTextChange(isCheckValidateIcon = true) { s -> s.isNotEmpty() }
        binding.etLastName.validateOnTextChange(isCheckValidateIcon = true) { s -> s.isNotEmpty() }
        binding.etEmail.validateOnTextChange(isCheckValidateIcon = true) { s -> s.isNotEmpty() }
        binding.etPassword.validateOnTextChange(isCheckValidateIcon = true) { s -> s.isNotEmpty() }
        binding.etConfirmPassword.validateOnTextChange(isCheckValidateIcon = true) { s -> s.isNotEmpty() }

        binding.etFirstName.clearTextOnRightDrawableClick()
        binding.etLastName.clearTextOnRightDrawableClick()
        binding.etEmail.clearTextOnRightDrawableClick()
        binding.etPassword.clearTextOnRightDrawableClick()
        binding.etConfirmPassword.clearTextOnRightDrawableClick()
    }

    private fun registerUser() {
        val user = DUser()
        user.firstName = binding.etFirstName.text.toString()
        user.lastName = binding.etLastName.text.toString()
        user.email = binding.etEmail.text.toString()
        user.password = binding.etPassword.text.toString()

        val userDatabase = UserDatabase.getDatabase(this)
        val userDao = userDatabase.userDao()
        Thread {
            userDao.registerUser(user)
            runOnUiThread {
                "User registered successfully".showToast(this)
                GoTo.main(this)
            }
        }.start()


    }

    inner class GenericTextWatcher() : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(p0: Editable?) {}

        override fun onTextChanged(ch: CharSequence?, p1: Int, p2: Int, p3: Int) {
            binding.btnSignUp.isEnabled =
                binding.etFirstName.text!!.isNotEmpty() && binding.etLastName.text!!.isNotEmpty() &&
                        binding.etEmail.text!!.toString()
                            .isValidEmail() && binding.etPassword.text!!.toString()
                    .isValidPassword() &&
                        binding.etConfirmPassword.text.toString() == binding.etPassword.text.toString()
        }
    }
}