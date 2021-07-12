package com.ziadsyahrul.submissionandroidexpertdicoding.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.ziadsyahrul.submissionandroidexpertdicoding.core.databinding.ItemListBinding
import com.ziadsyahrul.submissionandroidexpertdicoding.domain.model.Catalog

class CatalogAdapter: RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder>() {

    private var listData = ArrayList<Catalog>()
    var onItemClick: ((Catalog) -> Unit)? = null

    fun setDataAdapter(newListData: List<Catalog>?){
        if (newListData == null) return

        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatalogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class CatalogViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Catalog){
            with(binding){
                Picasso.get().load("https://image.tmdb.org/t/p/w500/" + data.posterPath).into(imgList)
                tvTitle.text = data.title
                tvDate.text = data.releaseDate
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}