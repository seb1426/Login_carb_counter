package com.example.login_carb_counter.ui.modelo

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.login_carb_counter.R
import com.example.login_carb_counter.ui.login.ConteoCarbohidratos
import de.hdodenhof.circleimageview.CircleImageView


class RecyclerViewAdapter(): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var mNames: ArrayList<String> = ArrayList()
    private var mImageUrls: ArrayList<String> = ArrayList()
    private lateinit var mContext:Context
    private var arregloRecyclerView:ArrayList<RecyclerView>? = ArrayList()
    private var arregloAlimento: ArrayList<String> = ArrayList()
    private var arregloPorcion: ArrayList<String> = ArrayList()
    private lateinit var recyclerViewResumen: RecyclerView
    private lateinit var botonPorcion: Button
    private lateinit var spinnerPorcion: Spinner
    private var llamarCarbohidratos: claseAuxiliarConteo = claseAuxiliarConteo()


    constructor(
            mContext: Context,
            mNames: ArrayList<String>,
            mImageUrls: ArrayList<String>,
            arregloRecyclerView: ArrayList<RecyclerView>?,
            recyclerViewResumen: RecyclerView,
            botonPorcion: Button,
            spinnerPorcion: Spinner



    ):this() {
        this.mNames = mNames
        this.mImageUrls = mImageUrls
        this.mContext = mContext
        this.arregloRecyclerView = arregloRecyclerView
        this.recyclerViewResumen = recyclerViewResumen
        this.botonPorcion = botonPorcion
        this.spinnerPorcion = spinnerPorcion


    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

         var image: CircleImageView = itemView.findViewById(R.id.image_view)
         var name: TextView = itemView.findViewById(R.id.name)

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view:View = LayoutInflater.from(parent.context).inflate(R.layout.layout_horizontal_alimentos, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return mImageUrls.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var almidon = arregloRecyclerView?.get(0)
        var frutas = arregloRecyclerView?.get(1)
        var granos = arregloRecyclerView?.get(2)
        var lacteos = arregloRecyclerView?.get(3)
        var vegetales = arregloRecyclerView?.get(4)



        Glide.with(mContext)
            .asBitmap()
            .load(mImageUrls[position])
            .into(holder.image)

        holder.name.text = mNames[position]

        holder.image.setOnClickListener {
            Log.d(TAG, "onClick: clicked on an image: " + mNames[position])

            if(almidon!=null) {

                //println("EL ARREGLO AL CAMBIAR DE CATEGORIA " + arregloAlimento)

                if (mNames[position] == "ALMIDONES") {


                    if(almidon?.visibility == View.INVISIBLE) {
                        println("MUESTRA ALMIDONES")
                        almidon?.visibility = View.VISIBLE
                        frutas?.visibility = View.INVISIBLE
                        granos?.visibility = View.INVISIBLE
                        lacteos?.visibility = View.INVISIBLE
                        vegetales?.visibility = View.INVISIBLE
                        //recyclerAlmidon!!.visibility = View.VISIBLE
                    }
                    else {
                        almidon?.visibility = View.INVISIBLE
                    }
                    /*
                    recyclerFrutas!!.visibility == View.INVISIBLE
                    recyclerJugos!!.visibility == View.INVISIBLE
                    recyclerLacteos!!.visibility == View.INVISIBLE
                    recyclerVegetales!!.visibility == View.INVISIBLE

                     */

                }
               else if (mNames[position] == "FRUTAS" ) {

                    if(frutas?.visibility == View.INVISIBLE) {
                        println("MUESTRA FRUTAS")
                        frutas?.visibility = View.VISIBLE
                        almidon?.visibility = View.INVISIBLE
                        granos?.visibility = View.INVISIBLE
                        lacteos?.visibility = View.INVISIBLE
                        vegetales?.visibility = View.INVISIBLE

                    }else {
                        frutas?.visibility = View.INVISIBLE
                    }
                    /*
                    recyclerAlmidon!!.visibility = View.INVISIBLE
                    recyclerJugos!!.visibility == View.INVISIBLE
                    recyclerLacteos!!.visibility == View.INVISIBLE
                    recyclerVegetales!!.visibility == View.INVISIBLE
                    */
                }
               else  if (mNames[position] == "GRANOS") {

                    if( granos?.visibility == View.INVISIBLE){
                        println("MUESTRA GRANOS")
                        granos?.visibility = View.VISIBLE
                        frutas?.visibility = View.INVISIBLE
                        almidon?.visibility = View.INVISIBLE
                        lacteos?.visibility = View.INVISIBLE
                        vegetales?.visibility = View.INVISIBLE

                    }
                    else {
                        granos?.visibility = View.INVISIBLE
                    }
                    /*
                    recyclerFrutas!!.visibility = View.INVISIBLE
                    recyclerAlmidon!!.visibility = View.INVISIBLE
                    recyclerLacteos!!.visibility == View.INVISIBLE
                    recyclerVegetales!!.visibility == View.INVISIBLE
                    */
                }
                else if (mNames[position] == "LÁCTEOS") {

                    if(lacteos?.visibility == View.INVISIBLE)
                    {
                        println("MUESTRA LACTEOS")
                        lacteos?.visibility = View.VISIBLE
                        granos?.visibility = View.INVISIBLE
                        frutas?.visibility = View.INVISIBLE
                        almidon?.visibility = View.INVISIBLE
                        vegetales?.visibility = View.INVISIBLE
                    }
                    else {
                        lacteos?.visibility = View.INVISIBLE
                    }
                    /*
                    recyclerJugos!!.visibility = View.INVISIBLE
                    recyclerFrutas!!.visibility = View.INVISIBLE
                    recyclerAlmidon!!.visibility = View.INVISIBLE
                    recyclerVegetales!!.visibility == View.INVISIBLE
                    */

                }
                else if (mNames[position] == "VEGETALES") {

                    if(vegetales?.visibility == View.INVISIBLE)
                    {
                        println("MUESTRA VEGETALES")
                        vegetales?.visibility = View.VISIBLE
                        lacteos?.visibility = View.INVISIBLE
                        granos?.visibility = View.INVISIBLE
                        frutas?.visibility = View.INVISIBLE
                        almidon?.visibility = View.INVISIBLE

                    }
                    else {
                        vegetales?.visibility = View.INVISIBLE
                    }
                    /*
                    recyclerLacteos!!.visibility = View.INVISIBLE
                    recyclerJugos!!.visibility = View.INVISIBLE
                    recyclerFrutas!!.visibility = View.INVISIBLE
                    recyclerAlmidon!!.visibility = View.INVISIBLE
                    */

                }
            }
            else if(mNames[position] in arregloAlimento) {

                Toast.makeText(mContext, "YA COLOCASTE ESTE ALIMENTO: " + mNames[position], Toast.LENGTH_SHORT).show()
            }
            else{


/*
                 arregloAlimento = llamarCarbohidratos.getArregloAlimento()
                 arregloPorcion = llamarCarbohidratos.getArregloPorcion()

                println("TRAE EL ARREGLO AL RECYCLER" + arregloAlimento)
                println("TRAE EL ARREGLO AL RECYCLER" + arregloPorcion)
*/

                Toast.makeText(mContext, mNames[position], Toast.LENGTH_SHORT).show()
                var porcionAlimento: String

                spinnerPorcion.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }

                    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                        porcionAlimento = parent.getItemAtPosition(position).toString()

                        arregloPorcion.add(porcionAlimento)

                    }

                }


                println(arregloAlimento)
                botonPorcion.setOnClickListener {
                    arregloAlimento.add(mNames[position])


                    val layoutManager = LinearLayoutManager(mContext)
                    recyclerViewResumen?.layoutManager = layoutManager
                    println("PASA EL LAYOUT MANAGER DEL RESUMEN")
                    val adapter = RecyclerViewAlimentos(mContext,  arregloAlimento, arregloPorcion)
                    recyclerViewResumen?.adapter = adapter
                    //llamarCarbohidratos.asignarAlimentos(arregloAlimento, arregloPorcion)

                }





            }
           // Toast.makeText(mContext, mNames[position], Toast.LENGTH_SHORT).show()
        }
    }
}
