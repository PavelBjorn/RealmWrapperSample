package com.sample.wrapper.realm.pavelfedor.realmwrappersample.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class NewDataObject(
        @PrimaryKey open var id: String = "",
        open var data: String = "",
        open var data1: String = "",
        open var data2: String = "",
        open var data3: Float = 0.0f
) : RealmObject()
