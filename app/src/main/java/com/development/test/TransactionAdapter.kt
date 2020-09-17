package com.development.test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.development.test.models.TransactionResult

class TransactionAdapter(val list:ArrayList<TransactionResult>): RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {






    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val formNameView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_trans, parent, false)
        return ViewHolder(formNameView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val mResult=list.get(position)
        holder.dateTextView.text=mResult.trans_date
        holder.descTextView.text=mResult.trans_desc
        holder.creditTextView.text=mResult.trans_credit.toString()
        holder.debitTextView.text=mResult.trans_debit.toString()
        holder.balanceTextView.text=mResult.balance.toString()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder (itemView: View) :
        RecyclerView.ViewHolder(itemView){
        var dateTextView: TextView
        var descTextView: TextView
        var creditTextView: TextView
        var debitTextView: TextView
        var balanceTextView: TextView

        init {
            dateTextView = itemView.findViewById(R.id.date)
            descTextView = itemView.findViewById(R.id.desc)
            creditTextView = itemView.findViewById(R.id.credit)
            debitTextView = itemView.findViewById(R.id.debit)
            balanceTextView = itemView.findViewById(R.id.balance)
        }
    }
}