package com.udacity.shoestore.views

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.view.marginTop
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ItemShoeBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeViewModel
import kotlinx.android.synthetic.main.item_shoe.view.*
import timber.log.Timber

class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding

    private val mModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_list,
            container, false
        )

        setHasOptionsMenu(true)

        (activity as MainActivity).removeBackIcon()

        binding.addShoeFloatButton.setOnClickListener {
            findNavController()
                .navigate(
                    ShoeListFragmentDirections
                        .actionShoeListFragmentToShoeDetailFragment()
                )
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.app_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController()
            .navigate(
                ShoeListFragmentDirections
                    .actionShoeListFragmentToLoginFragment()
            )
        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mModel.shoeListLiveData.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                it.forEachIndexed { index, shoe ->
                    createAndAddShoeView(index, shoe)
                }
            }
        }
    }


    /**
     * Create shoe view and add to the layout
     */
    private fun createAndAddShoeView(index: Int, shoe: Shoe) {
        val shoeView = DataBindingUtil.inflate<ItemShoeBinding>(
            layoutInflater,
            R.layout.item_shoe,
            binding.shoeListLinearLayout,
            false
        )
        shoeView.shoe = shoe
        shoeView.index = (index + 1)
        binding.shoeListLinearLayout.addView(shoeView.root)
    }


}