package com.development.test

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.development.test.models.Transaction
import com.development.test.models.TransactionResult


@Dao
interface TransactionDao {



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTransaction(user: Transaction)

    @Query("SELECT a.uid,a.trans_date,a.trans_desc, a.trans_credit, a.trans_debit, ( SELECT SUM(a.trans_credit-a.trans_debit)  FROM `transactionData` a where a.uid <= b.uid) as 'balance' FROM   `transactionData` a,`transactionData` b WHERE b.uid <= a.uid GROUP BY a.uid,a.trans_debit, a.trans_credit")
    fun getAllTransactions(): LiveData<List<TransactionResult>>
}