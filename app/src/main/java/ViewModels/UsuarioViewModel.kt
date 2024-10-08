import Modelo.Usuario
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fernandoapp.AppDatabase
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class UsuarioViewModel(aplication:Application):AndroidViewModel(aplication) {

    private val _usuarios = MutableLiveData<List<Usuario>>()
    val usuarios: LiveData<List<Usuario>> get() = _usuarios

    private val _estadoConsulta = MutableLiveData<String?>()
    val estadoConsulta: MutableLiveData<String?> get() = _estadoConsulta

    val usuarioDAO = AppDatabase.getDatabase(aplication).usuarioDAO()

    fun obtenerAllUsuarios() = viewModelScope.launch {
        val lista = usuarioDAO.getAllUsuarios()
        _usuarios.postValue(lista)
    }
    fun insertarUsuario(usuario: Usuario)= viewModelScope.launch {

        try {
            usuarioDAO.insertUsuario(usuario)
            _estadoConsulta.postValue("Usuario insertado correctamente")
        } catch (e: Exception) {

            Log.e("Rodri", "Error al insertar persona")
            _estadoConsulta.postValue("Error al insertar persona")
        }
    }
    fun obtenerUsuarioPorNombre(nombre: String): LiveData<Usuario?> {
        val usuarioLiveData = MutableLiveData<Usuario?>()
        viewModelScope.launch {

            val usuario = usuarioDAO.getUsuarioByCod(nombre)
            usuarioLiveData.postValue(usuario)

        }
        return usuarioLiveData
    }


    fun limpiarEstadoInsercion() {
        _estadoConsulta.value = null
    }
}




