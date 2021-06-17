package com.ziadsyahrul.submissionandroidexpertdicoding.tvshow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ziadsyahrul.submissionandroidexpertdicoding.FavoriteViewModel
import com.ziadsyahrul.submissionandroidexpertdicoding.detail.DetailCatalogActivity
import com.ziadsyahrul.submissionandroidexpertdicoding.favorite.R
import com.ziadsyahrul.submissionandroidexpertdicoding.favorite.databinding.FragmentFavoriteTvShowBinding
import com.ziadsyahrul.submissionandroidexpertdicoding.favorite.databinding.FragmentMovieFavoriteBinding
import com.ziadsyahrul.submissionandroidexpertdicoding.ui.CatalogAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteTvShowFragment : Fragment() {


    private var _favoriteTvBinding: FragmentFavoriteTvShowBinding? = null
    private val binding get() = _favoriteTvBinding!!
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var adapter: CatalogAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _favoriteTvBinding = FragmentFavoriteTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CatalogAdapter()


        adapter = CatalogAdapter()
        if (activity != null) {
            val catalogAdapter = CatalogAdapter()
            catalogAdapter.onItemClick = { selectData ->
                val intent = Intent(activity, DetailCatalogActivity::class.java)
                intent.putExtra("DETAIL_CATALOG", selectData)
                startActivity(intent)
            }
            favoriteViewModel.tvShowFavorite.observe(viewLifecycleOwner, { dataTvShow ->
                catalogAdapter.setDataAdapter(dataTvShow)
                binding.tvNodata.visibility =
                    if (dataTvShow.isNotEmpty()) View.GONE else View.VISIBLE
                binding.progressBarTvFavorite.visibility =
                    if (dataTvShow.isNotEmpty()) View.GONE else View.GONE
            })
            with(binding.rvTvFavorite) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = catalogAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
         _favoriteTvBinding = null
    }

}