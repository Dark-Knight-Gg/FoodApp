package com.example.foodapp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdminAdapter(private val context:MainActivity4,private val listFood:ArrayList<Food>):RecyclerView.Adapter<AdminAdapter.Viewholder>() {
    class Viewholder(view: View):RecyclerView.ViewHolder(view){
        var adminImg : ImageView = view.findViewById(R.id.adminImg)
        var adminTxtName :TextView=view.findViewById(R.id.adminTxtname)
        var adminTxtDescribe :TextView = view.findViewById(R.id.adminTxtDescribe)
        var adminTxtPrice :TextView =view.findViewById(R.id.adminTxtPrice)
        var adminImgDelete:ImageView = view.findViewById(R.id.adminImgDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {

        var view:View = LayoutInflater.from(context).inflate(R.layout.custom_recyclerview_admin,parent,false)
        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {

        val food =listFood[position]
        val picture:ByteArray =food.picture
        val bitmap : Bitmap = BitmapFactory.decodeByteArray(picture,0,picture.size)
        holder.adminTxtName.setText(food.name)
        holder.adminTxtDescribe.setText(food.describe)
        holder.adminTxtPrice.setText(food.price.toString())
        holder.adminImg.setImageBitmap(bitmap)
        holder.adminImgDelete.setOnClickListener(){
            context.diaLogDelete(food.id)
        }
    }

    override fun getItemCount(): Int {

        return listFood.size
    }
}