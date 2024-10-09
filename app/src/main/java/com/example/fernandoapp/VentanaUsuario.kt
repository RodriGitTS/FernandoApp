package com.example.fernandoapp

import Adaptadores.AdaptadorRecyclerView
import Modelo.Usuario
import UsuarioViewModel
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.InvalidationTracker
import com.example.fernandoapp.databinding.ActivityVentanaRegistroBinding
import com.example.fernandoapp.databinding.ActivityVentanaUsuarioBinding


class VentanaUsuario : AppCompatActivity() {

    lateinit var miRecyclerView : RecyclerView
    lateinit var binding: ActivityVentanaUsuarioBinding
    private lateinit var miAdapter: AdaptadorRecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityVentanaUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }
        var userViewModel = ViewModelProvider(this).get(UsuarioViewModel::class.java)

        var usuarios=userViewModel.obtenerAllUsuarios()

        miRecyclerView=binding.rv
        miRecyclerView.setHasFixedSize(true)
        miRecyclerView.layoutManager=LinearLayoutManager(this)

        miAdapter= AdaptadorRecyclerView(emptyList(),this)
        miRecyclerView.adapter=miAdapter

        userViewModel.obtenerAllUsuarios()

        userViewModel.usuarios.observe(this,Observer{usuarios->

            Log.e("Rodri",usuarios.toString())
            if (usuarios!=null){
                miAdapter.updateData(usuarios)
            }
        })

        userViewModel.estadoConsulta.observe(this, Observer{mensaje ->

            if (mensaje!=null){
                Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show()
                userViewModel.limpiarEstadoInsercion()
            }
        })
    }

}