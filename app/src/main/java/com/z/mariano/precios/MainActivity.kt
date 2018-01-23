package com.z.mariano.precios


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import com.z.mariano.precios.Fragments.ConfigFragment
import com.z.mariano.precios.Fragments.QueryFragment
import com.z.mariano.precios.Fragments.ScanFragment

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        container.adapter = SectionsPagerAdapter(supportFragmentManager)// Create the adapter that will return a fragment for each
        tabs.setupWithViewPager(container) // Set up the ViewPager with the sections adapter.
    }

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        private val fragments = arrayListOf(Pair("Escanear",ScanFragment()),
                                            Pair("Por nombre",QueryFragment()),
                                            Pair("Configuración",ConfigFragment()))

        override fun getItem(position: Int) = fragments[position].second

        override fun getCount() = fragments.size

        override fun getPageTitle(position: Int) = fragments[position].first
    }
}
