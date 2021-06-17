package com.ziadsyahrul.submissionandroidexpertdicoding.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ziadsyahrul.submissionandroidexpertdicoding.favorite.databinding.ActivityFavoriteBinding
import com.ziadsyahrul.submissionandroidexpertdicoding.injection.favoriteModule
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionPager = FavoritePagerAdapter(this, supportFragmentManager)
        binding.viewPagerFavorite.adapter = sectionPager
        binding.tabLayoutFavorite.setupWithViewPager(binding.viewPagerFavorite)

        supportActionBar?.elevation = 0f

        loadKoinModules(favoriteModule)

    }
}