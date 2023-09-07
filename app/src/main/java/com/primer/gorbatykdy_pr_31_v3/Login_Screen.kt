package com.primer.gorbatykdy_pr_31_v3

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.primer.gorbatykdy_pr_31_v3.databinding.ActivityLoginScreenBinding
import com.primer.gorbatykdy_pr_31_v3.FlatPriceCalc as FlatPriceCalc1

class Login_Screen : AppCompatActivity() {
    private val APP_PREFERENCES = "settings"
    private val pref_login = "login"
    private val pref_psswd = "psswd"

    lateinit var bindingClass: ActivityLoginScreenBinding
    lateinit var login: EditText
    lateinit var psswd: EditText
    lateinit var pref: SharedPreferences

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        login = bindingClass.edit1
        psswd = bindingClass.edit2
        pref = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)
    }

    fun Save() {
        val edited = pref.edit()
        val login_text = login.text.toString()
        val psswd_text = psswd.text.toString()
        if (login_text.isNotEmpty() && psswd_text.isNotEmpty()) {
            edited.putString("login", login_text)
            edited.putString("psswd", psswd_text)
            edited.apply()

            Toast.makeText(
                applicationContext,
                "Вы успешно сохранили данные для входа",
                Toast.LENGTH_SHORT
            ).show()
            val intent = Intent(this@Login_Screen, FlatPriceCalc1::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(applicationContext, "Обнаружены пустые поля", Toast.LENGTH_SHORT).show()
        }
    }

    fun Get(v: View) {
        val login_text = login.text.toString()
        val psswd_text = psswd.text.toString()
        if (v.id == R.id.auth) {
            Save()
        } else {
            login.setText(pref.getString("login", "")); psswd.setText(pref.getString("psswd", ""))
        }
    }
}