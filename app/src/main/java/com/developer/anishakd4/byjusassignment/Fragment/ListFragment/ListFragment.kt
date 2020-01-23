package com.developer.anishakd4.byjusassignment.Fragment.ListFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.developer.anishakd4.byjusassignment.Adapter.RecyclerViewAdapter
import com.developer.anishakd4.byjusassignment.Database.NewsDatabase
import com.developer.anishakd4.byjusassignment.Fragment.DetailFragment.DetailFragment
import com.developer.anishakd4.byjusassignment.Model.ArticlesModel
import com.developer.anishakd4.byjusassignment.R
import com.developer.anishakd4.byjusassignment.databinding.ListFragmentBinding

class ListFragment : Fragment(), RecyclerViewAdapter.ClickInterFace {

    lateinit var newsList: List<ArticlesModel>

    override fun onClickedInAdapter(position: Int) {
        val detailFragment = DetailFragment()
        val args = Bundle()
        args.putParcelable("article", newsList.get(position))
        detailFragment.setArguments(args)
        val manager = fragmentManager
        val transaction = manager?.beginTransaction()
        transaction?.replace(R.id.frame_container, detailFragment)
        transaction?.addToBackStack("detailFragment")
        transaction?.commit()
    }

    lateinit var viewModel: ListFragmentViewModel
    lateinit var binding: ListFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.list_fragment, container, false)

        val dataSource = NewsDatabase.getInstance(context!!.applicationContext).newsDao
        val viewModelFactory = ListFragmentViewModelFactory(dataSource)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListFragmentViewModel::class.java)

        viewModel.fetchNews()

        observeViewModel()

        return binding.root
    }

    fun observeViewModel() {
        viewModel.news.observe(this, Observer {
            if(it != null){
                viewModel.insertIntoDb()
            }
        })

        viewModel.news2.observe(this, Observer {
            if (it != null) {
                this.newsList = it
                binding.newsList.visibility = View.VISIBLE
                val adapter = RecyclerViewAdapter(it, this)
                binding.newsList.layoutManager = LinearLayoutManager(context)
                binding.newsList.adapter = adapter

                viewModel.haveData()
            }
        })

        viewModel.loadError.observe(this, Observer {isError ->
            binding.listError.visibility = if (isError == "" || isError == null) View.GONE else View.VISIBLE
        })

        viewModel.loading.observe(this, Observer {isLoading ->
            isLoading?.let {
                binding.loadingView.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    binding.listError.visibility = View.GONE
                    binding.newsList.visibility = View.GONE
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.show()
    }
}