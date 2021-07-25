package com.letscodee.userlogin

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var shp:SharedPreferences
    lateinit var shpEditor: SharedPreferences.Editor

    lateinit var userName:TextInputLayout
    lateinit var passward:TextInputLayout

    lateinit var loginBtn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userName=findViewById(R.id.username)
        passward=findViewById(R.id.passward)
        loginBtn=findViewById(R.id.loginBtn)

        shp=getSharedPreferences("loginPref", MODE_PRIVATE)
        CheckLogin()

        loginBtn.setOnClickListener {
            if(userName.editText?.text.toString() == "" || passward.editText?.text.toString() == ""){
                userName.error="Field Can't be empty"
                passward.error="Field Can't be empty"
            }
            else{
                userName.error=null
                userName.isErrorEnabled=false
                passward.error=null
                passward.isErrorEnabled=false
                DoLogin()
            }
        }
    }

    @SuppressLint("CommitPrefEdits")
    private fun DoLogin() {
        try{
            if(passward.editText?.text.toString().equals("Letscodee")){
                if(shp==null){
                    shp=getSharedPreferences("loginPref", MODE_PRIVATE)
                }

                shpEditor=shp.edit();
                shpEditor.putString("name", userName.editText?.text.toString())
                shpEditor.commit()
                val intent=Intent(this,Page2::class.java)
                startActivity(intent)
            }
        }catch (ex:Exception){
            Log.d("Error",ex.toString())
        }
    }

    private fun CheckLogin() {
        if(shp==null){
            shp=getSharedPreferences("loginPref", MODE_PRIVATE)
        }
        val username=shp.getString("name","");

        if(username!=null && username != ""){
            val intent=Intent(this,Page2::class.java)
            startActivity(intent)
        }

    }
}