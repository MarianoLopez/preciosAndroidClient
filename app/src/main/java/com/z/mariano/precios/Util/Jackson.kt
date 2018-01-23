package com.z.mariano.precios.Util

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Mariano on 22-ene-18.
 */
object Jackson {
    val mapper = jacksonObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false)
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false)
            .configure(SerializationFeature.INDENT_OUTPUT,true)
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false)
            .setDateFormat(SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()))
            .setTimeZone(TimeZone.getTimeZone("America/Argentina/Buenos_Aires"))
}