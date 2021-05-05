package com.example.projetofinal.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projetofinal.Interface.ItemClickListener
import com.example.projetofinal.Model.Article
import com.example.projetofinal.R
import com.example.projetofinal.SourceSite
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_layout.view.*
import java.text.ParseException
import java.util.*

class ListNewsAdapter(val context: Context, val articleList: MutableList<Article>):
    RecyclerView.Adapter<ListNewsAdapter.ListNewsViewHolder>() {

    class ListNewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private lateinit var itemClickListener: ItemClickListener

        var article_title = itemView.article_title
        var article_image = itemView.article_image
        var article_publishedAt = itemView.article_time

        init {
            itemView.setOnClickListener(this)
        }

        fun setItemClickListener(itemClickListener: ItemClickListener) {
            this.itemClickListener = itemClickListener
        }

        override fun onClick(v: View?) {
            itemClickListener.onClick(v!!, adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListNewsViewHolder {
        return ListNewsViewHolder(LayoutInflater.from(context).inflate(R.layout.news_layout, parent, false))
    }
    override fun onBindViewHolder(holder: ListNewsViewHolder, position: Int) {
        Picasso.get().load(articleList[position].urlToImage).into(holder.article_image)

        holder.article_publishedAt.text = articleList[position].publishedAt!!
        holder.article_title.text = articleList[position].title!!

        holder.setItemClickListener(object : ItemClickListener {
            override fun onClick(view: View, position: Int) {
                val details = Intent(context, SourceSite::class.java)
                details.putExtra("webURL", articleList[position].url)
                details.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(details)
            }
        })
    }

    override fun getItemCount(): Int {
        return articleList.size
    }


}