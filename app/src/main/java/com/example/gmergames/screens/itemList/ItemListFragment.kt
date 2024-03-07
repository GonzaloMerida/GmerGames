package com.example.gmergames.screens.itemList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.gmergames.R
import com.example.gmergames.databinding.FragmentItemListBinding
import com.example.gmergames.datasource.Datasource
import com.example.gmergames.screens.detailItem.DetailItemFragment

class ItemListFragment : Fragment(){
    private var _binding: FragmentItemListBinding? = null
    val binding
        get() = _binding!!

    val items = Datasource.getItemList()

    private lateinit var itemAdapter: DetailItemFragment

    private lateinit var layoutManager: RecyclerView.LayoutManager
    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        //initRecView()
        binding.clFragmentItemList.isVisible = false

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_itemListFragment_to_menuFragment)
        }
        binding.btnAddToFavorites.setOnClickListener {
            //findNavController().navigate(R.id.action_itemListFragment_to_menuFragment)
        }
        binding.btnGoToFavs.setOnClickListener {
            findNavController().navigate(R.id.action_itemListFragment_to_favItemListFragment)
        }
        binding.btnViewItemDetail.setOnClickListener {
            findNavController().navigate(R.id.action_itemListFragment_to_detailItemFragment)
        }
        return binding.root
    }

    //private fun initRecView() {
       //itemAdapter = DetailItemFragment(items)
        //binding.rvItems.adapter = itemAdapter

        //layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
      //  binding.rvItems.layoutManager = layoutManager
    //}
    private fun addToFavoriteItem(pos : Int){
    }

   // override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    //    super.onViewCreated(view, savedInstanceState)
    //}
}