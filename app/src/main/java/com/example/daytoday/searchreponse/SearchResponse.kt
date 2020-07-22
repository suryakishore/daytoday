package com.example.daytoday.searchreponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SearchResponse {

    @Expose
    @SerializedName("query")
    private val query: SearchQuery? = null
    public fun getQuery(): SearchQuery? {
        return query
    }


}