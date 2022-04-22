package com.example.a3

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper



class db(context:Context):SQLiteOpenHelper(context,DTATBASE_NAME,null,DTATBASE_VERSION) {
    companion object {
        const val DTATBASE_VERSION = 1;
        const val DTATBASE_NAME = "contact.db";
        const val TABLE_NAME = "contact_table";
        // const val COLUMN_ID = "id";
        const val COLUMN_NAME = "name";
        const val COLUMN_NUMBER = "number";
        const val CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_NAME+" TEXT,"+ COLUMN_NUMBER+" TEXT PRIMARY KEY " + ")";


    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertContact(contact:data_holder):Long{
        val db=this.writableDatabase

        val contentValues=ContentValues()


        contentValues.put(COLUMN_NAME,contact.name)
        contentValues.put(COLUMN_NUMBER,contact.phone)
        val success=db.insert(TABLE_NAME,null,contentValues)
        db.close()

        return success
    }
    fun getAllContacts(): ArrayList<data_holder> {
        val contactList: ArrayList<data_holder> = ArrayList()
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val db = this.readableDatabase
        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        }
        catch (e:Exception)
        {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var name:String
        var number:String


        if(cursor.moveToFirst())
        {
            do{

                name=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
                number=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NUMBER))

                val cont=data_holder(R.drawable.ano,name,number,number)
                contactList.add(cont)

            }while(cursor.moveToNext())
        }
        db.close()
        cursor.close()
        return contactList

    }

}