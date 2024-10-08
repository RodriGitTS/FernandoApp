package Modelo

import androidx.room.*

@Dao
interface UsuarioDAO{


    @Insert
    suspend fun insertUsuario(usuario: Usuario)


    @Query("SELECT * FROM usuarios")
    suspend fun getAllUsuarios(): List<Usuario>

    @Query("SELECT * FROM usuarios WHERE codUsuario = :cod")
    suspend fun getUsuarioByCod(cod: Int): Usuario?


    @Update
    suspend fun updateUsuario(usuario: Usuario)


    @Delete
    suspend fun deleteUsuario(usuario: Usuario)
}
