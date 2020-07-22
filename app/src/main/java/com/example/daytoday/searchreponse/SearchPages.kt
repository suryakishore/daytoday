package com.example.daytoday.searchreponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SearchPages {

    @Expose
    @SerializedName("title")
    val title: String? = null


    @Expose
    @SerializedName("index")
    val index: Int? = null


    @Expose
    @SerializedName("contentmodel")
    val contentmodel: String? = null


    @Expose
    @SerializedName("pagelanguage")
    val pagelanguage: String? = null


    @Expose
    @SerializedName("pageid")
    val pageid: String? = null


    @Expose
    @SerializedName("fullurl")
    val fullurl: String? = null

    @Expose
    @SerializedName("editurl")
    val editurl: String? = null

    @Expose
    @SerializedName("canonicalurl")
    val canonicalurl: String? = null


}