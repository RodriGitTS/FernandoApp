package com.example.fernandoapp

import Modelo.Usuario
import Modelo.UsuarioDAO
import androidx.room.*


@Database(entities = [Usuario::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usuarioDAO(): UsuarioDAO
}

