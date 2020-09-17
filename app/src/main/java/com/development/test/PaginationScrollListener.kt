package com.development.test


import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


/**
 * Pagination
 * Created by Suleiman19 on 10/15/16.
 * Copyright (c) 2016. Suleiman Ali Shakir. All rights reserved.
 */
abstract class PaginationScrollListener
/**
 * Supporting only LinearLayoutManager for now.
 *
 * @param layoutManager
 */(var layoutManager: LinearLayoutManager) :
    RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
        if (!isLoading && !isLastPage) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0 && totalItemCount >= totalPageCount) {
                loadMoreItems()
            }
        }
    }

    protected abstract fun loadMoreItems()
    abstract val totalPageCount: Int
    abstract val isLastPage: Boolean
    abstract val isLoading: Boolean

}