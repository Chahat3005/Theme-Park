package com.example.themepark

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.themepark.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
        loadSaveData()
        observerViewModel()
    }

    private fun setupClickListeners() {
        binding.tvForgotPassword.setOnClickListener(this)
        binding.btLogin.setOnClickListener {
            if (loginValidation()) {
                val username = binding.etUsername.text.toString().trim()
                val password = binding.etPassword.text.toString().trim()

                loginViewModel.login(username, password)
                saveData(username, password)
            }
        }
        binding.btnSignup.setOnClickListener(this)
        binding.btnLogin.setOnClickListener(this)
        binding.btSignUp.setOnClickListener(this)
    }

    private fun observerViewModel() {
        loginViewModel.loginResponse.observe(this) { response ->
            if (response != null) {
                Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MessageActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Enter valid credentials", Toast.LENGTH_SHORT).show()
            }
        }

        loginViewModel.error.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }
    }

    private fun loginValidation(): Boolean {
        return when {
            binding.etUsername.text.toString().isEmpty() -> {
                Toast.makeText(this, "Please enter your username", Toast.LENGTH_SHORT).show()
                false
            }
            binding.etPassword.text.toString().isEmpty() -> {
                Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show()
                false
            }
            else -> true
        }
    }

    private fun signUpValidation(): Boolean {
        return when {
            binding.etSignUpUsername.text.toString().isEmpty() -> {
                Toast.makeText(this, "Please enter your username", Toast.LENGTH_SHORT).show()
                false
            }
            binding.etSignUpEmail.text.toString().isEmpty() -> {
                Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show()
                false
            }
            binding.etSignUpPassword.text.toString().isEmpty() -> {
                Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show()
                false
            }
            binding.etSignUpConfirmPassword.text.toString().isEmpty() -> {
                Toast.makeText(this, "Please enter your confirm password", Toast.LENGTH_SHORT).show()
                false
            }
            binding.etSignUpPassword.text.toString() != binding.etSignUpConfirmPassword.text.toString() -> {
                Toast.makeText(this, "Confirm password is not equal to the above password", Toast.LENGTH_SHORT).show()
                false
            }
            else -> true
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvForgotPassword -> {
                startActivity(Intent(this, ForgotPasswordActivity::class.java))
            }

            R.id.btnSignup -> {
                binding.llSignUp.visibility = View.VISIBLE
                binding.llLogin.visibility = View.GONE
            }

            R.id.btnLogin -> {
                binding.llLogin.visibility = View.VISIBLE
                binding.llSignUp.visibility = View.GONE
            }

            R.id.btSignUp -> {
                if (signUpValidation()) {
                    Toast.makeText(this, "Signup Successful", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun saveData(username: String, password: String) {
        val sharedPreferences = getSharedPreferences("LoginSaved", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("USERNAME", username)
        editor.putString("PASSWORD", password)
        editor.apply()
    }

    private fun loadSaveData() {
        val sharedPreferences = getSharedPreferences("LoginSaved", Context.MODE_PRIVATE)
        val savedUsername = sharedPreferences.getString("USERNAME", "")
        val savedPassword = sharedPreferences.getString("PASSWORD", "")

        if (!savedUsername.isNullOrEmpty()) {
            binding.etUsername.setText(savedUsername)
            binding.etPassword.setText(savedPassword)
        }
    }
}
