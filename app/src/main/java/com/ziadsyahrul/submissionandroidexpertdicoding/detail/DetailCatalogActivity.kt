package com.ziadsyahrul.submissionandroidexpertdicoding.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.squareup.picasso.Picasso
import com.ziadsyahrul.submissionandroidexpertdicoding.R
import com.ziadsyahrul.submissionandroidexpertdicoding.databinding.ActivityDetailCatalogBinding
import com.ziadsyahrul.submissionandroidexpertdicoding.domain.model.Catalog
import org.koin.android.viewmodel.ext.android.viewModel

class DetailCatalogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailCatalogBinding
    private val detailViewModel: DetailCatalogViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCatalogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val detailMov = intent.getParcelableExtra<Catalog>("DETAIL_CATALOG")
        if (detailMov != null){
            showData(detailMov)
        }
    }

    private fun showData(catalog: Catalog) {
        with(binding){
            supportActionBar?.title = catalog.title
            tvDetailDate.text  = catalog.releaseDate
            tvDetailDesc.text = catalog.description

            Picasso.get().load("https://image.tmdb.org/t/p/w500/" + catalog.posterPath).into(imgDetail)

            var stateFavorite = catalog.isFavorite
            setFavorite(stateFavorite)
            binding.fab.setOnClickListener {
                stateFavorite = !stateFavorite
                detailViewModel.setCatalogFavorite(catalog, stateFavorite)
                detailViewModel.setTvCatalogFavorite(catalog, stateFavorite)
                setFavorite(stateFavorite)
            }
        }
    }

    private fun setFavorite(stateFavorite: Boolean) {
        if (stateFavorite){
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_24))
        }else{
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icfavorite_border_24))
        }
    }
}