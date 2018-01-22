package com.z.mariano.precios.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.z.mariano.precios.R

/**
 * Created by Mariano on 20-ene-18.
 */
class ConfigFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.activity_config, container, false);
    }
}