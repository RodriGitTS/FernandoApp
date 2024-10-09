package com.example.fernandoapp

import Modelo.Usuario
import Modelo.UsuarioDAO
import Parametros.Parametros
import android.content.Context
import androidx.room.*


@Database(entities = [Usuario::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usuarioDAO(): UsuarioDAO
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    Parametros.nombreBD
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

