package com.ziadsyahrul.submissionandroidexpertdicoding.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ziadsyahrul.submissionandroidexpertdicoding.R
import com.ziadsyahrul.submissionandroidexpertdicoding.core.Resource
import com.ziadsyahrul.submissionandroidexpertdicoding.databinding.FragmentMovieBinding
import com.ziadsyahrul.submissionandroidexpertdicoding.detail.DetailCatalogActivity
import com.ziadsyahrul.submissionandroidexpertdicoding.domain.model.Catalog
import com.ziadsyahrul.submissionandroidexpertdicoding.ui.CatalogAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
@FlowPreview
class MovieFragment : Fragment() {

    private val movieViewModel: MovieViewModel by viewModel()
    private lateinit var catalogAdapter: CatalogAdapter

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        catalogAdapter = CatalogAdapter()
        setList()

        with(binding.rvMovie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = catalogAdapter
        }

        catalogAdapter.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailCatalogActivity::class.java)
            intent.putExtra("DETAIL_CATALOG", selectedData)
            startActivity(intent)
        }

    }

    private val movieObserver =
        Observer<Resource<List<Catalog>>> { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Loading -> binding.progressBarMovie.visibility =
                        View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBarMovie.visibility = View.GONE
                        catalogAdapter.setDataAdapter(movie.data)
                    }
                    is Resource.Error -> {
                        binding.progressBarMovie.visibility = View.GONE
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text =
                            movie.message ?: getString(R.string.wrong_text)
                    }
                }
            }

        }


    private fun setList() {
        movieViewModel.getMovie().observe(viewLifecycleOwner, movieObserver)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}