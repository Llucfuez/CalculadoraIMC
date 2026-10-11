package com.example.calculadoraimc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.Slider
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private var estaSeleccionatHome = true
    private var estaSeleccionatDona = false
    private var pes = 60
    private var edat = 20
    private var altura = 160

    private lateinit var viewMale:CardView
    private lateinit var viewFemale:CardView

    private lateinit var tvHeight:TextView
    private lateinit var rsHeight:Slider

    private lateinit var btnWheightSubstract:FloatingActionButton
    private lateinit var btnWheightPlus:FloatingActionButton
    private lateinit var tvWheight:TextView

    private lateinit var btnAgeSubstract: FloatingActionButton
    private lateinit var btnAgePlus: FloatingActionButton
    private lateinit var tvAge:TextView
    private lateinit var btnCalculate:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        iniciaComponents()
        initListeners()
        initUI()
    }

    private fun iniciaComponents(){
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        btnWheightSubstract = findViewById(R.id.btnWheightSubstract)
        btnWheightPlus = findViewById(R.id.btnWheightPlus)
        tvWheight = findViewById(R.id.tvWeight)
        btnAgeSubstract = findViewById(R.id.btnAgeSubstract)
        btnAgePlus = findViewById(R.id.btnAgePlus)
        tvAge = findViewById(R.id.tvAge)
        btnCalculate = findViewById(R.id.btnCalculate)

    }

    private fun initListeners(){
        viewMale.setOnClickListener {
            canviaGenere(true)
            asignaColorGenere()
        }
        viewFemale.setOnClickListener {
            canviaGenere(false)
            asignaColorGenere()
        }

        rsHeight.addOnChangeListener { _, f, _ ->
            val nombreArrodonit = f.roundToInt()
            tvHeight.text = getString(R.string.height_cm, nombreArrodonit)
            altura = nombreArrodonit
        }

        btnWheightSubstract.setOnClickListener {
            pes -= 1
            canviaPes()
        }
        btnWheightPlus.setOnClickListener {
            pes +=  1
            canviaPes()
        }

        btnAgeSubstract.setOnClickListener {
            edat -=  1
            canviaEdat()
        }
        btnAgePlus.setOnClickListener {
            edat += 1
            canviaEdat()
        }

        btnCalculate.setOnClickListener {
            val resultat = calculaIMC()
            val intent = Intent(this, ResultIMCActivity::class.java)
            intent.putExtra("IMC_RESULT", resultat)
            startActivity(intent)
        }
    }

    private fun canviaGenere(haClicatHome:Boolean){
        if(haClicatHome and !estaSeleccionatHome){
            estaSeleccionatHome = true
            estaSeleccionatDona = false
        } else if(!haClicatHome and !estaSeleccionatDona){
            estaSeleccionatHome = false
            estaSeleccionatDona = true
        }
    }

    private fun asignaColorGenere(){
        viewMale.setCardBackgroundColor(agafaColorFons(estaSeleccionatHome))
        viewFemale.setCardBackgroundColor(agafaColorFons(estaSeleccionatDona))
    }

    private fun agafaColorFons(estaSeleccionat:Boolean):Int {

        val referenciaColor = if(estaSeleccionat){
            R.color.background_component_selected
        } else {
            R.color.background_component
        }

        return ContextCompat.getColor(this, referenciaColor)

    }

    private fun canviaPes(){
        if (pes < 0){
            pes = 0
        }
        tvWheight.text = pes.toString()

    }

    private fun canviaEdat(){
        if (edat < 0){
            edat = 0
        }
        tvAge.text = edat.toString()
    }

    private fun calculaIMC(): Double{
        val alturaEnMetres = altura/100.0
        val imc = pes/(alturaEnMetres * alturaEnMetres)
        return imc
    }
    private fun initUI(){
        asignaColorGenere()
        canviaPes()
        canviaEdat()
    }
}
