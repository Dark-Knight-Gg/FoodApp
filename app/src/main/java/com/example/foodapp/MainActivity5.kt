package com.example.foodapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.foodapp.databinding.ActivityMain5Binding
import java.io.ByteArrayOutputStream
import java.io.InputStream

class MainActivity5 : AppCompatActivity() {
    private val Code1=1
    private val Code2 =2
    private var database = Database.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)
        val binding : ActivityMain5Binding = DataBindingUtil.setContentView(this,R.layout.activity_main5)
        binding.adminBtnCancle.setOnClickListener(){
            Toast.makeText(this,"Đã huỷ!",Toast.LENGTH_SHORT).show()
            intent = Intent(this,MainActivity4::class.java)
            startActivity(intent)
        }
        binding.addImgCamera.setOnClickListener(){
            intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent,Code1)
        }
        binding.addImgFoder.setOnClickListener(){
            intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, Code2)
        }
        binding.adminBtnAdd.setOnClickListener(){
            val name = binding.addEdtName.text.toString()
            val describe  = binding.addEdtDescribe.text.toString()
            val price = binding.addEdtPrice.text.toString()
            val priceD:Double = price.toDouble()
            val bitmapDrawable :BitmapDrawable = binding.addImg.drawable as BitmapDrawable
            val bitmap :Bitmap =bitmapDrawable.bitmap
            val byteArrayOutputStream :ByteArrayOutputStream= ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream)
            val picture :ByteArray =byteArrayOutputStream.toByteArray()
            database.insert(name,describe,priceD,picture)
            Toast.makeText(this,"Đã thêm thành công!",Toast.LENGTH_SHORT).show()
            intent = Intent(this,MainActivity4::class.java)
            startActivity(intent)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val binding : ActivityMain5Binding = DataBindingUtil.setContentView(this,R.layout.activity_main5)
        if(requestCode==Code1&&resultCode== RESULT_OK&& data!=null){
            val bitmap :Bitmap = data.extras?.get("data") as Bitmap
            binding.addImg.setImageBitmap(bitmap)
        }
        if(requestCode==Code2 && resultCode== RESULT_OK && data!=null){
            val uri : Uri? =data.data
            val inputStream : InputStream? = uri?.let { contentResolver.openInputStream(it) }
            val bitmap:Bitmap =BitmapFactory.decodeStream(inputStream)
            binding.addImg.setImageBitmap(bitmap)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}