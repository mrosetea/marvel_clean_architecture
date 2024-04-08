package com.example.marvelcleanarchitectureapp.utils
import android.util.Log
import java.security.MessageDigest

class Keys {
    companion object {
        const val PRIVATE_KEY = "4414869dd08d339aa79ae147aa0ee3516026686b"
        const val PUBLIC_KEY = "d4fadba74bffb12c119624caed0235e4"
        fun generateTimestamp(): String {
            return (System.currentTimeMillis() / 1000).toString()
        }
        fun generateHash(timestamp: String): String {
            val md = MessageDigest.getInstance("MD5")
            val value = "${timestamp}${PRIVATE_KEY}${PUBLIC_KEY}"
            val digest = md.digest(value.toByteArray())
            return digest.joinToString("") {
                String.format("%02x", it)
            }
        }
    }

}