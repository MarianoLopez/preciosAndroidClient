package com.z.mariano.precios.Util

import android.app.Activity
import android.content.Context

/**
 * Created by Mariano on 22-ene-18.
 */

    fun Activity.getStringFromSharedPreferences(vararg pair: Pair<String,String>): List<Pair<String, String?>> {
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
        var result = mutableListOf<Pair<String,String?>>()
        pair.forEach { result.add(Pair(it.first,sharedPref?.getString(it.first,it.second))) }
        return result
    }
    fun Activity.setStringsInSharedPreferences(vararg pair: Pair<String,String>){
            val sharedPref = this.getPreferences(Context.MODE_PRIVATE)?.edit()
            pair.forEach { if(it.second.isNotEmpty()){
                println("put $it")
                sharedPref?.putString(it.first,it.second)
            }else{
                println("empty")
            }
            }
            sharedPref?.commit()
    }
