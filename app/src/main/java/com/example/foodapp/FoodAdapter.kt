package com.example.foodapp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FoodAdapter(private var listFood:ArrayList<Food>):RecyclerView.Adapter<FoodAdapter.ViewHolder>() {
    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
            var customImg : ImageView = view.findViewById(R.id.customImg)
            var customTxtName : TextView = view.findViewById(R.id.customTxtName)
            var customTxtDescribe : TextView = view.findViewById(R.id.customTxtDescribe)
            var customTxtPrice : TextView = view.findViewById(R.id.customTxtPrice)
            var customEdtCount : EditText = view.findViewById(R.id.customEdtCount)
            var customBtn: Button = view.findViewById(R.id.customBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.custom_recyclerviewfood,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var food = listFood[position]
        val picture:ByteArray =food.picture
        val bitmap :Bitmap =BitmapFactory.decodeByteArray(picture,0,picture.size)
        holder.customTxtName.setText(food.name)
        holder.customTxtDescribe.setText(food.describe)
        holder.customTxtPrice.setText(food.price.toString())
        holder.customImg.setImageBitmap(bitmap)
    }

    override fun getItemCount(): Int {
        return listFood.size
    }
}