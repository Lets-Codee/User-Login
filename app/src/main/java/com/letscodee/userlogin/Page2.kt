package com.letscodee.userlogin

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Page2 : AppCompatActivity() {

    lateinit var shp: SharedPreferences
    lateinit var shpEditor: SharedPreferences.Editor

    lateinit var wel_text: TextView
    lateinit var logoutBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page2)

        wel_text = findViewById(R.id.welcome_text)
        logoutBtn = findViewById(R.id.logoutBtn)

        shp = getSharedPreferences("loginPref", MODE_PRIVATE)
        CheckLogin()

        logoutBtn.setOnClickListener { Logout() }
    }

    @SuppressLint("CommitPrefEdits")
    private fun Logout() {
        try {
            if (shp == null) {
                shp = getSharedPreferences("loginPref", MODE_PRIVATE)
            }

            shpEditor = shp.edit();
            shpEditor.putString("name", "")
            shpEditor.commit()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } catch (ex: Exception) {
            Log.d("Error", ex.toString())
        }
    }


    private fun CheckLogin() {
        if (shp == null) {
            shp = getSharedPreferences("loginPref", MODE_PRIVATE)
        }
        val username = shp.getString("name", "");
        wel_text.text = username
        if (username != null && username != "") {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}