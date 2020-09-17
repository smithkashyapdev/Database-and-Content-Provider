package com.development.test.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactionData")
 class Transaction() {
    @PrimaryKey(autoGenerate = true)
    private var uid = 0

    @ColumnInfo(name = "trans_date")
    var trans_date: String? = null

    @ColumnInfo(name = "trans_desc")
    var trans_desc: String? = null

    @ColumnInfo(name = "trans_credit")
    var trans_credit: Double? = null

    @ColumnInfo(name = "trans_debit")
    var trans_debit: Double? = null


   fun getUid(): Int? {
      return uid
   }

   fun gettrans_date(): String? {
      return trans_date
   }

   fun gettrans_desc(): String? {
      return trans_desc
   }

   fun gettrans_credit(): Double? {
      return trans_credit
   }

   fun gettrans_debit(): Double? {
      return trans_debit
   }

//Setter

   fun setUid( uid:Int)  {
      this.uid=uid
   }

   fun settrans_date(trans_date :String) {
      this.trans_date=trans_date
   }

   fun settrans_desc(trans_desc :String) {
      this.trans_desc=trans_desc
   }

   fun settrans_credit(trans_credit:Double) {
      this.trans_credit=trans_credit
   }

   fun settrans_debit(trans_debit:Double) {
      this.trans_debit=trans_debit
   }


}