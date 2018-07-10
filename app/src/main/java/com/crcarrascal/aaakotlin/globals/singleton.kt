package com.crcarrascal.aaakotlin.globals

import com.crcarrascal.aaakotlin.webservices.OpenWeatherMapService
import io.reactivex.disposables.Disposable

/**
 * Created by crcarrascal on 10/07/2018.
 */

val openWeatherMapServe by lazy {
    OpenWeatherMapService.create()
}

var disposable : Disposable? = null
