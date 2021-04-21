package com.example.login_carb_counter.ui.modelo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.login_carb_counter.R

class RecyclerViewInfoCompleta():RecyclerView.Adapter<RecyclerViewInfoCompleta.ViewHolder>()  {

    private lateinit var mContext: Context
    private var informacionAlimento: ArrayList<String> = ArrayList()
    private var imagenesAlimento: ArrayList<String> = ArrayList()
    private var posicionImagen: Int = 0


    constructor(
            mContext: Context,
            informacionAlimento: ArrayList<String>,
            imagenesAlimento: ArrayList<String>,
            posicionImagen: Int

    ):this() {
        this.mContext = mContext
        this.informacionAlimento = informacionAlimento
        this.imagenesAlimento = imagenesAlimento
        this.posicionImagen = posicionImagen

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var name: TextView = itemView.findViewById(R.id.textViewNombreAlimento)
        var gramos: TextView = itemView.findViewById(R.id.textViewGramosAlimento)
        var carbohidratos: TextView = itemView.findViewById(R.id.textViewCarbohidratos)
        var calorias: TextView = itemView.findViewById(R.id.textViewCalorias)
        var imagen: ImageView = itemView.findViewById(R.id.imageView)




    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var view: View = LayoutInflater.from(parent.context).inflate(R.layout.layout_alimentos_finales, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {

        return 1
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
/*
        for(i in (0..informacionAlimento.size-1))
        {
            println(informacionAlimento[i])
        }
*/
        holder.name.setText(informacionAlimento[4])
        holder.gramos.setText("Cada " + informacionAlimento[8] + " g de " + informacionAlimento[4] + " aportan:")
        holder.carbohidratos.setText("• Carbohidratos: " + informacionAlimento[12])
        holder.calorias.setText("• Calorías: " + informacionAlimento[10])

        Glide.with(mContext)
                .asBitmap()
                .load(imagenesAlimento[posicionImagen])
                .into(holder.imagen)



    }
}