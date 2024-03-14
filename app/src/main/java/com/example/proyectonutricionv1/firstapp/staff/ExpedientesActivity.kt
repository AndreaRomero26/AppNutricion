package com.example.proyectonutricionv1.firstapp.staff

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.example.proyectonutricionv1.R
import com.example.proyectonutricionv1.firstapp.DBHelper

class ExpedientesActivity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expedientes)

        dbHelper = DBHelper(this)
        val dataList = dbHelper.getAllData()

        val tableLayout: TableLayout = findViewById(R.id.tableLayout)

        for ((value1, value2, value3,value4, value5, value6, value7, value8, value9, value10, value11, value12) in dataList) {
            val tableRow = TableRow(this)
            val layoutParams = TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT
            )
            tableRow.layoutParams = layoutParams

            val textView1 = TextView(this)
            textView1.text = value1
            textView1.setPadding(10, 10, 10, 10)
            val textView2 = TextView(this)
            textView2.text = value2
            textView2.setPadding(10, 10, 10, 10)
            val textView3 = TextView(this)
            textView3.text = value3
            textView3.setPadding(10, 10, 10, 10)
            val textView4 = TextView(this)
            textView4.text = value4
            textView4.setPadding(10, 10, 10, 10)
            val textView5 = TextView(this)
            textView5.text = value5
            textView5.setPadding(10, 10, 10, 10)
            val textView6 = TextView(this)
            textView6.text = value6
            textView6.setPadding(10, 10, 10, 10)
            val textView7 = TextView(this)
            textView7.text = value7
            textView7.setPadding(10, 10, 10, 10)
            val textView8 = TextView(this)
            textView8.text = value8
            textView8.setPadding(10, 10, 10, 10)
            val textView9 = TextView(this)
            textView9.text = value9
            textView9.setPadding(10, 10, 10, 10)
            val textView10 = TextView(this)
            textView10.text = value10
            textView10.setPadding(10, 10, 10, 10)
            val textView11 = TextView(this)
            textView11.text = value11
            textView11.setPadding(10, 10, 10, 10)
            val textView12 = TextView(this)
            textView12.text = value12
            textView12.setPadding(10, 10, 10, 10)

            tableRow.addView(textView1)
            tableRow.addView(textView2)
            tableRow.addView(textView3)
            tableRow.addView(textView4)
            tableRow.addView(textView5)
            tableRow.addView(textView6)
            tableRow.addView(textView7)
            tableRow.addView(textView8)
            tableRow.addView(textView9)
            tableRow.addView(textView10)
            tableRow.addView(textView11)
            tableRow.addView(textView12)

            tableLayout.addView(tableRow)
        }

    }
}