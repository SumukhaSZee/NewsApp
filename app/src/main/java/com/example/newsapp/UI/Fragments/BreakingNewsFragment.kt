
package com.example.newsapp.UI.Fragments
/*
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.Adapters.NewsAdapter
import com.example.newsapp.Adapters.OnItemClickListener
import com.example.newsapp.Models.Article
import com.example.newsapp.R
import com.example.newsapp.ViewModel.NewsViewModel
import com.example.newsapp.Util.Resource
import com.example.newsapp.databinding.BrNewsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel


class BreakingNewsFragment :Fragment(R.layout.br_news),OnItemClickListener {


    private val viewModel by viewModel<NewsViewModel>()
    private lateinit var newsAdapter: NewsAdapter
    lateinit var brNewsBinding: BrNewsBinding
    var TAG = "BreakingNewsFragment"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        brNewsBinding = BrNewsBinding.inflate(inflater, container, false)
        brNewsBinding.lifecycleOwner = viewLifecycleOwner // for fragments
        val view = brNewsBinding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupRecyclerView()

        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.breakingNews.collect { response ->
                when (response) {
                    is Resource.Success -> {
                        withContext(Dispatchers.Main){
                            hideprogressbar()
                            response.data?.let { newsResponse ->
                                val filteredArticles = newsResponse.articles.filterNotNull()
                                newsAdapter.differ.submitList(filteredArticles)
                        }

                        }
                    }
                    is Resource.Error -> {
                        withContext(Dispatchers.Main){
                            hideprogressbar()
                            response.message?.let { message ->
                                Log.d(TAG, "An Error Occured $message")
                            }
                        }

                    }
                    is Resource.Loading -> {
                        withContext(Dispatchers.Main){
                            showprogressbar()
                        }

                    }
                }
            }
        }



    }

    private fun hideprogressbar() {
         brNewsBinding.paginationProgressBar.visibility = View.INVISIBLE
    }
    private fun showprogressbar() {
         brNewsBinding.paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView(){
            newsAdapter = NewsAdapter(this)
            brNewsBinding.rvBreakingNews.apply {
                adapter = newsAdapter
                layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,true)
            }
        }

    override fun onItemClick(article: Article) {
        // Ensure that the 'article' object is not null before using it
        if (article != null) {
            val bundle = Bundle()
            bundle.putString("url",article.url)
            findNavController().navigate(
                R.id.action_breakingNewsFragment2_to_articleFragment2,
                bundle
            )
        } else {
            // Handle the case where 'article' is null
            // You can log an error message or take appropriate action here
            Log.d(TAG,"Error in receiving article")
            Toast.makeText(context, "Article is null", Toast.LENGTH_SHORT).show()
        }
    }

}





*/
