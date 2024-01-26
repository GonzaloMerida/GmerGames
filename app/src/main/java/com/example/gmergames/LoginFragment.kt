// LoginFragment.kt
package com.example.gmergames

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.gmergames.databinding.FragmentLoginBinding
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //btnBegin.setOnClickListener {
            //val userName = editTextUserName.text.toString()
            //if (userName.isEmpty()) {
             //   showErrorSnackbar(editTextUserName, getString(R.string.errorSnackbar))
            //} else {
             //   goToCredit(userName)
            //}
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.btnEnter.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_menuFragment)
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
            Snackbar.make(requireView(), "FloatingActionButton clicked", Snackbar.LENGTH_SHORT).show()
        }

        return binding.root
    }

    ///**
     //* El parámetro view es la vista donde se mostrará el snackBar
     //* El parámetro message, es un string con el mensaje de error que se le mostrará en el
     //* snackBar
     //*/
    //private fun showErrorSnackbar(view: View, message: String) {
        //creación del objeto Snackbar indicando la vista en la que se mostrará
        //el mensaje que mostrará y la duración del snackBar
     //   val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        //muestra el snackbar
    //    snackbar.show()

        // Ocultar el teclado virtual
        //obtenemos un objeto del teclado
    //    val inputMethodManager = getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        //ocultar el teclado de la ventana en la que se encuentre y el 0 indica que no se debe
        //forzar cambios en el teclado
    //       inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
   // }

}
