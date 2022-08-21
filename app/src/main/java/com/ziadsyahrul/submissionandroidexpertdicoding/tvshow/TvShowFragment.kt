package com.ziadsyahrul.submissionandroidexpertdicoding.tvshow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ziadsyahrul.submissionandroidexpertdicoding.R
import com.ziadsyahrul.submissionandroidexpertdicoding.core.Resource
import com.ziadsyahrul.submissionandroidexpertdicoding.databinding.FragmentMovieBinding
import com.ziadsyahrul.submissionandroidexpertdicoding.databinding.FragmentTvShowBinding
import com.ziadsyahrul.submissionandroidexpertdicoding.detail.DetailCatalogActivity
import com.ziadsyahrul.submissionandroidexpertdicoding.domain.model.Catalog
import com.ziadsyahrul.submissionandroidexpertdicoding.ui.CatalogAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class TvShowFragment : Fragment() {

    private val tvViewModel: TvShowViewModel by viewModel()

    private lateinit var catalogAdapter: CatalogAdapter
    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        catalogAdapter = CatalogAdapter()
        setList()

    }

    private val tvShowObserver = Observer<Resource<List<Catalog>>> { tvShow ->
        if (tvShow != null){
            val tvshowAdapter = CatalogAdapter()
            tvshowAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailCatalogActivity::class.java)
                intent.putExtra("DETAIL_CATALOG", selectedData)
                startActivity(intent)
            }

            tvViewModel.getTvShow().observe(viewLifecycleOwner) { tvShow ->
                if (tvShow != null) {
                    when (tvShow) {
                        is Resource.Loading -> binding.progressBarTvShow.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBarTvShow.visibility = View.GONE
                            tvshowAdapter.setDataAdapter(tvShow.data)
                        }
                        is Resource.Error -> {
                            binding.progressBarTvShow.visibility = View.GONE
                            binding.viewErrorTv.root.visibility = View.VISIBLE
                            binding.viewErrorTv.tvError.text =
                                tvShow.message ?: getString(R.string.wrong_text)
                        }
                    }
                }
            }
            with(binding.rvTv){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvshowAdapter
            }
        }
    }


    private fun setList() {
        tvViewModel.getTvShow().observe(viewLifecycleOwner, tvShowObserver)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}