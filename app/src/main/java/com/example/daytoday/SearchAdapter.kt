package com.example.daytoday

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.daytoday.databinding.ItemSearchBinding
import com.example.daytoday.searchreponse.SearchPages


/**
 * adapter class for the search items.
 */
public class SearchAdapter(data: ArrayList<SearchPages>, selectItem: SelectItem) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    private var mSelectItem: SelectItem
    private var searchData: ArrayList<SearchPages>
    lateinit var mContext: Context

    init {
        searchData = data
        mSelectItem = selectItem
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchAdapter.SearchViewHolder {
        mContext = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemSearchBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_search, parent, false)
        return SearchViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return if (searchData != null) searchData.size else 0
    }

    override fun onBindViewHolder(holder: SearchAdapter.SearchViewHolder, position: Int) {
        holder.mItemBinding.tvTitle.text =
            """${mContext.resources.getString(R.string.title)}${searchData.get(position).title}"""
        holder.mItemBinding.tvIndex.text =
            """${mContext.resources.getString(R.string.index)}${searchData.get(position).index}"""
        holder.mItemBinding.tvContentModel.text =
            """${mContext.resources.getString(R.string.contentmodel)}${searchData.get(position).contentmodel}"""
        holder.mItemBinding.tvpageId.text =
            """${mContext.resources.getString(R.string.pageid)}${searchData.get(position).pageid}"""

        holder.mItemBinding.cvSerachItem.setOnClickListener(View.OnClickListener {
            mSelectItem.onSelectItem(position)
        })
    }

    /**
     * view holder class for the search items
     */
    class SearchViewHolder(itemBinding: ItemSearchBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        var mItemBinding: ItemSearchBinding

        init {
            mItemBinding = itemBinding
        }
    }
}