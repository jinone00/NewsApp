package com.example.part2.newapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.part2.newapp.databinding.ItemNewsBinding

class NewsAdapter(private val onClick: (String) -> Unit):ListAdapter<NewsModel, NewsAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemNewsBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: NewsModel){
            binding.titleTextViews.text = item.title
            binding.root.setOnClickListener {
                onClick(item.link)
            }


            Glide.with(binding.thumbnailImageView)
                .load(item.imageUrl)
                .into(binding.thumbnailImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object{
        val diffUtil = object: DiffUtil.ItemCallback<NewsModel>(){
            override fun areItemsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
                return oldItem === newItem // ===은 HashCode까지 비교한다.
            }

            override fun areContentsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
                return oldItem == newItem
            }

        }
    }


}