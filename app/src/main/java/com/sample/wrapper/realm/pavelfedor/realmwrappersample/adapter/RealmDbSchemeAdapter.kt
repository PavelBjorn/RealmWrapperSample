package com.sample.wrapper.realm.pavelfedor.realmwrappersample.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.sample.wrapper.realm.pavelfedor.realmwrappersample.R
import io.realm.RealmObjectSchema
import io.realm.internal.Table
import kotlinx.android.synthetic.main.item.view.*

class RealmDbSchemeAdapter(val onClick: (tableName: String) -> Unit) : RecyclerView.Adapter<RealmDbSchemeAdapter.ViewHolder>() {

    private val objectScheme: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
    )

    fun addAll(all: Collection<String>) {
        objectScheme.size.apply {
            objectScheme.addAll(all)
            notifyItemRangeInserted(this, all.size)
        }
    }

    fun clear() {
        objectScheme.size.apply {
            objectScheme.clear()
            notifyItemRangeRemoved(0, this)
        }
    }

    override fun getItemCount() = objectScheme.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(objectScheme[position])

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        init {
            itemView.itemParent.setOnClickListener {
                onClick.invoke(itemView.tvText.text.toString())
            }
        }

        fun bind(schema: String) {
            itemView.tvText.text = schema
        }
    }
}
