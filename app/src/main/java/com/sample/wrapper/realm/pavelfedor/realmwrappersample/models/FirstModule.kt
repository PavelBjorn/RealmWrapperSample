package com.sample.wrapper.realm.pavelfedor.realmwrappersample.models

import io.realm.annotations.RealmModule

@RealmModule(classes = [User::class, NewDataObject::class], library = true)
open class FirstModule
