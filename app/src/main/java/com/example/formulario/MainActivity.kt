package com.example.formulario

import android.app.DatePickerDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var datebirth: Button
    private lateinit var spinnersex: Spinner
    private lateinit var spinnerstatus: Spinner
    private lateinit var number: EditText
    private lateinit var email: EditText
    private lateinit var tin: EditText
    private lateinit var buttonnext: Button
    private var isDateSelected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.statusBarColor = Color.parseColor("#800000")

        spinnersex = findViewById(R.id.sex_edit)

        val listsex = resources.getStringArray(R.array.sex_options)

        val adaptersex = ArrayAdapter<Any?>(this, R.layout.spinner_list, listsex)
        adaptersex.setDropDownViewResource(R.layout.spinner_list)

        spinnersex.adapter = adaptersex

        spinnerstatus = findViewById(R.id.civil_status_edit)

        val liststatus = resources.getStringArray(R.array.civil_status_options)

        val adapterstatus = ArrayAdapter<Any?>(this, R.layout.spinner_list, liststatus)
        adapterstatus.setDropDownViewResource(R.layout.spinner_list)

        spinnerstatus.adapter = adapterstatus

        datebirth = findViewById(R.id.date_edit)

        datebirth.setOnClickListener {
            showDatePicker()
        }

        number = findViewById(R.id.number_edit)

        email = findViewById(R.id.mail_edit)

        tin = findViewById(R.id.tin_edit)

        val numberPattern = Regex("^09\\d{8}")

        val emailPattern = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.com\$")

        val tinPattern = Regex("^\\d{7}-\\d$")

        buttonnext = findViewById(R.id.button_next)

        buttonnext.setOnClickListener {
            val numberText = number.text.toString()
            val emailText = email.text.toString()
            val tinText = tin.text.toString()
            if (!isDateSelected){
                Toast.makeText(this, getString(R.string.datecheck), Toast.LENGTH_SHORT).show()
            } else if (!numberText.matches(numberPattern)) {
                Toast.makeText(this, getString(R.string.numbercheck), Toast.LENGTH_SHORT).show()
            } else if (!emailText.matches(emailPattern)){
                Toast.makeText(this, getString(R.string.emailinccorrect), Toast.LENGTH_SHORT).show()
            } else if (!tinText.matches(tinPattern)){
                Toast.makeText(this, getString(R.string.tincheck), Toast.LENGTH_SHORT).show()
            } else {
                datebirth.text = getString(R.string.select)
                spinnersex.setSelection(0)
                spinnerstatus.setSelection(0)
                number.setText("")
                email.setText("")
                tin.setText("")
            }
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, yearSelected, monthOfYear, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$yearSelected"
                datebirth.text = selectedDate
                isDateSelected = true
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }
}
