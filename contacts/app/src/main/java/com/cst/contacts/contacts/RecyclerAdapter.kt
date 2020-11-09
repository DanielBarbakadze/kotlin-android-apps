package com.cst.contacts.contacts

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.cst.contacts.R
import com.cst.contacts.details.ContactDetailedActivity
import com.github.tamir7.contacts.Contact
import java.util.*


open class RecyclerAdapter(private var letter: List<String>, private var fullName: List<String>, private var idsList: List<Long>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val itemLetter: TextView = itemView.findViewById(R.id.ContactLetter)
        val itemFullName: TextView = itemView.findViewById(R.id.ContactFullName)
        val itemOvalLetter: TextView = itemView.findViewById(R.id.ContactOvalLetter)

        init {
            itemView.setOnClickListener {
                val position: Int = adapterPosition
                Toast.makeText(itemView.context, "You clicked on item # ${position + 1}", Toast.LENGTH_SHORT).show()

                val intent = Intent(it.context, ContactDetailedActivity::class.java)
                intent.putExtra("id", idsList[position])
                it.context.startActivity(intent)

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.contact_list_item, parent, false);
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return letter.size
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.itemLetter.text = letter[position]
        if (position > 0 && letter[position] == letter[position - 1]) {
            holder.itemLetter.setTextColor(Color.TRANSPARENT)
        }
        holder.itemFullName.text = fullName[position]
        holder.itemOvalLetter.text = letter[position]
        val rnd = Random()
        val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        holder.itemOvalLetter.background.setTint(color)
    }

}