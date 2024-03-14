// MenuFragment.kt
package com.example.gmergames.screens.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.gmergames.R
import com.example.gmergames.databinding.FragmentMenuBinding
import kotlinx.coroutines.launch

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    val args: MenuFragmentArgs by navArgs()

    private val menuVM: MenuVM by viewModels<MenuVM> { MenuVM.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        menuVM.setName(args.name)
        setListeners()
        setCollectors()
    }

    private fun setListeners() {
        binding.btnExit.setOnClickListener {
            val action = MenuFragmentDirections.actionMenuFragmentToLoginFragment()
            findNavController().navigate(action)
        }
        binding.btnInitiate.setOnClickListener {
            val action = MenuFragmentDirections.actionMenuFragmentToItemListFragment()
            findNavController().navigate(action)
        }
        binding.btnUserInfo.setOnClickListener {
            val action = MenuFragmentDirections.actionMenuFragmentToUserInfoFragment()
            findNavController().navigate(action)
        }
    }

    private fun setCollectors() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                menuVM.uiState.collect { itemState ->
                    itemState.copy().let {newState ->
                        binding.tvInfo.text = newState.name
                    }
                }
            }
        }
    }
}

