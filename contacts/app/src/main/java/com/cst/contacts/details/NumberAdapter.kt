package com.cst.contacts.details

import android.content.Intent
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

class NumberAdapter(private var numbersList: List<String>, private var typesList: List<InfoType>) : RecyclerView.Adapter<NumberAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val itemNumber: TextView = itemView.findViewById(R.id.contact_number)
        val itemType: TextView = itemView.findViewById(R.id.contact_type)
        val itemInfoIcon: TextView = itemView.findViewById(R.id.contact_info_icon)

//        init {
//            itemView.setOnClickListener {
//                val position: Int = adapterPosition
//                Toast.makeText(itemView.context, "You clicked on item # ${position + 1}", Toast.LENGTH_SHORT).show()
//            }
//        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberAdapter.ViewHolder {
        Log.d("SETHERE","created $numbersList")
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_contact_detailed_number, parent, false);
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return numbersList.size
    }

    override fun onBindViewHolder(holder: NumberAdapter.ViewHolder, position: Int) {
        Log.d("SETHERE",numbersList.toString())
        holder.itemNumber.text = numbersList[position]
        holder.itemType.text = typesList[position].toString()
        holder.itemInfoIcon.setBackgroundResource(R.drawable.ic_phone)
    }

}