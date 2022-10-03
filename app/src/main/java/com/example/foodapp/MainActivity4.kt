package com.example.foodapp

import android.app.Dialog
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.databinding.ActivityMain4Binding

class MainActivity4 : AppCompatActivity() {
    private val listFood = ArrayList<Food>()
    private val database = Database.getInstance(this)
    private lateinit var adp: AdminAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
        val binding: ActivityMain4Binding = DataBindingUtil.setContentView(this, R.layout.activity_main4)
        adp = AdminAdapter(this,listFood)
        binding.adminRecyclerview.layoutManager=LinearLayoutManager(this)
        binding.adminRecyclerview.adapter=adp
        getData()
        binding.adminBtnAdd.setOnClickListener(){
            intent = Intent(this, MainActivity5::class.java)
            startActivity(intent)
        }
    }

    private fun getData() {
        val cursor: Cursor = database.getData("SELECT * FROM Food")
        listFood.clear()
        while (cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val name = cursor.getString(1)
            val describe = cursor.getString(2)
            val price = cursor.getDouble(3)
            val picture = cursor.getBlob(4)
            listFood.add(Food(id,name, describe, price,picture))
        }
        adp.notifyDataSetChanged()
    }
    fun diaLogDelete(position:Int){
        val dialog =Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.delete_dialog)
        val dialogDeleteTxtTitle :TextView = dialog.findViewById(R.id.dialogDeleteTxtTitle)
        val dialogDeleteBtnYes :Button =dialog.findViewById(R.id.dialogDeleteBtnYes)
        val dialogDeleteBtnNo :Button =dialog.findViewById(R.id.dialogDeleteBtnNo)
        dialogDeleteBtnNo.setOnClickListener(){
            Toast.makeText(this,"Đã hủy!",Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        dialogDeleteBtnYes.setOnClickListener(){
            database.queryData("DELETE FROM Food WHERE Id= '$position'")
            Toast.makeText(this,"Đã xóa thành công!",Toast.LENGTH_SHORT).show()
            getData()
            dialog.dismiss()
        }
        dialog.show()
    }
}