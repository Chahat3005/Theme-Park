package com.example.themepark

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.themepark.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        clicks()
        setToolbar()
    }

    private fun clicks() {
        binding.toolbar.ivBack.setOnClickListener(this)
        binding.toolbar.ivCross.setOnClickListener(this)
    }

    private fun setToolbar() {
        binding.toolbar.tvTitle.visibility = View.GONE
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ivBack -> {
                onBackPressedDispatcher.onBackPressed()
            }

            R.id.ivCross -> {
                onBackPressedDispatcher.onBackPressed()
            }
        }
    }

}