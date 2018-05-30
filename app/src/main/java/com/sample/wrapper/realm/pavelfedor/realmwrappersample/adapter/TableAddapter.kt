package com.sample.wrapper.realm.pavelfedor.realmwrappersample.adapter

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sample.wrapper.realm.pavelfedor.realmwrappersample.R
import io.realm.RealmFieldType
import io.realm.internal.Row
import io.realm.internal.Table
import kotlinx.android.synthetic.main.item_table_column.view.*
import kotlinx.android.synthetic.main.item_table_row.view.*

class TableAddapter(
        val table: Table,
        val type: Type,
        val columnIndex: Long = 0
) : RecyclerView.Adapter<TableAddapter.TableViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            createViewHolder(parent.run {
                LayoutInflater.from(context).inflate(viewType, this, false)
            })

    override fun getItemViewType(position: Int) = when (type) {
        Type.COLUMN -> R.layout.item_table_column
        Type.ROW -> R.layout.item_table_row
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.layoutManager = when (type) {
            Type.COLUMN -> LinearLayoutManager(recyclerView.context, LinearLayoutManager.HORIZONTAL, false)
            Type.ROW -> LinearLayoutManager(recyclerView.context)
        }
    }

    override fun getItemCount() = when (type) {
        Type.COLUMN -> table.columnCount.toInt()
        Type.ROW -> table.size().toInt()
    }

    private fun createViewHolder(view: View): TableViewHolder = when (type) {
        Type.COLUMN -> ColumnViewHolder(view)
        Type.ROW -> RowViewHolder(view)
    }

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) = holder.bind(table, position)

    abstract class TableViewHolder(root: View) : RecyclerView.ViewHolder(root) {
        abstract fun bind(table: Table, position: Int)
    }

    inner class ColumnViewHolder(root: View) : TableViewHolder(root) {
        override fun bind(table: Table, position: Int) {
            itemView.tvTitle.text = table.getColumnName(position.toLong())
            itemView.tvType.text = table.getColumnType(position.toLong()).name
            itemView.rowList.apply {
                isNestedScrollingEnabled = false
                adapter = TableAddapter(
                        table = table,
                        type = Type.ROW,
                        columnIndex = position.toLong()
                )
            }
        }
    }

    inner class RowViewHolder(root: View) : TableViewHolder(root) {

        override fun bind(table: Table, position: Int) {
            itemView.tvValue.text = getValue(
                    table = table,
                    position = position,
                    columnIndex = columnIndex
            ).toString()
        }

        fun getValue(table: Table, position: Int, columnIndex: Long): Any = when (table.getColumnType(columnIndex)) {
            RealmFieldType.INTEGER -> table.getLong(columnIndex, position.toLong())
            RealmFieldType.BOOLEAN -> table.getBoolean(columnIndex, position.toLong())
            RealmFieldType.STRING -> table.getString(columnIndex, position.toLong())
            RealmFieldType.BINARY -> table.getBinaryByteArray(columnIndex, position.toLong())
            RealmFieldType.DATE -> table.getDate(columnIndex, position.toLong())
            RealmFieldType.FLOAT -> table.getFloat(columnIndex, position.toLong())
            RealmFieldType.DOUBLE -> table.getDouble(columnIndex, position.toLong())
            else -> ""
        }
    }

    enum class Type {
        COLUMN,
        ROW
    }
}
