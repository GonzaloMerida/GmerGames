package com.example.gmergames.screens.favItemList

import android.annotation.SuppressLint
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
import com.example.gmergames.databinding.FragmentFavItemListBinding
import com.example.gmergames.screens.itemList.ItemListFragmentDirections
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class FavItemListFragment : Fragment() {
    private var _binding: FragmentFavItemListBinding? = null
    private val binding get() = _binding!!

    private val favItemListVM : FavItemListVM by viewModels<FavItemListVM> { FavItemListVM.Factory }

    private lateinit var gameAdapter: GameAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavItemListBinding.inflate(inflater, container, false)

        binding.tvItemListHead.text = getString(R.string.fav_games_head)



        return binding.root
    }

    private fun initRecView() {
        gameAdapter = GameAdapter(
            mutableListOf(),
            onClickGame = { id:Int -> selectGame(id)},
            onClickDelete = { pos -> confirmDeleteItemFromFav(pos)},
            onClickFav = { pos -> delItemFromFav(pos)}
        )
        binding.rvFavItems.adapter = gameAdapter

        binding.rvFavItems.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)
    }

    private fun selectGame(id: Int) {
        val action = FavItemListFragmentDirections.actionFavItemListFragmentToDetailFavItemFragment(id)
        findNavController().navigate(action)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecView()
        setListeners()
        setCollectors()
    }

    private fun setListeners() {
        binding.btnVolver.setOnClickListener {
            val action = FavItemListFragmentDirections.actionFavItemListFragmentToItemListFragment()
            findNavController().navigate(action)
        }
        binding.btnDelFromFavs.setOnClickListener {
        val gameToDelete =
        //Lo hace ya el gameAdapter con onClickDelete?
            //Si quiero que lo haga también el botón btnDelFromFavs?
        }
    }

    private fun setCollectors() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                favItemListVM.uiState.collect { gameState ->
                    if(!gameState.isLoading) {
                        binding.pbLoadingFavItemList.isVisible = false
                        gameAdapter.setItemList(gameState.gameList)
                        gameAdapter.notifyDataSetChanged()
                    }else {
                        binding.pbLoadingFavItemList.isVisible = true
                    }
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                favItemListVM.uiState.collect{gameState ->
                    if(gameState.delFromFav){
                        Snackbar.make(requireView(), R.string.game_deleted_from_fav, Snackbar.LENGTH_SHORT).show()
                        favItemListVM.gameDeleted()
                    }
                    else{
                        Snackbar.make(requireView(), R.string.game_not_deleted_from_favs, Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    //borra un juego de la lista y notifica al adapter.
    private fun delItemFromFav(pos : Int){
        favItemListVM.deleteGameFromFav(pos)
        gameAdapter.notifyItemInserted(pos)
    }
    //Dialogo de confirmación del borrado.
    @SuppressLint("StringFormatMatches")
    private fun confirmDeleteItemFromFav(pos : Int) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(resources.getString(R.string.delete))
            .setMessage(resources.getString(R.string.support_confirm_delete_from_fav,favItemListVM.uiState.value.gameList[pos].name))
            .setNegativeButton(resources.getString(R.string.cancel)) { dialog, which ->
                // Respond to negative button press
            }
            .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                // Respond to positive button press
                delItemFromFav(pos)
            }
            .show()
    }
}