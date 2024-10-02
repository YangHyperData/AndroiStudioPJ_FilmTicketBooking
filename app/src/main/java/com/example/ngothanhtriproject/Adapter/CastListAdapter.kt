package com.example.ngothanhtriproject.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ngothanhtriproject.Model.Cast
import com.example.ngothanhtriproject.databinding.ViewholderCastBinding

class CastListAdapter(private val cast: ArrayList<Cast>) :
    RecyclerView.Adapter<CastListAdapter.Viewholder>() {
    private var context: Context? = null

    inner class Viewholder(private val binding: ViewholderCastBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cast: Cast) {
            context?.let {
                Glide.with(it)
                    .load(cast.PicUrl)
                    .into(binding.actorimage)
            }
            binding.nameTxt.text = cast.Actor
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastListAdapter.Viewholder {
        context=parent.context
        val binding =
            ViewholderCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: CastListAdapter.Viewholder, position: Int) {
        holder.bind(cast[position])
    }

    override fun getItemCount(): Int {
        return cast.size
    }
}