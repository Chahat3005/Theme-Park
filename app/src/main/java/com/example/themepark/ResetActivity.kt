package com.example.themepark

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.themepark.databinding.ActivityResetBinding

class ResetActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityResetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        clicks()
    }

    private fun clicks() {
        binding.btnSave.setOnClickListener(this)
    }

    private fun checkValidation(): Boolean {
        if (binding.etNewPassword.text.toString().isEmpty()) {
            Toast.makeText(this, "Please enter your new password", Toast.LENGTH_SHORT).show()
            return false
        } else if (binding.etConfirmPassword.text.toString().isEmpty()) {
            Toast.makeText(this, "Please enter your confirm password", Toast.LENGTH_SHORT).show()
            return false
        } else if (binding.etNewPassword.text.toString()
                .isEmpty() != binding.etConfirmPassword.text.toString().isEmpty()
        ) {
            Toast.makeText(
                this,
                "New password is not equal to the confirm password",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return true
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnSave -> {
                if (checkValidation()) {
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            }
        }
    }
}