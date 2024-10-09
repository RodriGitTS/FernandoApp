package com.example.fernandoapp

import Modelo.Usuario
import UsuarioViewModel
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.fernandoapp.databinding.ActivityMainBinding
import com.example.fernandoapp.databinding.ActivityVentanaRegistroBinding

class VentanaRegistro : AppCompatActivity() {

    lateinit var binding: ActivityVentanaRegistroBinding
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityVentanaRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var usuarioVIEWmodel = ViewModelProvider(this)[UsuarioViewModel::class.java]

        binding.btnVolver.setOnClickListener { finish() }

        binding.btnRegistrar.setOnClickListener{

            var nombre=binding.lblNombre.text.toString()
            var pass=binding.lblPaswword.text.toString()
            var pass2=binding.lblPaswword.text.toString()
            var emai=binding.lblEmail.text.toString()
            var edad=binding.lblEdad.text.toString().toInt()

            if(
                nombre.isNotEmpty()||
                pass.isNotEmpty()||
                pass==pass2||
                emai.isNotEmpty()||
                edad<110
            ){
                usuarioVIEWmodel.insertarUsuario(Usuario(nombre,pass, emai,edad))
                Toast.makeText(this,"Datos correctos",Toast.LENGTH_SHORT).show()

            }else Toast.makeText(this, "Datos incorrectos",Toast.LENGTH_SHORT).show()

        }
    }
}