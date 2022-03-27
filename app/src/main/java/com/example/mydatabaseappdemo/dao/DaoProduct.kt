package com.example.mydatabaseappdemo.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mydatabaseappdemo.entity.Product

@Dao
interface DaoProduct {
    @Insert
    fun insertProduct(prod: Product)
    @Query ("SELECT * FROM tb_product")
    fun getAllProduct(): List<Product>
    @Query ("SELECT * FROM tb_product WHERE price <= :price")
    fun getProdByPrice(price: Double): List<Product>
}