package com.mmisoft.animalsretrofit.util

import java.net.InetAddress
import java.net.UnknownHostException


object InternetHelper {

    fun isInternetAvailable(): Boolean {
        try {
            val address: InetAddress = InetAddress.getByName("www.google.com")
            return !address.equals("")
        } catch (e: UnknownHostException) {

        }
        return false
    }
}