package com.crcarrascal.aaakotlin.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.crcarrascal.aaakotlin.webservices.models.Forecast
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit

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
        with(items[position]){
            val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(position.toLong())
            val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())

            holder!!.textView.text = "${df.format(dt)} ${weather[0].description} ${temp.max}C ${temp.min}C"
        }
    }

    fun addUpdateData(items : List<Forecast>){

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(val textView : TextView) : RecyclerView.ViewHolder(textView)
}