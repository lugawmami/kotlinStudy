package com.crcarrascal.aaakotlin.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.crcarrascal.aaakotlin.R
import com.crcarrascal.aaakotlin.utils.ctx
import com.crcarrascal.aaakotlin.webservices.models.Forecast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_forecast.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find
import org.jetbrains.anko.info
import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by crcarrascal on 10/07/2018.
 */

public class  ForecastListAdapter(val weekForecast : List<Forecast>, val itemClick: OnItemClickListener)
    : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>(), AnkoLogger {

    var weather: List<Forecast>? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent?.ctx).inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.bindForecast(weekForecast[position])
    }

    // go to Android SDK's platform tools (make sure phone is connected)

    // adb tcpip 5555 : set to your desired port
    // adb shell netcfg : show all connected devices on ADB
    // adb connect <your device ip> connected to ADB
    fun addUpdateData(items: List<Forecast>) {

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = weekForecast.size

    class ViewHolder(view: View, val itemClick: OnItemClickListener) : RecyclerView.ViewHolder(view), AnkoLogger {

        private val iconView = view.find<ImageView>(R.id.icon)
        private val dateView = view.find<TextView>(R.id.date)
        private val descriptionView = view.find<TextView>(R.id.description)
        private val maxTemperatureView = view.find<TextView>(R.id.maxTemperature)
        private val minTemperatureView = view.find<TextView>(R.id.minTemperature)


        fun bindForecast(forecast: Forecast) {
            with(forecast) {

                val dt = Calendar.getInstance().timeInMillis + this.dt * 1000
                val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())

                Picasso.get().load("http://openweathermap.org/img/w/${this.weather[0].icon}.png").into(iconView)
                dateView.text = "${df.format(dt)}"
                descriptionView.text = "${weather[0].description}"
                maxTemperatureView.text = "${temp.max}C"
                minTemperatureView.text = "${temp.min}C"
                itemView.setOnClickListener { itemClick(this) }
            }
        }


    }

    interface OnItemClickListener {
        operator fun invoke(forecast: Forecast)
    }
}