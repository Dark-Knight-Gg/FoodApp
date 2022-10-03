package com.example.foodapp

import android.annotation.SuppressLint
import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentOne :Fragment() {
    private var listFood = ArrayList<Food>()
    private lateinit var fragmentOneRecyclderView:RecyclerView
    private lateinit var adp :FoodAdapter
    private val database= activity?.let { Database.getInstance(it) }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view :View = inflater.inflate(R.layout.fragment_one,container,false)
        fragmentOneRecyclderView =view.findViewById(R.id.fragOneRecyclerView)
        adp = FoodAdapter(listFood)
        fragmentOneRecyclderView.layoutManager =LinearLayoutManager(activity)
        fragmentOneRecyclderView.adapter=adp
        getDt()
        return view
    }
    private fun getDt(){
        val cursor: Cursor? = database?.getData("SELECT * FROM Food")
        listFood.clear()
        if (cursor != null) {
            while(cursor.moveToNext()){
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val describe = cursor.getString(2)
                val price = cursor.getDouble(3)
                val picture = cursor.getBlob(4)
                listFood.add(Food(id,name,describe,price,picture))
            }
        }
    }
}