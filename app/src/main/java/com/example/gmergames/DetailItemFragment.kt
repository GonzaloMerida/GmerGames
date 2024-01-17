package com.example.gmergames

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.gmergames.databinding.FragmentDetailItemBinding
import com.example.gmergames.databinding.FragmentFavItemListBinding

class DetailItemFragment : Fragment() {
    private var _binding: FragmentDetailItemBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailItemBinding.inflate(inflater, container, false)

        binding.btnIrAtras.setOnClickListener {
            findNavController().navigate(R.id.action_detailItemFragment_to_itemListFragment)
        }
        return binding.root
    }
}