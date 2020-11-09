package com.example.play_store_app_page_ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val uninstallButton = findViewById<Button>(R.id.uninstallButton)
        uninstallButton.setOnClickListener {
            Snackbar.make(it,"UNINSTALL",Snackbar.LENGTH_LONG).setAction("Action",null).show()
        }
        val openButton = findViewById<Button>(R.id.openButton)
        openButton.setOnClickListener {
            Snackbar.make(it,"OPEN",Snackbar.LENGTH_LONG).setAction("Action",null).show()
        }
        val travelButton = findViewById<TextView>(R.id.travelImage)
        travelButton.setOnClickListener {
            Snackbar.make(it,"Travel & Local",Snackbar.LENGTH_LONG).setAction("Action",null).show()
        }
        val similarButton = findViewById<TextView>(R.id.similarImage)
        similarButton.setOnClickListener {
            Snackbar.make(it,"Similar",Snackbar.LENGTH_LONG).setAction("Action",null).show()
        }
    }
}