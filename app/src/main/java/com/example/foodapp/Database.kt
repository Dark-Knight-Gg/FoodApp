package com.example.foodapp

import android.app.Activity
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteStatement
import android.view.View
import androidx.fragment.app.FragmentActivity


class Database(context: Context) : SQLiteOpenHelper(context,"Data.sql",null,1) {
    companion object{
        private var instance:Database? = null
        fun getInstance(context: Context):Database{
            if(instance==null){
                instance=Database(context)
            }
            return instance!!
        }
    }
    fun queryData(sql:String){
        val data: SQLiteDatabase = writableDatabase
        data.execSQL(sql)
    }
    fun insert(name:String , describe:String,price:Double,picture:ByteArray){
        val data = writableDatabase
        val sql ="INSERT INTO Food VALUES(null,?,?,?,?)"
        val statement:SQLiteStatement=data.compileStatement(sql)
        statement.clearBindings()
        statement.bindString(1,name)
        statement.bindString(2,describe)
        statement.bindDouble(3,price)
        statement.bindBlob(4,picture)
        statement.executeInsert()
    }
    fun isUsernameExists(username:String):Boolean{
        val db : SQLiteDatabase = readableDatabase
        val cursor :Cursor =db.query("Users",arrayOf("Username","Password"),"Username"+"=?",arrayOf(username),null,null,null)
        if(cursor!=null && cursor.moveToFirst()){
            return true
        }
        return false
    }
    fun isLogin(user:User):Boolean{
        val user0= User("null","null")
        val db : SQLiteDatabase = readableDatabase
        val cursor :Cursor =db.query("Users",arrayOf("Username","Password"),"Username"+"=?",arrayOf(user.username),null,null,null)
        if(cursor!=null && cursor.moveToFirst()){
            val user1:User =User(cursor.getString(0),cursor.getString(1))
            if(user.password.equals(user1.password)){
                return true
            }
        }
        return false
    }
    fun getData(sql:String):Cursor{
        val data:SQLiteDatabase =readableDatabase
        return data.rawQuery(sql,null)
    }
    override fun onCreate(db: SQLiteDatabase?) {

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}