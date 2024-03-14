// LoginFragment.kt
package com.example.gmergames.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.gmergames.R
import com.example.gmergames.dataStore.UserPreferences
import com.example.gmergames.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val loginVM: LoginVM by viewModels<LoginVM> { LoginVM.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setListeners()
        setCollectors()
    }

    private fun setListeners() {
        binding.btnEnter.setOnClickListener {
            var nameEntered = validateName(binding.etUser.text.toString())
            val action = LoginFragmentDirections.actionLoginFragmentToMenuFragment(nameEntered.toString())
            findNavController().navigate(action)
        }

        binding.bottomAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_discover -> {
                    findNavController().navigate(R.id.action_loginFragment_to_itemListFragment)
                    true
                }

                R.id.action_favs -> {
                    findNavController().navigate(R.id.action_loginFragment_to_favItemListFragment)
                    true
                }

                R.id.action_user_info -> {
                    findNavController().navigate(R.id.action_loginFragment_to_userInfoFragment)
                    true
                }

                else -> false
            }
        }

        binding.fab.setOnClickListener {
            // Handle FloatingActionButton click here
            Snackbar.make(requireView(), "FloatingActionButton clicked", Snackbar.LENGTH_SHORT)
                .show()
        }
    }

    private fun setCollectors() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginVM.uiState.collect { itemState ->
                    itemState.copy().let {
                        binding.etUser.setText(itemState.name)
                    }
                }
            }
        }
    }

    private fun validateName(name: String) {
        if(name.isBlank())
            Snackbar.make(requireView(),getString(R.string.word_is_empty), Snackbar.LENGTH_SHORT).show()
        else
            loginVM.saveUserPrefs(name, loginVM.uiState.value.showCheckBox)
    }
}




