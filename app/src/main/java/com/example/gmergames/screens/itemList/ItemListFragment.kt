package com.example.gmergames.screens.itemList

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gmergames.R
import com.example.gmergames.adapters.GameAdapter
import com.example.gmergames.databinding.FragmentItemListBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class ItemListFragment : Fragment(){
    private var _binding: FragmentItemListBinding? = null
    val binding
        get() = _binding!!

    private val gameListVM : ItemListVM by viewModels{ ItemListVM.Factory }

    private lateinit var gameAdapter: GameAdapter

    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentItemListBinding.inflate(inflater, container, false)

        binding.tvItemListHead.text = getString(R.string.item_list)

        return binding.root
    }

    private fun initRecView() {
       gameAdapter = GameAdapter(
           mutableListOf(),
           onClickGame = { id:Int -> selectGame(id)},
           onClickDelete = { pos -> confirmDeleteGame(pos)},
           onClickFav = {pos -> addItemToFav(pos)}
       )
        binding.rvItems.adapter = gameAdapter

        binding.rvItems.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)
    }

    private fun selectGame(id: Int) {
        val action = ItemListFragmentDirections.actionItemListFragmentToDetailItemFragment(id)
        findNavController().navigate(action)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecView()
        setListeners()
        setCollectors()
    }

    private fun setListeners() {
        binding.btnBack.setOnClickListener {
            val name = gameListVM.getName()
            val action = ItemListFragmentDirections.actionItemListFragmentToMenuFragment(name)
            findNavController().navigate(action)
        }

        binding.btnGoToFavs.setOnClickListener {
            val action = ItemListFragmentDirections.actionItemListFragmentToFavItemListFragment()
            findNavController().navigate(action)
        }
    }

    private fun setCollectors() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                gameListVM.uiState.collect { gameState ->
                    if(!gameState.isLoading) {
                        binding.pbLoading.isVisible = false
                        gameAdapter.setItemList(gameState.gameList)
                        gameAdapter.notifyDataSetChanged()
                    }else {
                        binding.pbLoading.isVisible = true
                    }
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                gameListVM.uiState.collect{gameState ->
                    if(gameState.addedToFav){
                        Snackbar.make(requireView(), R.string.game_added_to_favs, Snackbar.LENGTH_SHORT).show()
                        gameListVM.gameAdded()
                    }
                    else{
                        Snackbar.make(requireView(),R.string.game_not_aded_to_favs, Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun addItemToFav(pos : Int){

        gameListVM.addItemToFav(pos)
        gameAdapter.notifyItemInserted(pos)

    }

    //Dialogo de confirmación del borrado.
    private fun confirmDeleteGame(pos : Int) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(resources.getString(R.string.delete))
            .setMessage(resources.getString(R.string.support_confirm_delete,gameListVM.uiState.value.gameList[pos].name))
            .setNegativeButton(resources.getString(R.string.cancel)) { dialog, which ->
                // Respond to negative button press
            }
            .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                // Respond to positive button press
                deleteGame(pos)
            }
            .show()
    }

    //borra un juego de la lista y notifica al adapter.
    private fun deleteGame(pos : Int) {
        gameListVM.deleteGame(pos)
        gameAdapter.notifyItemRemoved(pos)
    }
}