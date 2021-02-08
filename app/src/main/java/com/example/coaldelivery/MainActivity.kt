package com.example.coaldelivery

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.InputFilter
import android.text.Spanned
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val list_of_providers = arrayOf("Разрез Аршановский", "Разрез Белоярский", "Разрез Черногорский", "Разрез Восточнобейский", "Разрез Изыхский")
    val list_of_coal_arsh = arrayOf("ДМСШ 0-25", "ДО 25-50")
    val list_of_coal_bel = arrayOf("ДМСШ 0-25")
    val list_of_coal_chern = arrayOf("ДМСШ 0-25", "ДР 0-300")
    val list_of_coal_vost = arrayOf("ДМСШ 0-25")
    val list_of_coal_iz = arrayOf("ДМСШ 0-25", "ДПК 50-200")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        coal_amount.setFilters(arrayOf<InputFilter>(InputFilterMinMax(0, 40)))

        val providers_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list_of_providers)
        providers_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_provider!!.setAdapter(providers_adapter)

        spinner_provider?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var selected_provider = spinner_provider.selectedItem.toString()
                if (selected_provider == "Разрез Аршановский") {
                    val coal_adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, list_of_coal_arsh)
                    coal_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner_coal!!.setAdapter(coal_adapter)
                }
                if (selected_provider == "Разрез Белоярский") {
                    val coal_adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, list_of_coal_bel)
                    coal_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner_coal!!.setAdapter(coal_adapter)
                }
                if (selected_provider == "Разрез Черногорский") {
                    val coal_adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, list_of_coal_chern)
                    coal_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner_coal!!.setAdapter(coal_adapter)
                }
                if (selected_provider == "Разрез Восточнобейский") {
                    val coal_adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, list_of_coal_vost)
                    coal_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner_coal!!.setAdapter(coal_adapter)
                }
                if (selected_provider == "Разрез Изыхский") {
                    val coal_adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, list_of_coal_iz)
                    coal_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner_coal!!.setAdapter(coal_adapter)
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        spinner_coal?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var selected_coal = spinner_coal.selectedItem.toString()
                if (selected_coal == "ДМСШ 0-25") {
                    chosen_coal_price.text = "1500 руб."
                }
                if (selected_coal == "ДО 25-50") {
                    chosen_coal_price.text = "1700 руб."
                }
                if (selected_coal == "ДПК 50-200") {
                    chosen_coal_price.text = "1900 руб."
                }
                if (selected_coal == "ДР 0-300") {
                    chosen_coal_price.text = "1200 руб."
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        button_map.setOnClickListener {
            val goToNextScreen = Intent(this, MapActivity::class.java)
            startActivity(goToNextScreen)
            Log.i("MainActivity", "button_map was clicked")
        }

        button_order.setOnClickListener {
            val goToNextScreen = Intent(this, SecondActivity::class.java)
            startActivity(goToNextScreen)
            //var selected = spinner_coal.selectedItem.toString()
            //Toast.makeText(this, selected, Toast.LENGTH_SHORT).show()
        }
    } //end of onCreate function
} //end of MainActivity class

class InputFilterMinMax(min:Int, max:Int): InputFilter {
    private var min:Int = 0
    private var max:Int = 0

    init{
        this.min = min
        this.max = max
    }

    override fun filter(source:CharSequence, start:Int, end:Int, dest: Spanned, dstart:Int, dend:Int): CharSequence? {
        try
        {
            val input = (dest.subSequence(0, dstart).toString() + source + dest.subSequence(dend, dest.length)).toInt()
            if (isInRange(min, max, input))
                return null
        }
        catch (nfe:NumberFormatException) {}
        return ""
    }

    private fun isInRange(a:Int, b:Int, c:Int):Boolean {
        return if (b > a) c in a..b else c in b..a
    }
}


