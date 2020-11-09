package com.cst.contacts.details

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cst.contacts.R
import com.cst.contacts.adapter.EmailListAdapter
import com.cst.contacts.adapter.NumberListAdapter
import com.cst.contacts.donottouch.ContactInfo
import com.cst.contacts.donottouch.mapToContactInfo
import com.cst.contacts.helper.ImageHelper
import com.github.tamir7.contacts.Contact
import com.github.tamir7.contacts.Contacts

class ContactDetailedFragment(var contactId: Long) : Fragment(R.layout.fragment_contact_detailed) {
    private lateinit var ivAvatar: ImageView
    private lateinit var tvName: TextView

    private lateinit var tvActionCall: TextView
    private lateinit var tvActionText: TextView
    private lateinit var tvActionEmail: TextView
    private lateinit var tvActionVideo: TextView

    private lateinit var llScrollElements: LinearLayout

    private lateinit var llPhoneNumbersWrapper: LinearLayout
    private lateinit var llEmailsWrapper: LinearLayout

    private lateinit var rvNumbers: RecyclerView
    private lateinit var rvEmails: RecyclerView

    private lateinit var vLastHorizontalLine: View

    private lateinit var ivBack: ImageView
    private lateinit var ivStar: ImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initListeners()
        displayContact()
    }

    private fun initViews(view: View) {
        ivAvatar = view.findViewById(R.id.ivAvatar)
        tvName = view.findViewById(R.id.tvName)

        tvActionCall = view.findViewById(R.id.tvActionCall)
        tvActionText = view.findViewById(R.id.tvActionText)
        tvActionEmail = view.findViewById(R.id.tvActionEmail)
        tvActionVideo = view.findViewById(R.id.tvActionVideo)

        llScrollElements = view.findViewById(R.id.llScrollElements)

        llPhoneNumbersWrapper = view.findViewById(R.id.llPhoneNumbersWrapper)
        llEmailsWrapper = view.findViewById(R.id.llEmailsWrapper)

        rvNumbers = view.findViewById(R.id.rvNumbers)
        rvEmails = view.findViewById(R.id.rvEmails)

        vLastHorizontalLine = view.findViewById(R.id.vLastHorizontalLine)

        ivBack = view.findViewById(R.id.ivBack)
        ivStar = view.findViewById(R.id.ivStar)
    }

    private fun initListeners() {
        tvActionCall.setOnClickListener { Toast.makeText(activity, "Call", Toast.LENGTH_SHORT).show() }
        tvActionText.setOnClickListener { Toast.makeText(activity, "Text", Toast.LENGTH_SHORT).show() }
        tvActionEmail.setOnClickListener { Toast.makeText(activity, "Email", Toast.LENGTH_SHORT).show() }
        tvActionVideo.setOnClickListener { Toast.makeText(activity, "Video", Toast.LENGTH_SHORT).show() }
        ivBack.setOnClickListener { activity?.onBackPressed() }
        ivStar.setOnClickListener { Toast.makeText(activity, "Star", Toast.LENGTH_SHORT).show() }
    }

    private fun displayContact() {
        val contact = getContactById(contactId)

        if (contact != null) {
            ivAvatar.setImageBitmap(ImageHelper.generateContactAvatar(contact.name))
            tvName.text = contact.name

            if (contact.phoneNumbers.isNotEmpty()) {
                rvNumbers.layoutManager =
                        LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                rvNumbers.adapter = NumberListAdapter(contact.phoneNumbers, activity)
            } else {
                disableAction(tvActionCall)
                disableAction(tvActionText)
                disableAction(tvActionVideo)

                llScrollElements.removeView(llPhoneNumbersWrapper)
            }

            if (contact.emails.isNotEmpty()) {
                rvEmails.layoutManager =
                        LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                rvEmails.adapter = EmailListAdapter(contact.emails)
            } else {
                disableAction(tvActionEmail)

                llScrollElements.removeView(llEmailsWrapper)
                llScrollElements.removeView(vLastHorizontalLine)
            }
        }
    }

    private fun disableAction(action: TextView) {
        action.isEnabled = false

        val disableColor = context?.let { ContextCompat.getColor(it, R.color.gray) }
        if (disableColor != null) {
            action.setTextColor(disableColor)
            action.compoundDrawables.forEach {
                if (null != it) {
                    it.colorFilter = PorterDuffColorFilter(disableColor, PorterDuff.Mode.SRC_IN)
                }
            }
        }
    }

    /** ==== ქვედა კოდს არ ეხებით, მხოლოდ სწორ ადგილას ახდენთ გამოძახებას ==== **/

    private fun getContactById(id: Long): ContactInfo? {
        return Contacts.getQuery().whereEqualTo(Contact.Field.ContactId, id)
                .find().firstOrNull()?.mapToContactInfo()
    }

}