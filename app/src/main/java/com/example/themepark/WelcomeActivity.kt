package com.example.themepark

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.themepark.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var binding:ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        clicks()
    }
    private fun clicks(){
        binding.btSignup.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btSignup-> {
                startActivity(Intent(this,LoginActivity::class.java))
            }
        }
    }
}