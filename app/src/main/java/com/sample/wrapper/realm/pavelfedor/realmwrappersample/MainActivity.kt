package com.sample.wrapper.realm.pavelfedor.realmwrappersample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.sample.wrapper.realm.pavelfedor.realmwrappersample.adapter.RealmDbSchemeAdapter
import com.sample.wrapper.realm.pavelfedor.realmwrappersample.extensions.RealmUtil
import com.sample.wrapper.realm.pavelfedor.realmwrappersample.extensions.fillItemsWithRealmDbs
import com.sample.wrapper.realm.pavelfedor.realmwrappersample.extensions.showDataActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var selectedItem: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setHomeButtonEnabled(true)
        }

        tableLists.layoutManager = LinearLayoutManager(this)
        tableLists.adapter = RealmDbSchemeAdapter({
            showDataActivity(it, selectedItem)
        })

        navView.fillItemsWithRealmDbs()
        navView.setNavigationItemSelectedListener {
            selectedItem = it.title.toString()
            (tableLists.adapter as RealmDbSchemeAdapter).apply {
                clear()
                addAll(
                        RealmUtil.getSharedInstance(it.title.toString()).run {
                            (0 until size()).map { getTableName(it.toInt()) }
                                    .filter { it.startsWith("class_") }
                                    .map { getTable(it).name ?: "" }
                                    .toList()
                                    .apply { close() }
                        }
                )

                parentDrawer.closeDrawer(Gravity.START)
            }

            true
        }

        parentDrawer.addDrawerListener(ActionBarDrawerToggle(this, parentDrawer, toolbar, R.string.openDrawer, R.string.closeDrawer).apply {
            syncState()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            parentDrawer.openDrawer(Gravity.START)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}
