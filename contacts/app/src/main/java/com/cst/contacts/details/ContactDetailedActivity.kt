package com.cst.contacts.details

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.cst.contacts.R

class ContactDetailedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detailed)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val id = intent.getLongExtra("id", 0)
        supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayoutContactDetailed, ContactDetailedFragment(id))
                .commit()
    }

}
