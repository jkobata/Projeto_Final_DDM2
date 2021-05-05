package com.example.projetofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projetofinal.Adapter.ListNewsAdapter
import com.example.projetofinal.Common.Common
import com.example.projetofinal.Interface.NewsService
import com.example.projetofinal.Model.News
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_list_news.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PortalDeNoticias : AppCompatActivity() {

    var source = ""
    var webHotUrl: String? = ""

    lateinit var dialog: AlertDialog
    lateinit var mService: NewsService
    lateinit var adapter: ListNewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_news)

        mService = Common.newsService

        dialog = SpotsDialog.Builder().setContext(this).setCancelable(false).build()

        list_news.setHasFixedSize(true)
        list_news.layoutManager = LinearLayoutManager(this)

        if (intent != null) {
            source = intent.getStringExtra("source").toString()
            if (source.isNotEmpty()) {
                loadNews(false)
            }
        }
    }

    private fun loadNews(isRefreshed: Boolean) {
        if (!isRefreshed) {
            dialog.show()

            mService.getNewsFromSource(Common.getNewsAPI()).enqueue(object :
                Callback<News> {
                override fun onFailure(call: Call<News>, t: Throwable) {

                }

                override fun onResponse(call: Call<News>, response: Response<News>) {
                    dialog.dismiss()

                    webHotUrl = response.body()!!.articles!![0].url

                    val removeFirstItem = response.body()!!.articles

                    removeFirstItem!!.removeAt(0)

                    adapter = ListNewsAdapter(baseContext, removeFirstItem)
                    adapter.notifyDataSetChanged()
                    list_news.adapter = adapter
                }

            })
        }
        else {
            mService.getNewsFromSource(Common.getNewsAPI()).enqueue(object :
                Callback<News> {
                override fun onFailure(call: Call<News>, t: Throwable) {
                }
                override fun onResponse(call: Call<News>, response: Response<News>) {

                    webHotUrl = response.body()!!.articles!![0].url

                    val removeFirstItem = response.body()!!.articles

                    removeFirstItem!!.removeAt(0)

                    adapter = ListNewsAdapter(baseContext, removeFirstItem)
                    adapter.notifyDataSetChanged()
                    list_news.adapter = adapter
                }

            })
        }
    }
}