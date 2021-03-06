package com.developer.anishakd4.byjusassignment.Fragment.ListFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.developer.anishakd4.byjusassignment.Database.NewsDao
import com.developer.anishakd4.byjusassignment.Model.ArticlesModel
import com.developer.anishakd4.byjusassignment.Model.NewsModel
import com.developer.anishakd4.byjusassignment.Networking.GetNewsService
import kotlinx.coroutines.*

class ListFragmentViewModel(val database: NewsDao) : ViewModel(){

    val getData = GetNewsService.getGetDataService()

    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        //onError("Exception: ${throwable.localizedMessage}")
    }

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var news = MutableLiveData<NewsModel>()
    val loadError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

    var news2: LiveData<List<ArticlesModel>>

    init {
        news2 = database.getAllNews()
    }

    fun fetchNews(){
        refreshSlots()
    }

    fun refreshSlots(){
        loading.value = true

        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            Log.i("anisham", "anisham start")
            val response = getData.getNews()
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    news.value = response.body()
                    Log.i("anisham", "anisham success")
                    loadError.value = null
                    loading.value = false
                }else{
                    Log.i("anisham", "anisham else")
                    onError("Error ${response.message()}")
                }
            }
        }
    }

    fun haveData(){
        loadError.value = null
        loading.value = false
    }

    fun insertIntoDb(){
        uiScope.launch {
            withContext(Dispatchers.IO){
                val value = news.value
                if(value != null){
                    for (i in value.articles){
                        database.insert(i)
                    }
                }
            }
        }
    }

    private fun onError(message: String) {
        loadError.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
        viewModelJob.cancel()
    }

}