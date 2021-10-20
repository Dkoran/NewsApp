package com.sample.newsapp.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.sample.newsapp.NewsActivity
import com.sample.newsapp.R
import com.sample.newsapp.viewmodels.NewsViewModel
import kotlinx.android.synthetic.main.fragment_article.*

class NewsArticleFragment : Fragment (R.layout.fragment_article) {
    lateinit var viewModel :NewsViewModel

    val args: NewsArticleFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =(activity as NewsActivity).viewmodel
        val article = args.article

        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url!!)
        }

    }
}