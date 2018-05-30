package com.sample.wrapper.realm.pavelfedor.realmwrappersample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sample.wrapper.realm.pavelfedor.realmwrappersample.adapter.TableAddapter
import com.sample.wrapper.realm.pavelfedor.realmwrappersample.extensions.RealmUtil
import com.sample.wrapper.realm.pavelfedor.realmwrappersample.extensions.getRealmFileName
import com.sample.wrapper.realm.pavelfedor.realmwrappersample.extensions.getTableName
import io.realm.internal.OsSharedRealm
import kotlinx.android.synthetic.main.date_activity.*

class DataActivity : AppCompatActivity() {

    private lateinit var sharedRealm: OsSharedRealm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.date_activity)
        rvTable.adapter = TableAddapter(
                table = RealmUtil.getSharedInstance(intent.getRealmFileName()).run {
                    sharedRealm = this
                    getTable(intent.getTableName())
                },
                type = TableAddapter.Type.COLUMN
        )
    }


    override fun onDestroy() {
        sharedRealm.close()
        super.onDestroy()
    }
}
