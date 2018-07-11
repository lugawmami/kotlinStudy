package com.crcarrascal.aaakotlin.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.crcarrascal.aaakotlin.webservices.models.Forecast
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

/**
 * Created by crcarrascal on 10/07/2018.
 */

class  ForecastListAdapter(val items : List<Forecast>) : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>(), AnkoLogger{

    var weather : List<Forecast>? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        info { "$items[0].weather[0].description" }
        return ViewHolder(TextView(parent!!.context))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        info { "$items[position].weather[0].description" }
        with(items[position].weather[0]){
            holder!!.textView.text = "$description"
        }
    }

    fun addUpdateData(items : List<Forecast>){
        
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(val textView : TextView) : RecyclerView.ViewHolder(textView)
}