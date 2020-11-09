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
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cst.contacts.R
import com.cst.contacts.adapter.CellClickListener
import com.cst.contacts.adapter.ContactInfoListAdapter
import com.cst.contacts.details.ContactDetailedActivity
import com.cst.contacts.donottouch.ContactInfo
import com.cst.contacts.donottouch.InfoType
import com.cst.contacts.donottouch.mapToContactInfo
import com.github.tamir7.contacts.Contacts

fun InfoType.getString() =
        when (this) {
            InfoType.HOME -> "Home"
            InfoType.MOBILE -> "Mobile"
            InfoType.WORK -> "Work"
            else -> "Other"
        }

class ContactsFragment : Fragment(R.layout.fragment_contacts), CellClickListener {

    private lateinit var etFilter: EditText
    private lateinit var recyclerView: RecyclerView

    private var contacts = getContacts()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initListeners()
        checkPermissionsAndGetContacts()
    }

    private fun displayContacts(contacts: List<ContactInfo>) {
        recyclerView.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = ContactInfoListAdapter(contacts, this)
    }

    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.rvContacts)
        etFilter = view.findViewById(R.id.etFilter)
    }

    private fun initListeners() {
        etFilter.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                Log.d("ContactsFragment", "afterTextChanged: " + s.toString())

                val contacts =
                        contacts.filter { it.name.contains(s.toString(), ignoreCase = true) }

                displayContacts(contacts)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    override fun onCellClickListener(contact: ContactInfo) {
        val intent = Intent(activity, ContactDetailedActivity::class.java)
        intent.putExtra("id", contact.id)
        startActivity(intent)
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