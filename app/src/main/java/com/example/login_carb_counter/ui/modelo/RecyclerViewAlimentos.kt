package com.example.login_carb_counter.ui.modelo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.login_carb_counter.R


class RecyclerViewAlimentos():  RecyclerView.Adapter<RecyclerViewAlimentos.ViewHolder>() {
       private lateinit var mContext: Context
       private var nombreAlimento: ArrayList<String> = ArrayList()
       private var cantidadAlimento: ArrayList<String> = ArrayList()


    constructor(
            mContext: Context,
            nombreAlimento: ArrayList<String>,
            cantidadAlimento: ArrayList<String>

    ):this() {
          this.mContext = mContext
          this.nombreAlimento = nombreAlimento
          this.cantidadAlimento = cantidadAlimento
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var name: EditText = itemView.findViewById(R.id.textViewAlimento)
        var porcion: EditText = itemView.findViewById(R.id.textViewCantidad)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var view:View = LayoutInflater.from(parent.context).inflate(R.layout.layout_alimentos_completos, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {

        return nombreAlimento.size
    }

    override fun onBindViewHolder(holder: RecyclerViewAlimentos.ViewHolder, position: Int) {



        println(cantidadAlimento[position])
        holder.name.setText(nombreAlimento[position])
        holder.porcion.setText(cantidadAlimento[position])



        }
}