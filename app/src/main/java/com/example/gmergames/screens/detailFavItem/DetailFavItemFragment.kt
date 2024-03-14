package com.example.gmergames.screens.detailFavItem

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
import com.example.gmergames.databinding.FragmentDetailFavItemBinding
import kotlinx.coroutines.launch

class DetailFavItemFragment : Fragment() {
    private var _binding: FragmentDetailFavItemBinding? = null
    private val binding get() = _binding!!

    val args: DetailFavItemFragmentArgs by navArgs()

    private val detailFavItemVM: DetailFavItemVM by viewModels<DetailFavItemVM> { DetailFavItemVM.Factory }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailFavItemBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailFavItemVM.setId(args.id)
        detailFavItemVM.setGame()

        setCollectors()
        setListeners()
    }

    private fun setListeners() {
        binding.btnPaTras.setOnClickListener {
            val action = DetailFavItemFragmentDirections.actionDetailFavItemFragmentToFavItemListFragment()
            findNavController().navigate(action)
        }
    }

    private fun setCollectors() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                detailFavItemVM.uiState.collect { itemState ->
                    if (!itemState.isLoading) {
                        binding.pbLoadingDetailFavItem.isVisible = false
                        itemState.game?.let {
                            binding.tvName.text = itemState.game?.name ?: ""
                            Glide.with(requireContext()).load(it.photo).into(binding.ivPhoto)
                            binding.tvName.text = it.name
                            binding.tvGenre.text = it.genre
                            binding.tvSummary.text = it.summary
                        }
                    } else {
                        binding.pbLoadingDetailFavItem.isVisible = true
                    }
                }
            }
        }
    }
}