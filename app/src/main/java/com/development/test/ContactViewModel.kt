package com.development.test

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.provider.ContactsContract
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.development.test.models.ContactData
import com.development.test.repo.ContactRepo

class ContactViewModel(val ctx: Context) : ViewModel() {

    var mListData:MutableLiveData<ArrayList<ContactData>>?= MutableLiveData()
    var LIMIT = 10
    var PAGE_NO=1
    var TOTAL_PAGES = 5
    var isLoading = false
    var isLastPage = false

    var mContactRepo:ContactRepo

    init {
     mContactRepo= mListData?.let {
         ContactRepo(it,this,ctx) }!!
    }







    fun clearList(){

    }



}