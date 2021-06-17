package com.ziadsyahrul.submissionandroidexpertdicoding.ui

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ziadsyahrul.submissionandroidexpertdicoding.R
import com.ziadsyahrul.submissionandroidexpertdicoding.tvshow.FavoriteTvShowFragment
import com.ziadsyahrul.submissionandroidexpertdicoding.movie.FavoriteMovieFragment

class FavoritePagerAdapter(private val context: Context, fm: FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    companion object{
        private val TABS_TITLE  = intArrayOf(R.string.movie, R.string.tv_show)
    }

    override fun getCount(): Int = TABS_TITLE.size

    override fun getItem(position: Int): Fragment =
        when(position){
            0 -> FavoriteMovieFragment()
            1 -> FavoriteTvShowFragment()
            else -> FavoriteMovieFragment()
        }

    override fun getPageTitle(position: Int): CharSequence? = context.resources.getString(TABS_TITLE[position])

}