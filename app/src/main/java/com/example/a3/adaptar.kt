package com.example.a3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class adaptar(private val datalist: List<data_holder>) : RecyclerView.Adapter<adaptar.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adaptar.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: adaptar.ViewHolder, position: Int) {
        val dataHolder = datalist[position]
        holder.itemtittle.text = dataHolder.name
        holder.itemimage.setImageResource(dataHolder.img)
        holder.itemphone.text = dataHolder.phone
        holder.itembio.text = dataHolder.bio
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        var itemimage: ImageView = itemView.findViewById(R.id.itemimage)
        var itemtittle: TextView = itemView.findViewById(R.id.itemtittle)
        var itemphone: TextView = itemView.findViewById(R.id.itemphone)
        var itembio: TextView = itemView.findViewById(R.id.itembio)

    }
}