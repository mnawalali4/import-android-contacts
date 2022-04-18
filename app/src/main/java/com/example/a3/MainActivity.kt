package com.example.a3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<adaptar.ViewHolder>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layoutManager = LinearLayoutManager(this)
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview.layoutManager=LinearLayoutManager(this)

        var mydatalist = ArrayList<data_holder>()

        for (i in 1..10)
        {
            mydatalist.add(data_holder(R.drawable.ano, "Item ","033","bio"))
        }
        adapter = adaptar(mydatalist)
        recyclerview.adapter=adapter
    }
}