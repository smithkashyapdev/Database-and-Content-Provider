package com.development.test

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.development.test.databinding.ActivityMainBinding
import com.development.test.models.ContactData


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var cursorAdapter: RecyclerCusrorAdapter
    private var factory: ContactModelFactory ?=null
     val mContactModel : ContactViewModel by lazy {
         ViewModelProviders.of(this, factory).get(ContactViewModel::class.java)
    }

    var mList:ArrayList<ContactData>?= ArrayList()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        factory= ContactModelFactory(this)
        cursorAdapter = mList?.let { RecyclerCusrorAdapter(it) }!!

        binding.contactList.layoutManager=LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.contactList.adapter=cursorAdapter
        mContactModel.mListData?.observe(this, {
            mList!!.addAll(it)
            cursorAdapter.notifyDataSetChanged()
        })
        initLoader()
        binding.contactList.addOnScrollListener(object :
            PaginationScrollListener(binding.contactList.layoutManager as LinearLayoutManager) {
            override fun loadMoreItems() {
                mContactModel.isLoading = true
                mContactModel.PAGE_NO++
                Toast.makeText(
                    this@MainActivity,
                    "load" + mContactModel.PAGE_NO,
                    Toast.LENGTH_SHORT
                ).show()
                loadItem()
            }

            override val totalPageCount: Int
                get() = mContactModel.TOTAL_PAGES
            override val isLastPage: Boolean
                get() = mContactModel.isLastPage
            override val isLoading: Boolean
                get() = mContactModel.isLoading
        })



        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {

            }
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {

            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                cursorAdapter.filter.filter(s)
            }
        })


    }

    fun loadItem(){
        loaderManager.restartLoader(0, null, mContactModel.mContactRepo)
    }

    fun initLoader(){
        loaderManager.initLoader(0, null, mContactModel.mContactRepo)
    }

    fun clearList(){
        mList?.clear()
        cursorAdapter.notifyDataSetChanged()
        mContactModel.PAGE_NO=1
        initLoader()
    }

}