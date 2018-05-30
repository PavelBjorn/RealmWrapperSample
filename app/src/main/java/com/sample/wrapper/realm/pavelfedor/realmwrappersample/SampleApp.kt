package com.sample.wrapper.realm.pavelfedor.realmwrappersample

import android.app.Application
import com.sample.wrapper.realm.pavelfedor.realmwrappersample.models.FirstModule
import com.sample.wrapper.realm.pavelfedor.realmwrappersample.models.SecondModule
import com.sample.wrapper.realm.pavelfedor.realmwrappersample.models.SecondUser
import com.sample.wrapper.realm.pavelfedor.realmwrappersample.models.User
import io.realm.Realm
import io.realm.RealmConfiguration

class SampleApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

        RealmConfiguration.Builder()
                .name("first_test.realm")
                .modules(FirstModule())
                .build().run {
                    Realm.getInstance(this).executeTransaction {
                        it.insertOrUpdate(
                                (1..100).map {
                                    User(
                                            id = "$it",
                                            name = "User name $it",
                                            lastName = "User last name $it",
                                            address = "User address $it",
                                            someData = it
                                    )
                                }
                        )
                    }
                }

        RealmConfiguration.Builder()
                .name("second_test.realm")
                .modules(SecondModule())
                .build().run {
                    Realm.getInstance(this).executeTransaction {
                        it.insertOrUpdate(
                                (1..100).map {
                                    SecondUser(
                                            id = "$it",
                                            name = "SecondUser name $it",
                                            lastName = "SecondUser last name $it",
                                            address = "SecondUser address $it",
                                            someData = it
                                    )
                                }
                        )
                    }
                }
    }
}
