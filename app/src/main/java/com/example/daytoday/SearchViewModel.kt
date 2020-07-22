package com.example.daytoday

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.daytoday.Constants.ACTION
import com.example.daytoday.Constants.FORMAT
import com.example.daytoday.Constants.FORMAT_VERSION
import com.example.daytoday.Constants.GENERATOR
import com.example.daytoday.Constants.IN_PROP
import com.example.daytoday.Constants.LIMIT
import com.example.daytoday.Constants.PI_LIMIT
import com.example.daytoday.Constants.PI_PROP
import com.example.daytoday.Constants.PI_THUMB_SIZE
import com.example.daytoday.Constants.PROP
import com.example.daytoday.Constants.PROPER
import com.example.daytoday.Constants.REDIRECTS
import com.example.daytoday.Constants.SUCCESS
import com.example.daytoday.Constants.WEB_TERMS
import com.example.daytoday.searchreponse.SearchPages
import com.example.daytoday.searchreponse.SearchResponse
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.*

/**
 * view model class for the main activity.
 */
class SearchViewModel() : ViewModel() {

    private var mSearchData = MutableLiveData<ArrayList<SearchPages>>()
    private var networkService: NetworkService? = null
    public fun initializeRetrofit(context: Context) {

        networkService = NetworkManager.initializeBaseUrlRetrofit(context)


    }

    /**
     * This method used to get the search Data.
     */
    public fun getSearchResults(search: String) {
        networkService.also { it ->
            it!!.getSearchResults(
                ACTION,
                FORMAT,
                PROP,
                GENERATOR,
                REDIRECTS,
                FORMAT_VERSION,
                PI_PROP,
                PI_THUMB_SIZE,
                PI_LIMIT,
                PROPER,
                IN_PROP,
                WEB_TERMS,
                search,
                LIMIT
            ).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    if (it != null) {
                        try {
                            val jsonObject: JSONObject
                            val code = it.code()
                            if (code == SUCCESS) {
                                val response: String = it.body()!!.string()
                                jsonObject = JSONObject(response)
                                val gson = Gson()
                                val searchResponse =
                                    gson.fromJson(jsonObject.toString(), SearchResponse::class.java)
                                if (searchResponse != null) {
                                    mSearchData.postValue(
                                        searchResponse.getQuery()!!.getPagesData()
                                    )
                                }
                            } else {
                                jsonObject = JSONObject(it.errorBody()!!.string())
                                mSearchData.postValue(null)
                            }
                        } catch (e: JSONException) {
                            mSearchData.postValue(null)
                        } catch (e: IOException) {
                            mSearchData.postValue(null)
                        }
                    }
                }
        }
    }


    /**
     * notify activity search data comes
     */
    fun onSearchData(): MutableLiveData<ArrayList<SearchPages>> {
        return mSearchData
    }


}