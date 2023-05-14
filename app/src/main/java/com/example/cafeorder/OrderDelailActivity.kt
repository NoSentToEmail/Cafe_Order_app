package com.example.cafeorder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class OrderDelailActivity : AppCompatActivity() {

    private lateinit var textViewOrder: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_delail)
        textViewOrder = findViewById(R.id.textViewOrder)
        val intent = intent
        if (intent.hasExtra("order")){
            val order = intent.getStringExtra("order")
            textViewOrder.text = order
        }else {
            val backToLogin = Intent(this, MainActivity::class.java)
            startActivity(backToLogin)
        }
    }
}