package com.example.calculadoraimc

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.util.Locale


class ResultIMCActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private lateinit var tvIMC: TextView
    private lateinit var tvResult2: TextView

    private lateinit var btnRecalculate: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result_imcactivity)
        val result = intent.extras?.getDouble("IMC_RESULT") ?: -1.0
        initComponents()
        initUI(result)
        initListeners()

    }

    private fun initListeners(){
        btnRecalculate.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
    }

    private fun initComponents(){
        tvResult = findViewById(R.id.Result)
        tvResult2 = findViewById(R.id.Result2)
        tvIMC = findViewById(R.id.IMC)
        btnRecalculate = findViewById(R.id.btnRecalculate)
    }

    private fun initUI(result: Double){
        tvIMC.text = String.format(Locale.getDefault(),"%.2f", result)

        when (result){
            in 0.00..18.50 ->{
                tvResult.text = getString(R.string.result_BajoPeso)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.yellow))
                tvResult2.text = getString(R.string.result_BajoPeso)
            } in 18.51..24.99 ->{
                tvResult.text = getString(R.string.result_Normal)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.green))
                tvResult2.text = getString(R.string.result_Normal)
            } in 25.00..29.99 ->{
                tvResult.text = getString(R.string.result_SobrePeso)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.orange))
                tvResult2.text = getString(R.string.result_SobrePeso)
            } in 30.00..99.99 ->{
                tvResult.text = getString(R.string.result_Obesidad)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.red))
                tvResult2.text = getString(R.string.result_Obesidad)
            } else -> {
                tvResult.text = getString(R.string.error)
                tvIMC.text = getString(R.string.error)
                tvResult2.text = getString(R.string.error)
            }
        }
    }
}