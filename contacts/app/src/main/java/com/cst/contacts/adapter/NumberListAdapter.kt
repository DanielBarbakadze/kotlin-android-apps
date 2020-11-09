package com.cst.contacts.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.cst.contacts.R
import com.cst.contacts.contacts.getString
import com.cst.contacts.donottouch.PhoneNumber

class NumberListAdapter(private val list: List<PhoneNumber>, private val context: FragmentActivity?) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_number, parent, false)

        return NumberViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NumberViewHolder) {
            holder.setContent(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class NumberViewHolder(itemView: View, private val context: FragmentActivity?) : RecyclerView.ViewHolder(itemView) {
    private val tvPhoneNumber = itemView.findViewById<TextView>(R.id.tvPhoneNumber)
    private val tvPhoneType = itemView.findViewById<TextView>(R.id.tvPhoneType)
    private val ivMessage = itemView.findViewById<ImageView>(R.id.ivMessage)

    fun setContent(number: PhoneNumber) {
        with(number) {
            tvPhoneNumber.text = this.number
            tvPhoneType.text = type.getString()
        }

        ivMessage.setOnClickListener { Toast.makeText(context, "Text", Toast.LENGTH_SHORT).show() }
    }

}
