package com.example.gmergames

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.example.gmergames.databinding.FragmentDetailFavItemBinding

class DetailFavItemFragment : Fragment() {
    private var _binding: FragmentDetailFavItemBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailFavItemBinding.inflate(inflater, container, false)

        binding.btnPaTras.setOnClickListener {
            findNavController().navigate(R.id.action_detailFavItemFragment_to_favItemListFragment)
        }
        return binding.root
    }
}