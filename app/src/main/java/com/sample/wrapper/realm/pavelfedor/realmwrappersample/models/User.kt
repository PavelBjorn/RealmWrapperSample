package com.sample.wrapper.realm.pavelfedor.realmwrappersample.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class User(
        @PrimaryKey open var id: String = "",
        open var name: String = "",
        open var lastName: String = "",
        open var address: String = "",
        open var someData: Int = 0
) : RealmObject()
