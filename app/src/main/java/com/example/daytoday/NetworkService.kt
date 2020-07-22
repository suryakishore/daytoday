package com.example.daytoday

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {
    @GET("api.php")
    fun getSearchResults(
        @Query("action") action: String,
        @Query("format") format: String,
        @Query("prop") prop: String,
        @Query("generator") generator: String,
        @Query("redirects") redirects: Int,
        @Query("formatversion") formatversion: Int,
        @Query("piprop") piprop: String,
        @Query("pithumbsize") pithumbsize: Int,
        @Query("pilimit") pilimit: Int,
        @Query("prop") proper: String,
        @Query("inprop") inprop: String,
        @Query("wbptterms") wbptterms: String,
        @Query("gpssearch") gpssearch: String,
        @Query("gpslimit") gpslimit: Int
    ): Observable<Response<ResponseBody>>
}