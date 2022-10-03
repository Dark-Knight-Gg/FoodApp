package com.example.foodapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.foodapp.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        val binding :ActivityMain3Binding = DataBindingUtil.setContentView(this,R.layout.activity_main3)
        val fragmentOne = FragmentOne()
        val fragmentTwo = FragmentTwo()
        val fragmentManager:FragmentManager = supportFragmentManager
        val fragmentTransaction :FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.foodFragOne,fragmentOne)
        fragmentTransaction.add(R.id.foodFragTwo,fragmentTwo)
        fragmentTransaction.commit()
    }
}