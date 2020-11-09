package com.cst.contacts.contacts

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cst.contacts.R
import com.cst.contacts.details.ContactDetailedActivity
import com.cst.contacts.donottouch.ContactInfo
import com.cst.contacts.donottouch.mapToContactInfo
import com.github.tamir7.contacts.Contacts

class ContactsFragment : Fragment(R.layout.fragment_contacts) {

    private var lettersList = mutableListOf<String>()
    private var fullNamesList = mutableListOf<String>()
    private var idsList = mutableListOf<Long>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermissionsAndGetContacts()

        val name = getView()?.findViewById<EditText>(R.id.name)

        name?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                bitch(s)
            }
        })

    }

    private fun bitch(text: Editable) {

        val filteredContacts = getContacts().filter { it.name.contains(text.toString(), ignoreCase = true) }

        for (contact in filteredContacts) {
            Log.d("TAG", contact.name)
        }
        displayContacts(filteredContacts)

        Toast.makeText(activity, "$text", Toast.LENGTH_SHORT).show()
    }

    private fun addToList(letter: String, fullName: String, id: Long) {
        lettersList.add(letter)
        fullNamesList.add(fullName)
        idsList.add(id)
    }

    private fun postToList(contacts: List<ContactInfo>) {
        lettersList.clear()
        fullNamesList.clear()
        idsList.clear()
        for (contact in contacts) {
            addToList(contact.name.substring(0, 1), contact.name, contact.id)
        }
    }

    private fun displayContacts(contacts: List<ContactInfo>) {
        /** ==== თქვენი კოდი ==== **/
        val recyclerView = view?.findViewById<RecyclerView>(R.id.contact_list_recycler)
        postToList(contacts)

        if (recyclerView != null) {
            recyclerView.layoutManager = LinearLayoutManager(view?.context)
        }
        if (recyclerView != null) {
            recyclerView.adapter = RecyclerAdapter(lettersList, fullNamesList, idsList)
        }
    }

    /** ======== ამის ქვევით კოდს არ შეეხოთ ============= **/

    private fun checkPermissionsAndGetContacts() {
        if (ContextCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.READ_CONTACTS
                ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                    arrayOf(Manifest.permission.READ_CONTACTS),
                    123
            )
        } else {
            displayContacts(getContacts())
        }
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        if (requestCode == 123 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            displayContacts(getContacts())
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun getContacts() =
            Contacts.getQuery().find().map {
                it.mapToContactInfo()
            }
}