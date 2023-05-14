package com.example.cafeorder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT

class MainActivity : AppCompatActivity() {

    private lateinit var button_order: Button
    private lateinit var editTextName: EditText
    private lateinit var editTextPasword: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button_order = findViewById(R.id.buttonCreatOrder)
        editTextName = findViewById(R.id.editTextTextName)
        editTextPasword = findViewById(R.id.editTextTextPassword)


        button_order.setOnClickListener(){
            val name = editTextName.text.toString().trim()
            val password = editTextPasword.text.toString().trim()
            if (!name.isEmpty() && !password.isEmpty()) {
                val intent = Intent(this, CreateOrderActivity::class.java)
                intent.putExtra("name", name)
                intent.putExtra("password", password)
                startActivity(intent)
            }else {
                Toast.makeText(this, R.string.warning_fill, LENGTH_SHORT).show()
            }
        }
    }

}