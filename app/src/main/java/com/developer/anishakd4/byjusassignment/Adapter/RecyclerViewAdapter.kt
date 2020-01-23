package com.developer.anishakd4.byjusassignment.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.developer.anishakd4.byjusassignment.Model.ArticlesModel
import com.developer.anishakd4.byjusassignment.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*

class RecyclerViewAdapter(val news: List<ArticlesModel>, val clickInterFace: ClickInterFace): RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

    interface ClickInterFace {
        fun onClickedInAdapter(position: Int);

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        (holder as RecyclerViewHolder).bind(news.get(position))
        (holder as RecyclerViewHolder).parent.setOnClickListener {
            if(clickInterFace != null){
                clickInterFace.onClickedInAdapter(position)
            }
        }
    }

    class RecyclerViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val title: TextView = view.title
        val sourceName: TextView = view.source_name
        val publishedAt: TextView = view.published_at
        val imageView: ImageView = view.news_image
        val parent: ConstraintLayout = view.parent_cons

        fun bind(currentNews: ArticlesModel) {
            title.text = currentNews.title
            sourceName.text = currentNews.source.name
            publishedAt.text = currentNews.publishedAt
            Picasso.get().load(currentNews.urlToImage).into(imageView)
        }
    }

    override fun getItemCount(): Int {
        if(news != null){
            return news.size;
        }
        return 0
    }


}
