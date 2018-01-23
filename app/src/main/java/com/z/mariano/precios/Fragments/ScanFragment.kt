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
import com.fasterxml.jackson.module.kotlin.readValue
import com.github.kittinunf.fuel.Fuel
import com.z.mariano.precios.Model.DataClasses
import com.z.mariano.precios.R
import com.z.mariano.precios.RecyclerView.ProductAdapter
import com.z.mariano.precios.ScannerViewActivity
import com.z.mariano.precios.Util.Jackson
import com.z.mariano.precios.Util.getStringFromSharedPreferences

/*Created by Mariano on 20-ene-18.*/
class ScanFragment() : Fragment() {
    private var recycler: RecyclerView?=null
    private var list:MutableList<DataClasses.Product> = mutableListOf()
    private var myAdapter: ProductAdapter?=null
    private var code: TextView?=null
    private var progresBar: ProgressBar?=null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater?.inflate(R.layout.activity_scan, container, false)
        setup(view)
        return view
    }

    private fun setup(view: View?){
        code=view?.findViewById(R.id.code)
        progresBar=view?.findViewById<ProgressBar>(R.id.progressbar)?.apply { visibility = View.GONE }
        view?.findViewById<Button>(R.id.scanBtn)?.setOnClickListener({bar()})
        myAdapter = ProductAdapter(list)
        recycler =view?.findViewById<RecyclerView>(R.id.recycler)?.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
            adapter = myAdapter
        }
    }

    private fun query(){
        progresBar?.visibility = View.VISIBLE
        val ip = activity.getStringFromSharedPreferences(Pair(getString(R.string.api_server_ip),getString(R.string.default_api_server_ip)))
        println("IP: $ip")
        Fuel.get("http://${ip.first().second}:8080/${code?.text?.toString()}")
                .header(Pair("Content-Type", "application/json; charset=utf-8"))
                .timeout(5000)
                .responseString { _, _,result ->
                    val (data, error) = result
                    if (error == null && data!=null) {
                        list.clear()
                        list.addAll(Jackson.mapper.readValue(data))
                        myAdapter?.notifyDataSetChanged()
                    } else {
                        code?.text = error?.toString()
                    }
                    progresBar?.visibility = View.GONE
                }
    }

    private fun bar() = startActivityForResult(Intent(activity, ScannerViewActivity::class.java), 2)

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        val barcode = data.extras.getString("BarCode")
        if (barcode == "") {
            Toast.makeText(activity,"No se encontró código de barra", Toast.LENGTH_LONG).show()
        } else {
            code?.text = barcode
            query()
        }
    }


}