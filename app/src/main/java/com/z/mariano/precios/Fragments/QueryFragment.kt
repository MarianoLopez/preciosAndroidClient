package com.z.mariano.precios.Fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.github.kittinunf.fuel.Fuel
import com.z.mariano.precios.Model.DataClasses
import com.z.mariano.precios.R
import com.z.mariano.precios.RecyclerView.ProductAdapter
import com.z.mariano.precios.ScannerViewActivity
import java.text.SimpleDateFormat
import java.util.*


/*Created by Mariano on 20-ene-18.*/
class QueryFragment: Fragment() {
    private var recycler:RecyclerView?=null
    private var list:MutableList<DataClasses.Product> = mutableListOf()
    private var myAdapter:ProductAdapter?=null
    private var name:EditText?=null
    private var progresBar: ProgressBar?=null


    private val mapper = jacksonObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false)
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false)
            .configure(SerializationFeature.INDENT_OUTPUT,true)
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false)
            .setDateFormat(SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()))
            .setTimeZone(TimeZone.getTimeZone("America/Argentina/Buenos_Aires"))

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater?.inflate(R.layout.activity_query, container, false)
        setup(view)
        return view
    }

    private fun setup(view: View?){
        name=view?.findViewById(R.id.consulta)
        progresBar=view?.findViewById(R.id.progressbar)
        progresBar?.visibility = View.GONE
        view?.findViewById<Button>(R.id.queryBtn)?.setOnClickListener({query()})
        view?.findViewById<Button>(R.id.scanBtn)?.setOnClickListener({bar()})
        recycler =view?.findViewById(R.id.recycler)
        myAdapter = ProductAdapter(list)
        recycler?.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
        recycler?.itemAnimator = DefaultItemAnimator()
        recycler?.adapter = myAdapter


    }

    private fun query(codeBar:Boolean=false){
        progresBar?.visibility = View.VISIBLE
        val url = if(codeBar) "http://192.168.0.22:8080/${name?.text?.toString()}" else "http://192.168.0.22:8080/byName/${name?.text?.toString()}"
        Fuel.get(url)
                .header(Pair("Content-Type", "application/json; charset=utf-8"))
                .timeout(5000)
                .responseString { _, response, _ ->
                    val result:List<DataClasses.Product> = mapper.readValue(response.data)
                    list.clear()
                    list.addAll(result)
                    myAdapter?.notifyDataSetChanged()
                    progresBar?.visibility = View.GONE
                }
        }

    private fun bar(){
        println("asdasd")
        val intent = Intent(activity, ScannerViewActivity::class.java)
        startActivityForResult(intent, 2)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        val barcode = data.extras.getString("BarCode")
        if (barcode == "") {
            Toast.makeText(activity,"No se encontró código de barra",Toast.LENGTH_LONG).show()
        } else {
            name?.setText(barcode)
            query(true)
        }
    }


}