package com.udacity.shoestore.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.ShoeViewModel
import com.udacity.shoestore.models.Shoe

class ShoeDetailFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding

    private val mModel: ShoeViewModel by activityViewModels()
    private lateinit var mShoeCreate: Shoe

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)

        mShoeCreate = Shoe("",0.0,"","", listOf())

        binding.controller = this
        binding.shoe = mShoeCreate

        return binding.root
    }

    /**
     * back to the shoe list screen
     */
    fun back(){
        findNavController().navigateUp()
    }

    /**
     * Check data validity
     * Add data to ViewModel
     */
    fun addShoe(){
        //Check vail
        val result = mModel.addShoe(mShoeCreate)
        if(result){
            Toast.makeText(context,getString(R.string.Add_shoe_success), Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }else{
            Toast.makeText(context,getString(R.string.Add_shoe_fail), Toast.LENGTH_SHORT).show()
        }
    }
}