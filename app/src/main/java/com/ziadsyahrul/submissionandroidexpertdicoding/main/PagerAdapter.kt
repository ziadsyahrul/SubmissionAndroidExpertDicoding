package com.ziadsyahrul.submissionandroidexpertdicoding.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ziadsyahrul.submissionandroidexpertdicoding.R
import com.ziadsyahrul.submissionandroidexpertdicoding.movie.MovieFragment
import com.ziadsyahrul.submissionandroidexpertdicoding.tvshow.TvShowFragment

class PagerAdapter(private val mContext: Context, fm: FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    companion object{
        private val TABS_TITLE = intArrayOf(R.string.movie, R.string.tv_show)
    }

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment =
        when(position){
            0 -> MovieFragment()
            1 -> TvShowFragment()
            else -> MovieFragment()
        }

    override fun getPageTitle(position: Int): CharSequence? = mContext.resources.getString(
        TABS_TITLE[position])
}