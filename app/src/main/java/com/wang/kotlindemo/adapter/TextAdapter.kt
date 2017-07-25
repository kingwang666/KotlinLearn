package com.wang.kotlindemo.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.wang.kotlindemo.R
import com.wang.kotlindemo.interfaces.OnRecyclerClickListener
import com.wang.kotlindemo.mdoel.TextMode

/**
 * Created on 2017/5/27.
 * Author: wang
 */
class TextAdapter(private val itemArray: MutableList<TextMode>, private val listener: OnRecyclerClickListener) : RecyclerView.Adapter<TextAdapter.TextViewHolder>() {

    override fun getItemCount(): Int {
        return itemArray.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): TextViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_text, parent, false)
        return TextViewHolder(itemView)
    }

    override fun onBindViewHolder(vh: TextViewHolder, position: Int) {
        val (_, name) = itemArray[position]
        vh.mNameTV.text = name
    }

    inner class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mNameTV: TextView = itemView.findViewById<View>(R.id.name_tv) as TextView

        init {
            itemView.setOnClickListener {
                listener.onClick(itemViewType, adapterPosition)
            }
        }
    }
}