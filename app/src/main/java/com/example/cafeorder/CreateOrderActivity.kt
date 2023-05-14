package com.example.cafeorder

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CreateOrderActivity : AppCompatActivity() {
    private lateinit var textViewWelcome: TextView
    private lateinit var textViewAdditions:TextView
    private lateinit var spiner_tea: Spinner
    private lateinit var spiner_coffee: Spinner
    private lateinit var checkBox_milk:CheckBox
    private lateinit var checkBox_sugar:CheckBox
    private lateinit var checkBox_lemon:CheckBox
    private lateinit var bulderAdditions: StringBuilder

    private lateinit var drink: String

    private lateinit var name: String
    private lateinit var password: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_order)

        drink = getString(R.string.tea)
        val intent = intent
        if(intent.hasExtra("name") && intent.hasExtra("password")) {
            name = intent.getStringExtra("name").toString()
            password = intent.getStringExtra("password").toString()
        } else {
            name = getString(R.string.defolt_name)
            password = getString(R.string.defolt_password)
        }


        textViewWelcome = findViewById(R.id.TextViewWelcome)
        textViewAdditions = findViewById(R.id.TextViewAdditions)
        spiner_tea = findViewById(R.id.spinnerTea)
        spiner_coffee = findViewById(R.id.spinnerCoffee)
        checkBox_milk = findViewById(R.id.checkboxMilk)
        checkBox_sugar =findViewById(R.id.checkboxSugar)
        checkBox_lemon = findViewById(R.id.checkboxLemon)
        bulderAdditions = StringBuilder()


        val hello = String.format(getString(R.string.helloUser), name)
        textViewWelcome.text = hello

        val additions = String.format(getString(R.string.edition), drink)
        textViewAdditions.text = additions


    }

    fun onClickChangeDrink(view: View) {
        val button = view as RadioButton
        val id = button.id
        if (id == R.id.radioButtonTea) {
            drink = getString(R.string.tea)
            spiner_tea.visibility = View.VISIBLE
            spiner_coffee.visibility = View.INVISIBLE
            checkBox_lemon.visibility = View.VISIBLE
        } else if (id == R.id.radioButtonCoffee) {
            drink = getString(R.string.coffe)
            spiner_tea.visibility = View.INVISIBLE
            spiner_coffee.visibility = View.VISIBLE
            checkBox_lemon.visibility = View.INVISIBLE
        }
        view.invalidate()

        val additions = String.format(getString(R.string.edition), drink)
        textViewAdditions.text = additions
    }


    fun onClickSendOrder(view: View) {
        bulderAdditions.setLength(0)
        if (checkBox_milk.isChecked){
            bulderAdditions.append(getString(R.string.milk)).append(" ")
        }
        if (checkBox_lemon.isChecked && drink == getString(R.string.tea)){
            bulderAdditions.append(getString(R.string.lemon)).append(" ")
        }
        if (checkBox_sugar.isChecked){
            bulderAdditions.append(getString(R.string.sugar)).append(" ")
        }
        var optionOfDrink = ""
        if(drink == getString(R.string.tea)){
            optionOfDrink = spiner_tea.selectedItem.toString()
        }else {
            optionOfDrink = spiner_coffee.selectedItem.toString()
        }
        val order = String.format(getString(R.string.order), name, password, drink, optionOfDrink)
        var additions = ""
        if (bulderAdditions.isNotEmpty()) {
            additions = "\n" + getString(R.string.need_additoins) + bulderAdditions.toString()
        }else {
            additions = ""
        }
        val fullOrder = order + additions
        val intent = Intent(this, OrderDelailActivity::class.java)
        intent.putExtra("order", fullOrder)
        startActivity(intent)
    }
}