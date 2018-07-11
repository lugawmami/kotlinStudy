package com.crcarrascal.aaakotlin.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import android.widget.Toast
import com.crcarrascal.aaakotlin.adapters.ForecastListAdapter
import com.crcarrascal.aaakotlin.R
import com.crcarrascal.aaakotlin.globals.disposable
import com.crcarrascal.aaakotlin.globals.openWeatherMapServe
import com.crcarrascal.aaakotlin.webservices.OpenWeatherMapService
import com.crcarrascal.aaakotlin.webservices.models.OpenWeatherMapServiceModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find
import org.jetbrains.anko.info

class MainActivity : AppCompatActivity(), AnkoLogger {

    var forecastList : RecyclerView? = null

    var weatherResult : OpenWeatherMapServiceModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forecastList = find(R.id.forecast_list)
        val apiService = OpenWeatherMapService.create()
        //apiService.search("15646a06818f61f7b8d7823ca833e1ce", 1701668, "json", "metric", 7)
        getWeatherData(message)


    }

    private fun getWeatherData(textview : TextView){
        disposable =
                openWeatherMapServe
                        .search("15646a06818f61f7b8d7823ca833e1ce", 1701668, "json", "metric", 8)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result -> showResult(result, textview)},
                                { error -> showError(error.message, textview) }
                        )
    }

    private fun showResult(result : OpenWeatherMapServiceModel, textview: TextView){

        info { "${result.toString()}" }
        weatherResult = result


        forecastList!!.layoutManager = LinearLayoutManager(this)
        forecastList!!.adapter = ForecastListAdapter(weatherResult!!.list)
        forecastList!!.adapter.notifyDataSetChanged()
    }

    private fun showError(error: String?, textview: TextView){
        textview.text = error
    }

    private val items = listOf(
            "Mon 6/23 - Sunny - 31/17",
            "Tue 6/24 - Foggy - 21/8",
            "Wed 6/25 - Cloudy - 22/17",
            "Thurs 6/26 - Rainy - 18/11",
            "Fri 6/27 - Foggy - 21/10",
            "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
            "Sun 6/29 - Sunny - 20/7"
    )
}

