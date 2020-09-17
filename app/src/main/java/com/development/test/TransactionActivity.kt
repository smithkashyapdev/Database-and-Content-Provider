package com.development.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.development.test.databinding.ActivityTransactionBinding
import com.development.test.models.Transaction
import com.development.test.models.TransactionResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TransactionActivity:AppCompatActivity (){


    lateinit var mBinding:ActivityTransactionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityTransactionBinding.inflate(layoutInflater)
        setContentView(mBinding.root)


        mBinding.saveButton.setOnClickListener {

            val mTransaction=Transaction()
            if (mBinding.spinnerType.selectedItemPosition==0){
                mTransaction.trans_credit=0.0
                mTransaction.trans_debit=mBinding.amountEditText.text.toString().toDouble()
            }
            else {
                mTransaction.trans_debit=0.0
                mTransaction.trans_credit=mBinding.amountEditText.text.toString().toDouble()
            }

            mTransaction.trans_date="17-Sep-2020"
            mTransaction.trans_desc=mBinding.descEditText.text.toString()


            lifecycleScope.launch {
                saveData(mTransaction)
            }



        }

        observeList()
    }

    suspend fun saveData(tData: Transaction) = DatabaseManager
        .invoke(applicationContext)
        .getTransactionDao().saveTransaction(tData)



    fun observeList(){
        DatabaseManager
            .invoke(applicationContext)
            .getTransactionDao()
            .getAllTransactions().observe(this,{

                it?.let {
                    val mAdapter=TransactionAdapter(it as ArrayList<TransactionResult>)
                    mBinding.listData.adapter=mAdapter
                }

            })
    }

}