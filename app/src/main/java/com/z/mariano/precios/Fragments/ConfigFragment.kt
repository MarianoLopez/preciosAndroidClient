package com.z.mariano.precios.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.z.mariano.precios.R
import com.z.mariano.precios.Util.getStringFromSharedPreferences
import com.z.mariano.precios.Util.setStringsInSharedPreferences

/**
 * Created by Mariano on 20-ene-18.
 */
class ConfigFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater?.inflate(R.layout.activity_config, container, false)
        setup(view)
        return view
    }

    private fun setup(view: View?){
        view?.findViewById<EditText>(R.id.ip)?.setText(activity.getStringFromSharedPreferences(Pair(getString(R.string.api_server_ip),getString(R.string.default_api_server_ip))).first().second)
        view?.findViewById<Button>(R.id.configBtn)?.setOnClickListener(
             {activity.setStringsInSharedPreferences(Pair(getString(R.string.api_server_ip),
                     view.findViewById<TextView>(R.id.ip)?.text?.toString()?:getString(R.string.default_api_server_ip)))
                 Toast.makeText(activity,"Guardado", Toast.LENGTH_LONG).show()
             })
    }


}