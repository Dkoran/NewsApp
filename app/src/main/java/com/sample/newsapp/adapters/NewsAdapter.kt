package com.sample.newsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sample.newsapp.R
import com.sample.newsapp.models.ArticlesItem
import kotlinx.android.synthetic.main.item_news_card.view.*

class NewsAdapter :RecyclerView.Adapter<NewsAdapter.ArticleViewHolder> (){

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
            Glide.with(this).load(articlesItem.urlToImage).into(image)
            date.text = articlesItem.publishedAt
            author.text = articlesItem.source?.name
            title.text = articlesItem.title
            setOnitemClickListener {
                itemClickListener?.let { it (articlesItem) }
            }


        }
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }


    private  var itemClickListener: ((ArticlesItem)-> Unit)? = null

   private fun setOnitemClickListener(listener:(ArticlesItem)->Unit){
        itemClickListener = listener
    }
}