package Adaptadores

import Modelo.Usuario
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fernandoapp.R

class AdaptadorRecyclerView(var usuarios:List<Usuario>, var context: Context): RecyclerView.Adapter<AdaptadorRecyclerView.ViewHolder>() {


    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val info=view.findViewById<TextView>(R.id.txtInfoUser)
        fun bind(usuario: Usuario, context: Context, pos: Int, adaptadorRecyclerView: AdaptadorRecyclerView){
            info.text=usuario.toString()
        }
    }

    @SuppressLint("ResourceAsColor")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista= LayoutInflater.from(parent.context).inflate(R.layout.card_view,parent,false)
        val viewHolder=ViewHolder(vista)
        return viewHolder
    }


    override fun getItemCount(): Int {
        return usuarios.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=usuarios.get(position)
        holder.bind(item,context,position,this)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(nuevaLista: List<Usuario>) {
        usuarios = nuevaLista
        notifyDataSetChanged()
    }


}