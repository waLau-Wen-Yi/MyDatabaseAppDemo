package com.example.mydatabaseappdemo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mydatabaseappdemo.dao.DaoProduct
import com.example.mydatabaseappdemo.entity.Product

@Database (entities = [Product::class], version = 1)
abstract class MyDatabase: RoomDatabase() {
    abstract val daoProduct: DaoProduct

    companion object{
        @Volatile
        private var INSTANCE: MyDatabase? = null
        fun getInstance(context: Context): MyDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MyDatabase::class.java,
                        "MyDatabase"
                    )
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}