/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.sbaltinsoy.gdg_bitirmeproje.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sbaltinsoy.gdg_bitirmeproje.network.CurrencyProperty
import com.sbaltinsoy.gdg_bitirmeproje.network.DovizApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {

    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the response String
    val response: LiveData<String>
        get() = _response

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getMarsRealEstateProperties()

    }

    /**
     * Sets the value of the status LiveData to the Mars API status.
     */
    var dolarKur: Double = 0.0
    var mainEuroKur: Double = 1.0
    var sterlinKur: Double = 0.0
    var tryKur: Double = 0.0

    private fun getMarsRealEstateProperties() {
        _response.value = "Set the Mars API Response here!"

        DovizApi.retrofitService.getProperties().enqueue(
                object: Callback<CurrencyProperty> {
                    override fun onResponse(
                        call: Call<CurrencyProperty>,
                        response: Response<CurrencyProperty>
                    ) {
                        response.body()?.let {
                            tryKur = it.rates.TRY
                            dolarKur = it.rates.USD
                            sterlinKur = it.rates.GBP
                        }
                        _response.value = "1 EURO : " + tryKur.toString() + "TL  " + dolarKur.toString() + " DOLAR   "+ sterlinKur.toString() + " STERLIN  "
                    }
                    override fun onFailure(call: Call<CurrencyProperty>, t: Throwable) {
                        _response.value = "Failure: " + t.message
                    }
                })
    }
}
