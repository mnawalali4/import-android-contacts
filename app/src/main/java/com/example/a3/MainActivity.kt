package com.example.a3

import android.content.ContentResolver
import android.content.ContentUris
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.BufferedInputStream
import java.io.InputStream


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

        /*for (i in 1..10)
        {
            mydatalist.add(data_holder(R.drawable.ano, "Item ","033","bio"))
        }*/

        var dataBase = db(this)

        getcontacts()

        mydatalist=dataBase.getAllContacts()

        adapter = adaptar(mydatalist)
        recyclerview.adapter=adapter




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

        var database:db
        database= db(this)

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




                    val contact=data_holder(R.drawable.ano,contactName,contactNumber,contactName)
                    var status=database.insertContact(contact)
                    if(status>-1)
                    {
                        Log.d("TAG", "Contact added")
                    }

                  //  Log.i("demo", contactName+address)
                }
            }

        }
    }

}




