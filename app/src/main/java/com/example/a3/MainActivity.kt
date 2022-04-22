package com.example.a3

import android.content.ContentResolver
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

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

        getcontacts()


    }

    fun getcontacts()
    {
        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_CONTACTS),0);
        }

        var contentResolver: ContentResolver =getContentResolver()
        var uri: Uri =ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        var cursor: Cursor?=contentResolver.query(uri,null,null,null,null)

        if (cursor != null) {
            if(cursor.count >0)
            {
                Log.i("demo", "total contacts: " + Integer.toString(cursor.count))

                while (cursor.moveToNext())
                {
                    var contactName: String =
                        cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                    var contactNumber: String =
                        cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    var address: String = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DATA))

                    Log.i("demo", contactName+address)
                }
            }

        }
    }

}




