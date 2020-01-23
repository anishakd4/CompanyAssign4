package com.developer.anishakd4.byjusassignment.Fragment.DetailFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.developer.anishakd4.byjusassignment.Model.ArticlesModel
import com.developer.anishakd4.byjusassignment.R
import com.developer.anishakd4.byjusassignment.databinding.DetailFragmentBinding
import com.squareup.picasso.Picasso

class DetailFragment :Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: DetailFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false)

        val args = getArguments()
        if(args != null){
            val article = args.getParcelable<ArticlesModel>("article")
            Picasso.get().load(article?.urlToImage).into(binding.imageview)
            binding.title.text = article?.title ?: ""
            binding.description.text = article?.description ?: ""
            binding.source.text = article?.source?.name ?: ""
            binding.createdAt.text = article?.publishedAt ?: ""
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
    }

}