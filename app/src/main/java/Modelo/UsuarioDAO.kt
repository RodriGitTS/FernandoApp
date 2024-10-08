package Modelo

import androidx.room.*

@Dao
interface UsuarioDAO{


    @Insert
    suspend fun insertUsuario(usuario: Usuario)


    @Query("SELECT * FROM usuarios")
    suspend fun getAllUsuarios(): List<Usuario>

    @Query("SELECT * FROM usuarios WHERE nombre= :nombre")
    suspend fun getUsuarioByCod(nombre: String): Usuario?


    @Update
    suspend fun updateUsuario(usuario: Usuario)


    @Delete
    suspend fun deleteUsuario(usuario: Usuario)
}
