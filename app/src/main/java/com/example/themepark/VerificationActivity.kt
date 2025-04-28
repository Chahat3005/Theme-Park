package com.example.themepark

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.themepark.databinding.ActivityVerificationBinding

class VerificationActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var binding: ActivityVerificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        allClicks()
        setupOtpInputs()
    }

    private fun allClicks() {
        binding.ivVerifyBack.setOnClickListener(this)
        binding.btnVerify.setOnClickListener(this)
    }

    private fun setupOtpInputs() {
        val editText = listOf(
            binding.etCode1,
            binding.etCode2,
            binding.etCode3,
            binding.etCode4,
            binding.etCode5,
            binding.etCode6,
        )

        for (i in editText.indices) {
            editText[i].addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    if (s?.length == 1 && i < editText.size - 1) {
                        editText[i + 1].requestFocus()

                    }
                }

                override fun afterTextChanged(s: Editable?) {}
            })
        }
    }

    private fun checkValidation(): Boolean {
        val otp = listOf(
            binding.etCode1.text.toString(),
            binding.etCode2.text.toString(),
            binding.etCode3.text.toString(),
            binding.etCode4.text.toString(),
            binding.etCode5.text.toString(),
            binding.etCode6.text.toString(),
        )
        if (otp.any { it.isEmpty() }) {
            Toast.makeText(this, "Please fill all the otp fields", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    override fun onClick(v: View) {
        when (v.id) {

            R.id.btnVerify -> {

                if (checkValidation()) {
                    val otp = binding.etCode1.text.toString() +
                            binding.etCode2.text.toString() +
                            binding.etCode3.text.toString() +
                            binding.etCode4.text.toString() +
                            binding.etCode5.text.toString() +
                            binding.etCode6.text.toString()

                    Toast.makeText(this, "OTP Verified: $otp", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,ResetActivity::class.java))

                }
            }

            R.id.ivVerifyBack -> {
                onBackPressedDispatcher.onBackPressed()

            }
        }
    }
}