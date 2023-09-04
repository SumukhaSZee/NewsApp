package com.example.newsapp.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.Models.Article
import com.example.newsapp.Models.NewsResponse
import com.example.newsapp.R
import com.example.newsapp.databinding.ArticlePreviewBinding



/*class NewsAdapter(private val mlist:List<Article>) : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {


    var TAG = "NewsAdapter"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_preview, parent, false)
        return ArticleViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d(TAG,"size of get items is :${mlist.size}")
        return 10
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = mlist[position]

        Glide.with(holder.itemView)
            .load(article.urlToImage)
            .into(holder.img)

        holder.apply {
            tvSource.text = article.source.name
            tvTitle.text = article.title
            tvDescription.text = article.description
            tvPublishedAt.text = article.publishedAt

            itemView.setOnClickListener {
                onItemClickListener?.invoke(article)
            }
        }
    }

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img: ImageView = itemView.findViewById(R.id.ivArticleImage)
        var tvSource: TextView = itemView.findViewById(R.id.tvSource)
        var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        var tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        var tvPublishedAt: TextView = itemView.findViewById(R.id.tvPublishedAt)
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }



}*/



class NewsAdapter() : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {



    private val differCallback = object :DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {

 /*return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.article_preview,
                parent,
                false)
        )*/

        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_preview,parent,false)
        return ArticleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.img.apply {
            Glide.with(this).
            load(article.urlToImage).into(holder.img)

            holder.apply {
                tvSource.text= article.source.name
                tvTitle.text= article.title
                tvDescription.text = article.description
                tvPublishedAt.text = article.publishedAt
                setOnClickListener{
                    onItemClickListener?.let{
                        it(article)
                    }
                }
            }
        }
    }

    class ArticleViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        var img:ImageView= itemView.findViewById(R.id.ivArticleImage)
        var tvSource :TextView = itemView.findViewById(R.id.tvSource)
        var tvTitle:TextView = itemView.findViewById(R.id.tvTitle)
        var tvDescription:TextView = itemView.findViewById(R.id.tvDescription)
        var tvPublishedAt:TextView = itemView.findViewById(R.id.tvPublishedAt)

    }

    private var onItemClickListener :  ((Article)->Unit)? = null

    fun setOnItemClickListener(listener:(Article)->Unit){
        onItemClickListener = listener
    }
}
