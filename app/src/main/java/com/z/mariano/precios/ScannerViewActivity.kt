package com.z.mariano.precios
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log

import com.google.zxing.Result

import me.dm7.barcodescanner.zxing.ZXingScannerView
/**Created by Mariano on 22-ene-18.*/
class ScannerViewActivity : Activity(), ZXingScannerView.ResultHandler {


    private var mScannerView: ZXingScannerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mScannerView = ZXingScannerView(this)   // Programmatically initialize the scanner view
        setContentView(mScannerView)
        mScannerView!!.setResultHandler(this) // Register ourselves as a handler for scan results.
        mScannerView!!.startCamera()         // Start camera
    }

    override fun onPause() {
        super.onPause()
        try {
            mScannerView!!.stopCamera() // Stop camera on pause
        } catch (e: Exception) {
            Log.e("Error", e.message)
        }
        blankResult()
    }
    private fun blankResult(){
        val resultintent = Intent()
        resultintent.putExtra("BarCode", "")
        setResult(2, resultintent)
        finish()
    }

    override fun onBackPressed() {
        try {
            mScannerView!!.stopCamera() // Stop camera on pause
        } catch (e: Exception) {
            Log.e("Error", e.message)
        }
        blankResult()
    }

    override fun handleResult(rawResult: Result) {
        Log.e("handler", rawResult.text) // Prints scan results
        Log.e("handler", rawResult.barcodeFormat.toString()) // Prints the scan format (qrcode)
        try {
            mScannerView!!.stopCamera()

            val resultintent = Intent()
            resultintent.putExtra("BarCode", rawResult.text)
            setResult(2, resultintent)
            finish()

            println("************** Stop Camera**********")
            // Stop camera on pause
        } catch (e: Exception) {
            Log.e("Error", e.message)
        }

    }
}