package com.example.gmergames.screens.notice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.gmergames.R
import com.example.gmergames.databinding.FragmentTab2NoticeBinding
import kotlinx.coroutines.launch

class Tab2NoticeFragment : Fragment() {

    private var _binding: FragmentTab2NoticeBinding? = null
    private val binding
        get() = _binding!!

    private val tab2NoticeVM: Tab2NoticeVM by viewModels<Tab2NoticeVM> { Tab2NoticeVM.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTab2NoticeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
        setCollectors()

    }

    fun setListeners() {
        binding.cbShowCB.setOnClickListener {
            var optionSelected = binding.cbShowCB.isChecked
            tab2NoticeVM.saveUserPrefs(tab2NoticeVM.uiState.value.name, optionSelected)
        }
        binding.btnStart.setOnClickListener {
            val action =
                NoticeFragmentDirections.actionNoticeFragmentToMenuFragment(tab2NoticeVM.uiState.value.name)
            findNavController().navigate(action)
        }
    }

    private fun setCollectors() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                tab2NoticeVM.uiState.collect { userPreferences ->
                    binding.cbShowCB.isChecked = userPreferences.showCheckBox
                }
            }
        }
    }
}
