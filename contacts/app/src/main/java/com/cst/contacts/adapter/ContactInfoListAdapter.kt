package com.cst.contacts.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cst.contacts.R
import com.cst.contacts.donottouch.ContactInfo
import com.cst.contacts.helper.ImageHelper

class ContactInfoListAdapter(private val list: List<ContactInfo>,
                             private val cellClickListener: CellClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_contact_info, parent, false)

        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ContactViewHolder) {
            holder.setContent(list[position])
            holder.itemView.setOnClickListener {
                cellClickListener.onCellClickListener(list[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        if (0 == position) {
            return WITH_LETTER
        }

        return if (list[position - 1].name[0] != list[position].name[0]) WITH_LETTER else WITHOUT_LETTER
    }

    companion object {
        const val WITH_LETTER = 1
        const val WITHOUT_LETTER = 2
    }

}

class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvUniqueLetter = itemView.findViewById<TextView>(R.id.tvUniqueLetter)
    private val ivAvatar = itemView.findViewById<ImageView>(R.id.ivAvatar)
    private val tvName = itemView.findViewById<TextView>(R.id.tvName)

    fun setContent(userInfo: ContactInfo) {
        with(userInfo) {
            tvName.text = name

            if (ContactInfoListAdapter.WITH_LETTER == itemViewType) {
                tvUniqueLetter.text = name[0].toString()
            }

            ivAvatar.setImageBitmap(ImageHelper.generateContactAvatar(name))
        }
    }

}

interface CellClickListener {
    fun onCellClickListener(contact: ContactInfo)
}
