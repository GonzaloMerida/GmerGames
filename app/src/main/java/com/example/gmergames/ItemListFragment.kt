package com.example.gmergames

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gmergames.databinding.FragmentItemListBinding

class ItemListFragment {
    private var _binding: FragmentItemListBinding? = null
    val binding
        get() = _binding!!

    val items = Datasource.getItemList()

    private lateinit var itemAdapter: DetailItemFragment

    private lateinit var layoutManager: RecyclerView.LayoutManager
    //override  fun onCreate(savedInstanceState: Bundle?) {
      //  super.onCreate(savedInstanceState)
        //arguments?.let {
        //}
    //}

    //override fun onCreateView(
    //    inflater: LayoutInflater, container: ViewGroup?,
    //    savedInstanceState: Bundle?
    //): View? {

        //_binding = FragmentItemListBinding.inflate(inflater, container, false)
       // initRecView()
       // binding.clFragmentItemList.isVisible = false

      //  return binding.root
    //}

   // private fun initRecView() {
    //    itemAdapter = DetailItemFragment(items)
    //    binding.rvItems.adapter = itemAdapter

    //    layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    //    binding.rvItems.layoutManager = layoutManager
   // }
    private fun addToFavoriteItem(pos : Int){
    }

   // override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    //    super.onViewCreated(view, savedInstanceState)
    //}
}