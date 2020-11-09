package com.cst.contacts.details

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cst.contacts.R
import com.cst.contacts.contacts.ContactsActivity
import com.cst.contacts.donottouch.ContactInfo
import com.cst.contacts.donottouch.mapToContactInfo
import com.github.tamir7.contacts.Contact
import com.github.tamir7.contacts.Contacts


/**
 * ამ ექთივითის უნდა გადმოაწოდოთ კონტაქტის ID,
 * რომელიც თავის მხრივ გადააწვდის ამ ID-ს ფრაგმენტს
 */
class ContactDetailedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detailed)

//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setDisplayShowHomeEnabled(true)
//        supportActionBar?.setDisplayShowTitleEnabled(false)

        val goBack = findViewById<TextView>(R.id.go_back_button)

        goBack.setOnClickListener {
            val intent = Intent(it.context, ContactsActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }


        val id = intent.getLongExtra("id", 0)

        val contact = getContactById(id)

        val name = findViewById<TextView>(R.id.contact_name)
        val fLetter = findViewById<TextView>(R.id.contact_first_letter)
        name?.text = contact?.name
        fLetter?.text = contact?.name?.substring(0, 1)

        supportFragmentManager.beginTransaction()
                .replace(R.id.detailed_number_frame, ContactDetailedFragment(id, "NUMBER"))
                .commit()
        supportFragmentManager.beginTransaction()
                .replace(R.id.detailed_mail_frame, ContactDetailedFragment(id, "EMAIL"))
                .commit()
    }

    private fun getContactById(id: Long): ContactInfo? {
        return Contacts.getQuery().whereEqualTo(Contact.Field.ContactId, id)
                .find().firstOrNull()?.mapToContactInfo()
    }
}
