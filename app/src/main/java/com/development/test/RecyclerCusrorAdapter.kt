package com.development.test
import android.content.Intent
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.development.test.models.ContactData
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created on : Jan 27, 2019
 * Author     : AndroidWave
 * Email    : info@androidwave.com
 */
class RecyclerCusrorAdapter(mList:ArrayList<ContactData>) :
    RecyclerView.Adapter<RecyclerCusrorAdapter.FriendViewHolder?>(),
    Filterable{

    var mList:ArrayList<ContactData>?=null

    var contactFilterList = ArrayList<ContactData>()

    init {
        this.mList=mList
        contactFilterList = mList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val formNameView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.contacts_list_item, parent, false)
        return FriendViewHolder(formNameView)
    }





    inner class FriendViewHolder(itemView: View) :
        ViewHolder(itemView) {
        var nameTextView: TextView

        init {
            nameTextView = itemView.findViewById(android.R.id.text1)
        }
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {

        holder.nameTextView.text = contactFilterList?.get(position)?.getNameToDisplay()
        holder.nameTextView.setTag(contactFilterList?.get(position))
        holder.nameTextView.setOnClickListener {
            goToNext(it)
        }
    }

    override fun getItemCount(): Int {
        return contactFilterList!!.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {

                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    contactFilterList = mList!!
                } else {
                    val resultList = ArrayList<ContactData>()
                    for (row in mList!!) {
                        if (row.getNameToDisplay().toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    contactFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = contactFilterList
                return filterResults


            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                contactFilterList = results?.values as ArrayList<ContactData>
                notifyDataSetChanged()            }

        }
    }


    fun goToNext(v:View){
        val mData:ContactData= v.getTag() as ContactData
        val mintent=Intent(v.context,ContactDetailActivity::class.java)
        mintent.apply {
            putExtra(ContactDetailActivity.KEY_DETAIL,mData)
        }
        v.context.startActivity(mintent)
    }

}