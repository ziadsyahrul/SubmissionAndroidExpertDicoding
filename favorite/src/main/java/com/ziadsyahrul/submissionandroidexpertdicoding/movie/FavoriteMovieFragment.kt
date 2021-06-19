package com.ziadsyahrul.favorite.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.LinearLayoutManager
import com.ziadsyahrul.favorite.FavoriteViewModel
import com.ziadsyahrul.favorite.databinding.FragmentMovieFavoriteBinding
import com.ziadsyahrul.submissionandroidexpertdicoding.detail.DetailCatalogActivity
import com.ziadsyahrul.submissionandroidexpertdicoding.ui.CatalogAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteMovieFragment : Fragment() {

    private var _favoriteMovieBinding: FragmentMovieFavoriteBinding? = null
    private val binding get() = _favoriteMovieBinding!!

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var adapter: CatalogAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _favoriteMovieBinding = FragmentMovieFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CatalogAdapter()
        if (activity != null) {
            val catalogAdapter = CatalogAdapter()
            catalogAdapter.onItemClick = { selectData ->
                val intent = Intent(activity, DetailCatalogActivity::class.java)
                intent.putExtra("DETAIL_CATALOG", selectData)
                startActivity(intent)
            }
            favoriteViewModel.movieFavorite.observe(viewLifecycleOwner, { dataMovie ->
                catalogAdapter.setDataAdapter(dataMovie)
                binding.tvNodata.visibility =
                    if (dataMovie.isNotEmpty()) View.GONE else View.VISIBLE
                binding.progressBarMovieFavorite.visibility =
                    if (dataMovie.isNotEmpty()) View.GONE else View.GONE
            })
            with(binding.rvMovieFavorite) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = catalogAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _favoriteMovieBinding = null
    }
}