package com.cst.contacts.details

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cst.contacts.R
import com.cst.contacts.contacts.RecyclerAdapter
import com.cst.contacts.donottouch.ContactInfo
import com.cst.contacts.donottouch.InfoType
import com.cst.contacts.donottouch.mapToContactInfo
import com.github.tamir7.contacts.Contact
import com.github.tamir7.contacts.Contacts

class ContactDetailedFragment(var contactId: Long, var type: String) : Fragment(R.layout.fragment_contact_datailed_sample) {

    private var numbersList = mutableListOf<String>()
    private var numbersTypesList = mutableListOf<InfoType>()

    private var emailsList = mutableListOf<String>()
    private var emailsTypesList = mutableListOf<InfoType>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("ID", getContactById(contactId).toString())
        displayContact()
    }

    private fun postNumberToList(contacts: ContactInfo) {
//        numbersList.clear()
//        numbersTypesList.clear()
        for (phoneNumber in contacts.phoneNumbers) {
            addNumberToList(phoneNumber.number, phoneNumber.type)
        }
    }

    private fun addNumberToList(number: String, type: InfoType) {
        numbersList.add(number)
        numbersTypesList.add(type)
    }


    private fun postEmailToList(contacts: ContactInfo) {
//        emailsList.clear()
//        emailsTypesList.clear()
        for (email in contacts.emails) {
            addEmailToList(email.address, email.type)
        }
    }

    private fun addEmailToList(email: String, type: InfoType) {
        emailsList.add(email)
        emailsTypesList.add(type)
    }

    private fun displayContact() {

        val contact = getContactById(contactId)

        val recyclerViewNumber = view?.findViewById<RecyclerView>(R.id.contact_detailed_number_sample)
        val recyclerViewEmail = view?.findViewById<RecyclerView>(R.id.contact_detailed_email_sample)

        Log.d("HERE", contact.toString())

        if (contact != null) {
            postNumberToList(contact)
        }
        if (contact != null) {
            postEmailToList(contact)
        }

        if (type == "NUMBER") {
            recyclerViewNumber?.layoutManager = LinearLayoutManager(view?.context)
            recyclerViewNumber?.adapter = NumberAdapter(numbersList, numbersTypesList)
        } else if (type == "EMAIL") {
            recyclerViewEmail?.layoutManager = LinearLayoutManager(view?.context)
            recyclerViewEmail?.adapter = EmailAdapter(emailsList, emailsTypesList)

        }
    }


    /** ==== ქვედა კოდს არ ეხებით, მხოლოდ სწორ ადგილას ახდენთ გამოძახებას ==== **/

    private fun getContactById(id: Long): ContactInfo? {
        return Contacts.getQuery().whereEqualTo(Contact.Field.ContactId, id)
                .find().firstOrNull()?.mapToContactInfo()
    }

}