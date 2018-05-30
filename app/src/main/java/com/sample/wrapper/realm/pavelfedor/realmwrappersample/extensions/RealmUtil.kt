package com.sample.wrapper.realm.pavelfedor.realmwrappersample.extensions

import io.realm.RealmConfiguration
import io.realm.internal.OsSharedRealm

object RealmUtil {

    fun getSharedInstance(name: String): OsSharedRealm = OsSharedRealm.getInstance(RealmConfiguration.Builder()
                    .name(name)
                    .build())
}
