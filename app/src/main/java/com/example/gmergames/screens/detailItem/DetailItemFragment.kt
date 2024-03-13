package com.example.gmergames.screens.detailItem

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
import com.example.gmergames.databinding.FragmentDetailItemBinding
import kotlinx.coroutines.launch

class DetailItemFragment : Fragment() {
    private var _binding: FragmentDetailItemBinding? = null
    private val binding get() = _binding!!

    val args: DetailItemFragmentArgs by navArgs()

    private val detailItemVM: DetailItemVM by viewModels<DetailItemVM> { DetailItemVM.Factory }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailItemBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailItemVM.setId(args.id)
        detailItemVM.setGame()

        setCollectors()
        setListeners()
    }

    private fun setListeners() {
        binding.btnIrAtras.setOnClickListener {
            val action = DetailItemFragmentDirections.actionDetailItemFragmentToItemListFragment()
            findNavController().navigate(action)
        }
    }

    private fun setCollectors() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                detailItemVM.uiState.collect { itemState ->
                    if (!itemState.isLoading) {
                        binding.pbLoadingDetailItem.isVisible = false
                        itemState.game?.let {
                            binding.tvName.text = itemState.game?.name ?: ""
                            Glide.with(requireContext()).load(it.photo).into(binding.ivPhoto)
                            binding.tvGenre.text = it.genre.toString()
                            binding.tvSummary.text = it.summary
                            binding.rbRating.rating = it.rating.toFloat()/20
                        }
                    } else {
                        binding.pbLoadingDetailItem.isVisible = true
                    }
                }
            }
        }
    }
}