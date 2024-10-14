package com.example.fernandoapp

import UsuarioViewModel
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.fernandoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var database: AppDatabase



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var usuarioVIEWmodel = ViewModelProvider(this)[UsuarioViewModel::class.java]

        binding.btnLogin.setOnClickListener {
            var nombre = binding.lblUsuario.text.toString()
            var pass = binding.lblPassword.text.toString()

            usuarioVIEWmodel.obtenerUsuarioPorNombre(nombre).observe(this){ usuario->

            if(usuario!!.nombre==nombre && usuario.pass==pass){
                Toast.makeText(this,"Datos correctos",Toast.LENGTH_SHORT).show()
                var intent = Intent(this@MainActivity, VentanaUsuario::class.java)
                startActivity(intent)
            }else Toast.makeText(this,"Datos incorrectos",Toast.LENGTH_SHORT).show()
            }

        }
        binding.btnLogin2.setOnClickListener {
            var intent = Intent(this@MainActivity, VentanaRegistro::class.java)
            startActivity(intent)
        }
        binding.swModoOscuro.setOnClickListener {

            if (!binding.swModoOscuro.isChecked){
                Toast.makeText(this,"Modo oscuro activado",Toast.LENGTH_SHORT).show()
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
                delegate.applyDayNight()
            }else {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
                delegate.applyDayNight()
            }
        }

    }
}