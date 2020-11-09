package com.cst.contacts.details

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.cst.contacts.R
import com.cst.contacts.donottouch.InfoType
import java.util.*

class EmailAdapter(private var emailsList: List<String>, private var typesList: List<InfoType>) : RecyclerView.Adapter<EmailAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val itemNumber: TextView = itemView.findViewById(R.id.contact_number)
        val itemType: TextView = itemView.findViewById(R.id.contact_type)
        val itemInfoIcon: TextView = itemView.findViewById(R.id.contact_info_icon)

        init {
            itemView.setOnClickListener {
                val position: Int = adapterPosition
                Toast.makeText(itemView.context, "You clicked on item # ${position + 1}", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailAdapter.ViewHolder {
        Log.d("SET2HERE","aqmaincshemodiblaid")
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_contact_detailed_number, parent, false);
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return emailsList.size
    }

    override fun onBindViewHolder(holder: EmailAdapter.ViewHolder, position: Int) {
        holder.itemNumber.text = emailsList[position]
        holder.itemType.text = typesList[position].toString()
        holder.itemInfoIcon.setBackgroundResource(R.drawable.ic_email)
    }

}