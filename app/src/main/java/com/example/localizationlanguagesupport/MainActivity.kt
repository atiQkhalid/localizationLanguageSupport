package com.example.localizationlanguagesupport

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.localizationlanguagesupport.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadLocate()

        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        binding.tvEn.setOnClickListener {
            setLocate("en")
            recreate()
        }
        binding.tvTr.setOnClickListener {
            setLocate("tr")
            recreate()
        }
    }

    private fun setLocate(Lang: String){
        val locale = Locale(Lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale

        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang", Lang)
        editor.apply()
    }

    fun loadLocate(){
        var preferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        var localizedLanguage = preferences.getString("My_Lang", "")
        setLocate(localizedLanguage!!)
    }
}