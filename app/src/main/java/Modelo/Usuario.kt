package Modelo

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity("usuarios")
data class Usuario (@PrimaryKey val nombre:String, val correo:String, val pass:String, val edad:Int):Serializable{

}