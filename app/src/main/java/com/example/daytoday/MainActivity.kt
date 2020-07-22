package com.example.daytoday

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.daytoday.databinding.ActivityMainBinding
import com.example.daytoday.searchreponse.SearchPages


/**
 * This activity is used to show the wiki pedia search results
 */
class MainActivity : AppCompatActivity(), SelectItem, TextWatcher {

    lateinit var mBinding: ActivityMainBinding
    lateinit var mImagesViewModel: SearchViewModel
    private var mSearchData = ArrayList<SearchPages>()
    lateinit var adapter: SearchAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeData()
        subscribeSearchData()
    }

    /**
     * subscribe to search data which is coming from server
     */
    private fun subscribeSearchData() {
        mImagesViewModel.onSearchData().observe(this, Observer {
            mBinding.pgLoadData.visibility = View.GONE
            if (it != null) {
                mSearchData.clear()
                mSearchData.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })
    }

    /**
     * initialize data related to user interface  and view model and initially
     * i am calling with an api with the search key wiki.
     */
    private fun initializeData() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mImagesViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        mBinding.viewModel = mImagesViewModel
        adapter = SearchAdapter(mSearchData, this)
        mBinding.rvWikipedia.adapter = adapter
        mBinding.pgLoadData.visibility = View.VISIBLE
        mBinding.etSearch.addTextChangedListener(this)
        mImagesViewModel.initializeRetrofit(this)
        mImagesViewModel.getSearchResults(resources.getString(R.string.wiki))
    }

    /**
     * This method will listen when we click on recyclerview item,
     * and we are opening web browser when we click on item.
     */
    override fun onSelectItem(pos: Int) {
        val webpage: Uri = Uri.parse(mSearchData.get(pos).fullurl)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    /**
     * This method will listen when we typing something in the  edit text
     */
    override fun afterTextChanged(s: Editable?) {
        if (s.toString() != null && !s.toString().isEmpty()) {
            mBinding.pgLoadData.visibility = View.VISIBLE
            mImagesViewModel.getSearchResults(s.toString())
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }
}