package com.example.foodapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.*

class MainActivity : AppCompatActivity() {
    private lateinit var loginTxtTitle : TextView
    private lateinit var loginEdtUsername:EditText
    private lateinit var loginEdtPassword :EditText
    private lateinit var loginCheckbox :CheckBox
    private lateinit var loginBtnLogin :Button
    private lateinit var loginBtnRegister:Button
    private lateinit var sharedPreferences: SharedPreferences
     var database = Database.getInstance(this)
    private fun AnhXa(){
        loginTxtTitle = findViewById(R.id.loginTxtTitle)
        loginEdtUsername = findViewById(R.id.loginEdtUsername)
        loginEdtPassword = findViewById(R.id.loginEdtPassword)
        loginCheckbox = findViewById(R.id.loginCheckbox)
        loginBtnLogin = findViewById(R.id.loginBtnLogin)
        loginBtnRegister = findViewById(R.id.loginBtnRegister)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AnhXa()
        sharedPreferences =getSharedPreferences("Data", MODE_PRIVATE)
        //lấy giá trị rồi gán
        loginEdtUsername.setText(sharedPreferences.getString("Username",""))
        loginEdtPassword.setText(sharedPreferences.getString("Password",""))
        database.queryData("CREATE TABLE IF NOT EXISTS Users(Id INTEGER PRIMARY KEY AUTOINCREMENT, Username NVARCHAR(200), Password NVARCHAR(200))")
        database.queryData("CREATE TABLE IF NOT EXISTS Food(Id INTEGER PRIMARY KEY AUTOINCREMENT,Name NVARCHAR(200), Describe NVARCHAR(400),Price DOUBLE,Picture BLOB)")
        loginBtnRegister.setOnClickListener(){
            intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
        loginBtnLogin.setOnClickListener(){
            val username = loginEdtUsername.text.toString()
            val password = loginEdtPassword.text.toString()
            val flag:Boolean = database.isLogin(User(username,password))
            if(username.equals("Admin")&&password.equals("admin123")){
                intent = Intent(this, MainActivity4::class.java)
                startActivity(intent)
            }else{
            if(flag){
                if(loginCheckbox.isChecked){
                    val editor :SharedPreferences.Editor = sharedPreferences.edit()
                    editor.putString("Username",username)
                    editor.putString("Password",password)
                    editor.putBoolean("Checked",true)
                }else{
                    val editor :SharedPreferences.Editor = sharedPreferences.edit()
                    editor.remove("Username")
                    editor.remove("Password")
                    editor.remove("Checked")
                    editor.commit()
                }
                Toast.makeText(this, "Đăng nhập thành công!",Toast.LENGTH_SHORT).show()
                intent = Intent(this,MainActivity3::class.java)
                startActivity(intent)

            }else{
                Toast.makeText(this,"Sai tên đăng nhập hoặc mật khẩu!",Toast.LENGTH_SHORT).show()
            }
            }
        }


    }
}

