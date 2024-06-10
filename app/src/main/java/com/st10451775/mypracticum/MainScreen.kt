package com.st10451775.mypracticum

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainScreen : AppCompatActivity() {
    //initialising widgets
    private lateinit var btnSaveAll : Button
    private lateinit var btnDetailedViewScreen : Button
    private lateinit var btnBackToMainAct: Button

    private lateinit var  edtDay : EditText
    private lateinit var  edtMinTemperature: EditText
    private lateinit var  edtMaxTemperature : EditText
    private lateinit var  edtWeatherCondition : EditText
    //declaring arrays an lists
    val arrDays = arrayOfNulls<String>(7)
    val arrMinTemps = arrayOfNulls<String>(7)
    val arrMaxTemps = arrayOfNulls<String>(7)
    val arrWeatherConditions = arrayOfNulls<String>(7)

    //ARRAY COUNTERS
    private var x =0
    var sumOfMinTemps = 0
    var sumOfMaxTemps = 0
    var average = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //using findViewByID for widgets
        btnSaveAll.findViewById<Button>(R.id.btnSaveAll)
        btnBackToMainAct.findViewById<Button>(R.id.btnBackToMainAct)
        btnDetailedViewScreen.findViewById<Button>(R.id.btnDetailedViewScreen)

        edtDay.findViewById<EditText>(R.id.edtDay)
        edtMinTemperature.findViewById<EditText>(R.id.edtMinTemperature)
        edtMaxTemperature.findViewById<EditText>(R.id.edtMaxTemperature)
        edtWeatherCondition.findViewById<EditText>(R.id.edtWeatherCondition)


        //extracting user input into arrays
        btnSaveAll.setOnClickListener{
            if(edtDay != null && edtMaxTemperature != null && edtMinTemperature != null && edtWeatherCondition !=null) {

                if (x in 0 .. 6){
                    arrDays[x] = edtDay.text.toString()
                    arrMinTemps[x] = edtMinTemperature.text.toString()
                    arrMaxTemps[x] = edtMaxTemperature.text.toString()
                    arrWeatherConditions[x] = edtWeatherCondition.text.toString()

                    sumOfMinTemps += edtMinTemperature.text.toString().toInt()
                    sumOfMaxTemps += edtMaxTemperature.text.toString().toInt()
                    x += 1
                    //clear edit texts
                    edtDay.text.clear()
                    edtMaxTemperature.text.clear()
                    edtMaxTemperature.text.clear()
                    edtMinTemperature.text.clear()



                    //displaying suitable message and logging changes
                    Toast.makeText(this, "Saved successfully", Toast.LENGTH_SHORT).show()
                    val TAG2 = "MainScreen"
                    Log.d(TAG2, "Values saved to arrays")
                }

            }else{
                //error handling
                Toast.makeText(this, "Make sure all necessary data has been input", Toast.LENGTH_LONG).show()
            }
            //calculate average
            average = (sumOfMaxTemps+sumOfMaxTemps)/7


        }


        //going back to main activity
        btnBackToMainAct.setOnClickListener{
            val intent2 = Intent(this,MainActivity::class.java)
            startActivity(intent2)
        }

        //navigate to detailed view and sending  data
        btnDetailedViewScreen.setOnClickListener{
            val intent3 = Intent(this,DetailedViewScreen::class.java)
            val bundle = Bundle()
            bundle.putStringArray("daysArray", arrDays)
            bundle.putStringArray("minTempArray", arrMinTemps)
            bundle.putStringArray("MaxTempsArray", arrMaxTemps)
            bundle.putStringArray("weatherConditionArray", arrWeatherConditions)
            bundle.putFloat("averageTemps", average.toFloat())
            intent.putExtras(bundle)
            startActivity(intent3)
        }

    }

}