package com.example.gmergames.screens.userInfo

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
import com.example.gmergames.databinding.FragmentUserInfoBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class UserInfoFragment : Fragment() {
    private lateinit var binding: FragmentUserInfoBinding

    private val userInfoVM : UserInfoVM by viewModels<UserInfoVM> { UserInfoVM.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserInfoBinding.inflate(inflater, container, false)

        setListeners()


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCollectors()

    }

    private fun setCollectors() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                userInfoVM.uiState.collect { userPreferences ->
                    binding.etName.setText(userPreferences.name)
                    if(userPreferences.showCheckBox){
                        binding.cbShowCheckBox.isChecked
                    }
                    else{
                        !binding.cbShowCheckBox.isChecked
                    }
                }
            }
        }
    }

    private fun setListeners(){
        binding.btnSaveUserPrefs.setOnClickListener {
            validateName(binding.etName.text.toString())
            val action = UserInfoFragmentDirections.actionUserInfoFragmentToMenuFragment()
            findNavController().navigate(action)
        }
    }

    private fun validateName(name: String) {
        if(name.isBlank())
            Snackbar.make(requireView(),getString(R.string.word_is_empty), Snackbar.LENGTH_SHORT).show()
        else
            userInfoVM.saveUserPrefs(name, setCheckBox())
    }

    private fun setCheckBox() : Boolean {
        return when{
            binding.cbShowCheckBox.isChecked -> UserPreferences.SHOW_CHECKBOX
            else -> !UserPreferences.SHOW_CHECKBOX
        }
    }
}