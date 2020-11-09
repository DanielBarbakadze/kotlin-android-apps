package com.cst.contacts.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cst.contacts.R
import com.cst.contacts.contacts.getString
import com.cst.contacts.donottouch.Email

class EmailListAdapter(private val list: List<Email>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_email, parent, false)

        return EmailViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is EmailViewHolder) {
            holder.setContent(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class EmailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvEmail = itemView.findViewById<TextView>(R.id.tvEmail)
    private val tvEmailType = itemView.findViewById<TextView>(R.id.tvEmailType)

    fun setContent(email: Email) {
        with(email) {
            tvEmail.text = address
            tvEmailType.text = type.getString()
        }
    }

}
