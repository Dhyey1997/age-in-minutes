package dd.tutorials.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate : TextView? = null
    private var tvAgeInMinutes : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
            tvSelectedDate = findViewById(R.id.tvSelectedDate)
            tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)

        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker() {

        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, { view, selectedYear, selectedMonth, selectedDayOfMonth ->
            Toast.makeText(this, "Selected Date is $selectedDayOfMonth-${selectedMonth + 1}-$selectedYear", Toast.LENGTH_LONG).show()

            val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"

            tvSelectedDate?.text = selectedDate

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val date = sdf.parse(selectedDate)
            date?.let {
                val selectedDateInMinutes = date.time / 60000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                currentDate?.let {
                    val currentDateInMinutes = currentDate.time / 60000
                    val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                    tvAgeInMinutes?.text = differenceInMinutes.toString()
                }
            }
        }, year, month, day)

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}