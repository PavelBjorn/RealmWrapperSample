package com.sample.wrapper.realm.pavelfedor.realmwrappersample.extensions

import android.content.Context
import android.support.design.widget.NavigationView
import java.io.File

fun NavigationView.fillItemsWithRealmDbs() {
    listRealmFiles(openDataFilesDir(context)).asSequence()
            .forEach { menu.add(it.name) }

    setCheckedItem(menu.getItem(0).itemId)
}


private fun listRealmFiles(file: File): List<File> {
    return mutableListOf<File>().apply {
        file.listFiles().asSequence().forEach {
            if (it.isDirectory) this.addAll(listRealmFiles(it))
            if (it.isFile && it.absolutePath.endsWith(".realm")) this.add(it)
        }
    }
}


private fun openDataFilesDir(context: Context) = File(context.packageManager.run {
    getPackageInfo(context.packageName, 0)
}.applicationInfo.dataDir)
