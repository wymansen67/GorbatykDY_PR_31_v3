package com.primer.gorbatykdy_pr_31_v3

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.primer.gorbatykdy_pr_31_v3.databinding.ActivityFlatPriceCalcBinding
import com.primer.gorbatykdy_pr_31_v3.databinding.ActivityLoginScreenBinding

class FlatPriceCalc : AppCompatActivity() {
    val flats = arrayOf(
        "1. 1-о комнатная квартира",
        "2. 2-х комнатная квартира",
        "3. 3-х комнатная квартира",
        "4. Студия"
    )

    var a_glob : Int = 0
    var b_glob : Double = 0.0

    lateinit var bindingClass: ActivityFlatPriceCalcBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityFlatPriceCalcBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val arrayAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, flats)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Toast.makeText(
                    applicationContext,
                    "Вы выбрали: " + flats[spinner.selectedItemId.toInt()],
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        bindingClass.calculate.setOnClickListener {
            Calc()
            val intent = Intent(this@FlatPriceCalc, Calculation::class.java)
            intent.putExtra("measurement", a_glob)
            intent.putExtra("result", b_glob)
            startActivity(intent)
            startActivity(intent)
            finish()
        }
        bindingClass.back.setOnClickListener {
            val intent = Intent(this@FlatPriceCalc, Login_Screen::class.java)
            startActivity(intent)
            finish()
        }
    }
    fun Calc(){
        var measurements = bindingClass.measurements
        if (measurements.text.toString().isNotEmpty()) {
            var a : Int? = try { measurements.text.toString().toInt() } catch (e: NumberFormatException) { null }
            if (a != null) {
                if (a > 0) {
                    a_glob = a
                    when (bindingClass.spinner.selectedItemId.toInt()){
                        0 -> { var b = 50000*a*1.4; bindingClass.result.setText("Результат\n"+b.toInt()+"₽"); b_glob = b }
                        1 -> { var b = (50000*a).toDouble(); bindingClass.result.setText("Результат\n"+b.toInt()+"₽"); b_glob = b }
                        2 -> { var b = 50000*a*0.8; bindingClass.result.setText("Результат\n"+b.toInt()+"₽"); b_glob = b }
                        3 -> { var b = 50000*a*1.1; bindingClass.result.setText("Результат\n"+b.toInt()+"₽"); b_glob = b }
                    }
                } else {
                    Toast.makeText(applicationContext,"Недопустимое значение",Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(applicationContext,"Недопустимое значение",Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(applicationContext, "Введите метраж помещения", Toast.LENGTH_SHORT).show()
        }
    }
}