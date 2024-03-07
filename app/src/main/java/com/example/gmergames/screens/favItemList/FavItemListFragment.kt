package com.example.gmergames.screens.favItemList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.gmergames.R
import com.example.gmergames.databinding.FragmentFavItemListBinding

class FavItemListFragment : Fragment() {
    private var _binding: FragmentFavItemListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavItemListBinding.inflate(inflater, container, false)

        binding.btnVolver.setOnClickListener {
            findNavController().navigate(R.id.action_favItemListFragment_to_itemListFragment)
        }
        //binding.btnDelFromFavs.setOnClickListener {

        //}
        return binding.root
    }
}