package com.example.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*

class MainActivity : AppCompatActivity() {
   private var tvselectedDate:TextView?=null
    private var tvAgeInMinutes:TextView?=null
    


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker:Button=findViewById(R.id.btnDatePicker)
       tvselectedDate=findViewById(R.id.selectedDate)
        tvAgeInMinutes=findViewById(R.id.tvAgeInMinutes)


        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }

    }
    fun clickDatePicker(){
        val myCalender=Calendar.getInstance()
        val year=myCalender.get(Calendar.YEAR)
        val month=myCalender.get(Calendar.MONTH)
        val day=myCalender.get(Calendar.DAY_OF_MONTH)



       val dpd= DatePickerDialog(this,
           //        DatePickerDialog.OnDateSetListener
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->

                val selectedDate="$selectedDayOfMonth/$selectedMonth/$selectedYear"
                tvselectedDate?.text=selectedDate

                val sdf=SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
                val theDate=sdf.parse(selectedDate)
                theDate?.let {
                    val selectedDateInMinutes=theDate.time/60000

                    val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMinutes=currentDate.time/60000
                        val differenceInMinutes=currentDateInMinutes - selectedDateInMinutes
                        tvAgeInMinutes?.text=differenceInMinutes.toString()
                    }
                }

            },year,month,day
            )
        dpd.datePicker.maxDate=System.currentTimeMillis()
        dpd.show()
        //-86400000

    }
}