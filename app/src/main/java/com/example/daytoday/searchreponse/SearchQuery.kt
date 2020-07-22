package com.example.daytoday.searchreponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SearchQuery {

    @Expose
    @SerializedName("pages")
    private val pages: ArrayList<SearchPages>? = null

    public fun getPagesData(): ArrayList<SearchPages>? {
        return pages;
    }
}