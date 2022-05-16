package com.udacity.shoestore.models

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class ShoeViewModel() : ViewModel() {

    private val _shoeList: MutableLiveData<ArrayList<Shoe>> = MutableLiveData(ArrayList())

    init {
        _shoeList.value = ArrayList()
    }

    val shoeListLiveData: LiveData<ArrayList<Shoe>>
        get() = _shoeList

    /**
     * Check data validity
     */
    private fun isVail(shoe: Shoe): Boolean {
        return shoe.company.isNotEmpty() && shoe.name.isNotEmpty()
    }

    /**
     * Add shoe to list
     */
    fun addShoe(shoe: Shoe): Boolean {
        if (!isVail(shoe)) return false
        _shoeList.value?.add(shoe)
        return true
    }

}