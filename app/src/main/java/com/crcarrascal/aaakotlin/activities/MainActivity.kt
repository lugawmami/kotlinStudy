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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import org.jetbrains.anko.find

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val forecastList : RecyclerView = find(R.id.forecast_list)
        forecastList.layoutManager = LinearLayoutManager(this)
        forecastList.adapter = ForecastListAdapter(items)

        val apiService = OpenWeatherMapService.create()
        apiService.search("15646a06818f61f7b8d7823ca833e1ce", 1701668, "json", "metric", 7)
        getWeatherData(message)
    }
}

private fun getWeatherData(textview : TextView){
    disposable =
            openWeatherMapServe
                    .search("15646a06818f61f7b8d7823ca833e1ce", 1701668, "json", "metric", 7)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { result -> showResult(result.city.name, textview)},
                            { error -> showError(error.message, textview) }
                    )
}

fun showResult(city : String, textview: TextView){
    textview.text = city
}

fun showError(error: String?, textview: TextView){
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