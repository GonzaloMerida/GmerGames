package com.example.gmergames.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gmergames.R
import com.example.gmergames.data.Item
import com.example.gmergames.databinding.FragmentDetailItemBinding

class GameAdapter(
    private val _itemList : MutableList<Item>,
    private val onClickGame : (Int) -> Unit,
    private val onClickDelete : (Int) -> Unit,
    private val onClickFav: (Int) -> Unit
) : RecyclerView.Adapter<GameAdapter.GameViewHolder>(){

    class GameViewHolder (view: View) : RecyclerView.ViewHolder(view){
        private val binding = FragmentDetailItemBinding.bind(view)

        fun bind(
            item : Item,
            onClickGame: (Int) -> Unit,
            onClickDelete: (Int) -> Unit,
            onClickFav : (Int) -> Unit
        ){
            binding.tvName.text = item.name
            binding.tvCompany.text = item.genre.toString()
            binding.tvDescription.text = item.summary

            val context = binding.ivPhoto.context
            Glide.with(context).load(item.photo).circleCrop().into(binding.ivPhoto)

            binding.root.setOnClickListener {
                onClickGame(item.id)
            }

            binding.ivDelItem.setOnClickListener {
                onClickFav(adapterPosition)
            }

            binding.ivFavItem.setOnClickListener {
                onClickDelete
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GameViewHolder(layoutInflater.inflate(R.layout.fragment_detail_item,parent,false))
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val hero = _itemList[position]
        holder.bind(hero, onClickGame, onClickDelete, onClickFav)
    }

    override fun getItemCount(): Int {
        return _itemList.size
    }

    fun setItemList(itemList : List<Item>){
        _itemList.clear()
        _itemList.addAll(itemList)
    }
}