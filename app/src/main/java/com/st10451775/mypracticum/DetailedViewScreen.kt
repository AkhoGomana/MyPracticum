package com.st10451775.mypracticum

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailedViewScreen : AppCompatActivity() {
    //initialising widgets
    private lateinit var btnBackToMainScreen: Button

    private lateinit var txtDays : TextView
    private lateinit var txtMinTemperatures: TextView
    private lateinit var txtMaxTemperatures : TextView
    private lateinit var txtAverage : TextView
    private lateinit var txtWeatherConditions : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detailed_view_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //using findViewById on widgets
        btnBackToMainScreen.findViewById<Button>(R.id.btnBackToMainScreen)

        txtDays.findViewById<TextView>(R.id.txtDays)
        txtMinTemperatures.findViewById<TextView>(R.id.txtMinTemperatures)
        txtMaxTemperatures.findViewById<TextView>(R.id.txtMaxTemps)
        txtWeatherConditions.findViewById<TextView>(R.id.txtWeatherConditions)
        txtAverage.findViewById<TextView>(R.id.txtAverage)

        //retrieving and displaying data
        val bundle2 = intent.extras

        val daysArr = bundle2?.getStringArray("daysArray")
        val minTempsArr = bundle2?.getStringArray("minTempsArray")
        val maxTempsArr = bundle2?.getStringArray("maxTempsArray")
        val weatherConditionsArr = bundle2?.getStringArray("weatherConditionsArray")
        val tempsAverage = bundle2?.getFloat("averageTemps")
        //null check
        if(daysArr != null && minTempsArr != null && maxTempsArr != null && weatherConditionsArr != null){
            txtAverage.append(tempsAverage.toString())

            for (y in daysArr.indices){
                txtDays.append(daysArr[y])
                txtMinTemperatures.append(minTempsArr[y])
                txtMaxTemperatures.append(maxTempsArr[y])
                txtWeatherConditions.append(weatherConditionsArr[y])
            }
        }else{
            //error handling message
            txtDays.append("Nothing to display")
            txtMinTemperatures.append("Nothing to display")
            txtWeatherConditions.append("Nothing to display")
            txtWeatherConditions.append("Nothing to display")
        }


        //navigating back to main screen
        btnBackToMainScreen.setOnClickListener{
            val intent4 = Intent(this, MainScreen::class.java)
            startActivity(intent4)
            val tag = "DetailedViewScreen"
            Log.d(tag,"back clicked")
        }

    }
}