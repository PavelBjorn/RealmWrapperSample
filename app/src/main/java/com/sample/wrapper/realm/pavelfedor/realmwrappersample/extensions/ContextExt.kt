package com.sample.wrapper.realm.pavelfedor.realmwrappersample.extensions

import android.content.Context
import android.content.Intent
import com.sample.wrapper.realm.pavelfedor.realmwrappersample.DataActivity

fun Context.showDataActivity(tableName: String, realmFile: String) {
    startActivity(Intent(this, DataActivity::class.java).apply {
        putExtra("tableName", tableName)
        putExtra("realmFile", realmFile)
    })
}


fun Intent.getTableName() = getStringExtra("tableName") ?: ""

fun Intent.getRealmFileName() = getStringExtra("realmFile") ?: ""
