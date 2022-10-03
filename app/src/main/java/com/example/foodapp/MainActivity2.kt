package com.example.foodapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {
    private lateinit var registerTxtTitle:TextView
    private lateinit var registerEdtUsername:EditText
    private lateinit var registerEdtPassword :EditText
    private lateinit var registerEdtRepeatPassword:EditText
    private lateinit var registerBtnRegister:Button
    var database = Database.getInstance(this)
    private fun AnhXa(){
        registerTxtTitle =findViewById(R.id.registerTxtTitle)
        registerEdtUsername = findViewById(R.id.registerEdtUsername)
        registerEdtPassword = findViewById(R.id.registerEdtPassword)
        registerEdtRepeatPassword = findViewById(R.id.registerEdtRepeatPassword)
        registerBtnRegister = findViewById(R.id.registerBtnRegister)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        AnhXa()
        registerBtnRegister.setOnClickListener(){
            val username = registerEdtUsername.text.toString()
            val password = registerEdtPassword.text.toString()
            val repeatpassword = registerEdtRepeatPassword.text.toString()
            if(!database.isUsernameExists(username)){
                if(password.equals(repeatpassword)){
                    database.queryData("INSERT INTO Users VALUES(null,'$username','$password')")
                    intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this,"Nhắc lại mật khẩu chưa đúng!",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Tên tài khảon đã tồn tại!",Toast.LENGTH_SHORT).show()
            }
        }
    }
}