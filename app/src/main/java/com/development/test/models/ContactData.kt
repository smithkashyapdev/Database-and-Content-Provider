package com.development.test.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContactData(
    var id: String, var firstName: String, var middleName: String, var surname: String, var suffix: String, var nickname: String,
    var photoUri: String,var number:String,var thumbNail:String) :
        Comparable<ContactData>, Parcelable {
    companion object {
        var sorting = 0
        var startWithSurname = false
    }



    fun getNameToDisplay(): String {
        val firstMiddle = "$firstName $middleName".trim()
        val firstPart = if (startWithSurname) surname else firstMiddle
        val lastPart = if (startWithSurname) firstMiddle else surname
        val suffixComma = if (suffix.isEmpty()) "" else ", $suffix"
        val fullName = "$firstPart $lastPart$suffixComma".trim()
        return fullName
    }





    fun getSignatureKey() = if (photoUri.isNotEmpty()) photoUri else hashCode()
    override fun compareTo(other: ContactData): Int {
        TODO("Not yet implemented")
    }
}
