// MenuFragment.kt
package com.example.gmergames.screens.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.gmergames.R
import com.example.gmergames.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)

        binding.btnExit.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_loginFragment)
        }
        binding.btnInitiate.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_itemListFragment)
        }
        binding.btnUserInfo.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_userInfoFragment)
        }
        return binding.root
    }

}
