package com.development.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.development.test.databinding.ActivityDetailContactBinding
import com.development.test.models.ContactData

class ContactDetailActivity :AppCompatActivity(){

    companion object{
        val KEY_DETAIL=" KEY_DATA"
    }
    private lateinit var binding: ActivityDetailContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mContactData=intent.getParcelableExtra<ContactData>(KEY_DETAIL)

        val options = RequestOptions()
            .transform(FitCenter(), RoundedCorners(resources.getDimension(R.dimen.normal_margin).toInt()))

        Glide.with(this)
            .load(mContactData.photoUri)
            .apply(options)
            .into(binding.contactPhoto)

        binding.contactFirstName.setText(mContactData.getNameToDisplay())
        binding.contactNumber.setText(mContactData.number)
    }


}