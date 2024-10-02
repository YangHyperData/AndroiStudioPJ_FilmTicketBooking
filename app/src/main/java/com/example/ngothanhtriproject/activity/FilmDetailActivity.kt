package com.example.ngothanhtriproject.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.RenderScript
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.ngothanhtriproject.Adapter.CastListAdapter
import com.example.ngothanhtriproject.Adapter.CategoryEachFilmAdapter
import com.example.ngothanhtriproject.Model.Film
import com.example.ngothanhtriproject.R
import com.example.ngothanhtriproject.databinding.ActivityFilmDetailBinding

class FilmDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFilmDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilmDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        setVariable()
    }

    private fun setVariable() {
        val item: Film = intent.getParcelableExtra("object")!!
        val requestOptions =
            RequestOptions().transform(CenterCrop(), GranularRoundedCorners(0f, 0f, 50f, 50f))
        Glide.with(this)
            .load(item.Poster)
            .apply(requestOptions)
            .into(binding.FilmPic)

        binding.titleTxt.text = item.Title
        binding.imdbTxt.text = "IMDB ${item.Imdb}"
        binding.movieTimeTxt.text = "${item.Year} - ${item.Time}"
        binding.movieSumaryTxt.text = item.Description

        binding.backBtn.setOnClickListener {
            finish()
        }
        binding.buyTicketBtn.setOnClickListener {
            val intent = Intent(this, SeatListActivity::class.java)
            intent.putExtra("film", item)
            startActivity(intent)
        }


        binding.blurView.outlineProvider = ViewOutlineProvider.BACKGROUND
        binding.blurView.clipChildren = true

        item.Genre?.let {
            binding.genreView.adapter = CategoryEachFilmAdapter(it)
            binding.genreView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        }

        item.Casts?.let {
            binding.castListView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            binding.castListView.adapter = CastListAdapter(it)
        }
    }
}