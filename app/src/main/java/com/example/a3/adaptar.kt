package com.example.a3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class adaptar : RecyclerView.Adapter<adaptar.ViewHolder>() {
    private var tittle = arrayOf("nawal", "ali","nawal")
    private var images = intArrayOf(R.drawable.ano,R.drawable.ano,R.drawable.ano)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adaptar.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: adaptar.ViewHolder, position: Int) {
       holder.itemtittle.text = tittle[position]
        holder.itemimage.setImageResource(images[position])
    }

    override fun getItemCount(): Int {
        return tittle.size
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        var itemimage: ImageView
        var itemtittle: TextView

        init {
            itemimage = itemView.findViewById(R.id.itemimage)
            itemtittle = itemView.findViewById(R.id.itemtittle)
        }
    }
}