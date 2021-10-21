package com.sample.newsapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.sample.newsapp.Repository.NewsRepository
import com.sample.newsapp.db.NewsDatabase
import com.sample.newsapp.ui.NewsViewModelProvider
import com.sample.newsapp.viewmodels.NewsViewModel
import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : AppCompatActivity() {
    lateinit var viewmodel : NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val newsRepository = NewsRepository(NewsDatabase(this))
        val viewModelProviderFactory = NewsViewModelProvider(newsRepository)
        viewmodel = ViewModelProvider(this,viewModelProviderFactory).get(NewsViewModel::class.java)

        val appBarConfiguration = AppBarConfiguration(setOf(R.id.breakingNewsFragment, R.id.newsArticleFragment,
            R.id.favoriteNewsFragment, R.id.searchNewsFragment))
        val navController by lazy { findNavController(R.id.newNavHostFragment) }
        NavigationUI.setupWithNavController(appBar, navController, appBarConfiguration)
        bottomNavigationView.setupWithNavController(navController)

    }
    fun showBottomNavigation()
    {

         bottomNavigationView.visibility = View.VISIBLE
    }

    fun hideBottomNavigation()
    {
         bottomNavigationView.visibility = View.GONE
    }

    fun showToolbar()
    {
         appBar.visibility = View.VISIBLE
    }

    fun hideToolbar()
    {
        appBar.visibility = View.GONE
    }
}