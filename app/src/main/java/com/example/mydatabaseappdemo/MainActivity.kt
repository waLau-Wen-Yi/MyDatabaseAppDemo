package com.example.mydatabaseappdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mydatabaseappdemo.database.MyDatabase
import com.example.mydatabaseappdemo.databinding.ActivityMainBinding
import com.example.mydatabaseappdemo.entity.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dao = MyDatabase.getInstance(applicationContext).daoProduct
        binding.btnInsert.setOnClickListener {
            val newProd = Product(0,"iPhone",3500.00)
            CoroutineScope(IO).launch {
                dao.insertProduct(newProd)
            }
        }
        binding.btnGet.setOnClickListener {
            var prodName = ""
            CoroutineScope(IO).launch {
                val productList = dao.getAllProduct()
                for (prod in productList)
                    prodName += prod.name + ", "
            }
            CoroutineScope(Main).launch {
                binding.textView.text = prodName
            }
        }
    }
}