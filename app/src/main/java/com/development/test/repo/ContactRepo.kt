package com.development.test.repo

import android.annotation.SuppressLint
import android.app.LoaderManager
import android.content.Context
import android.content.CursorLoader
import android.content.Loader
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import androidx.lifecycle.MutableLiveData
import com.development.test.ContactViewModel
import com.development.test.models.ContactData


class ContactRepo(var mLiveList:MutableLiveData<ArrayList<ContactData>>,var model:ContactViewModel,var mCtx:Context) : LoaderManager.LoaderCallbacks<Cursor>{

    @SuppressLint("InlinedApi")
    val PROJECTION: Array<out String> = arrayOf(
        ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone.NUMBER,
        ContactsContract.CommonDataKinds.Phone.PHOTO_URI,
        ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI,
        ContactsContract.CommonDataKinds.Phone.PHOTO_URI,
        ContactsContract.CommonDataKinds.Phone.PHOTO_URI,
        ContactsContract.CommonDataKinds.Phone.PHOTO_URI,
        ContactsContract.CommonDataKinds.Phone.PHOTO_URI,
        ContactsContract.CommonDataKinds.Phone.PHOTO_URI,
    )




    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        val offset = (model.LIMIT * model.PAGE_NO) - model.LIMIT;

        // Starts the query
        return mCtx?.let {
            return CursorLoader(
                it,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                PROJECTION,
                null,
                null,
                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY +
                        " ASC LIMIT " + model.LIMIT + " OFFSET " + offset
            )
        }
    }

    override fun onLoadFinished(loader: Loader<Cursor>?, c: Cursor?) {
        val mList:ArrayList<ContactData> = ArrayList()
        c?.let {
            if (it.moveToFirst()) {
                val idIndex: Int = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)
                val nameIndex: Int = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                val photoIndex: Int = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI)
                val number: Int = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                val thumbnailIndex =it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI)
                do {
                    val contactId: String = it.getString(idIndex)
                    val contactDisplayName: String = it.getString(nameIndex)?:""
                    val photo: String = it.getString(photoIndex)?:""
                    val numberData: String = it.getString(number)?:""
                    val contact = ContactData(
                        contactId,
                        contactDisplayName,
                        "",
                        "",
                        "",
                        "",
                        photo,
                        numberData,
                        it.getString(thumbnailIndex)?:""
                    )

                    mList?.add(contact)
                } while (c.moveToNext())


            }

            c.close()
            model.isLoading=false
            mLiveList.postValue(mList)
        }

    }

    override fun onLoaderReset(loader: Loader<Cursor>?) {
        model.clearList()

    }


}