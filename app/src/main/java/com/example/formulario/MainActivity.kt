package com.example.formulario

import android.app.DatePickerDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.statusBarColor = Color.parseColor("#800000")

        val spinnersex = findViewById<Spinner>(R.id.sex_edit)

        val listsex = resources.getStringArray(R.array.sex_options)

        val adaptersex = ArrayAdapter<Any?>(this, R.layout.spinner_list, listsex)
        adaptersex.setDropDownViewResource(R.layout.spinner_list)

        spinnersex.adapter = adaptersex

        val spinnerstatus = findViewById<Spinner>(R.id.civil_status_edit)

        val liststatus = resources.getStringArray(R.array.civil_status_options)

        val adapterstatus = ArrayAdapter<Any?>(this, R.layout.spinner_list, liststatus)
        adapterstatus.setDropDownViewResource(R.layout.spinner_list)

        spinnerstatus.adapter = adapterstatus

        val datebirth = findViewById<Button>(R.id.date_edit)

        datebirth.setOnClickListener {
            showDatePicker()
        }
    }
    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, yearSelected, monthOfYear, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$yearSelected"
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }
}