package com.sample.newsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sample.newsapp.R
import com.sample.newsapp.models.ArticlesItem
import kotlinx.android.synthetic.main.item_news_card.view.*

class NewsAdapter(private var listener: (ArticlesItem) -> Unit) :RecyclerView.Adapter<NewsAdapter.ArticleViewHolder> (){

    inner class ArticleViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)



    private val difutilCalback = object  :DiffUtil.ItemCallback<ArticlesItem>(){
        override fun areItemsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
        return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,difutilCalback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
       return ArticleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_news_card,parent,false))
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val articlesItem = differ.currentList[position]

        holder.itemView.apply {
            image.load(articlesItem.urlToImage) {
                crossfade(true)
                placeholder(R.drawable.image_12)
            }

            date.text = articlesItem.publishedAt
            author.text = articlesItem.source?.name
            title.text = articlesItem.title

            setOnClickListener {
                listener(articlesItem)
            }
        }
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }
}