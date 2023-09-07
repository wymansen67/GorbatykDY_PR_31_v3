package com.primer.gorbatykdy_pr_31_v3

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.primer.gorbatykdy_pr_31_v3.databinding.ActivityCalculationBinding
import com.primer.gorbatykdy_pr_31_v3.databinding.ActivityFlatPriceCalcBinding
import java.text.DecimalFormat

class Calculation : AppCompatActivity() {

    lateinit var bindingClass : ActivityCalculationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityCalculationBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        bindingClass.apply {
            val measurements = intent.getIntExtra("measurement",0).toString()
            val result = intent.getDoubleExtra("result", 0.0).toString()
            bindingClass.measurements.setText("Кол-во метров\n"+measurements)
            bindingClass.result.setText("Результат\n"+result)
        }

        bindingClass.reg.setOnClickListener(){
            val intent = Intent(this@Calculation, Login_Screen::class.java)
            startActivity(intent)
            finish()
        }
    }
}