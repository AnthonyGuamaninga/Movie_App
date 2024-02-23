package com.aaguamaninga.movie_app.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.aaguamaninga.movie_app.R
import com.aaguamaninga.movie_app.databinding.ActivityMainBinding
import com.aaguamaninga.movie_app.ui.adapters.MovieAdapter
import com.aaguamaninga.movie_app.ui.fragments.ListNowPlayingFragment
import com.aaguamaninga.movie_app.ui.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter = MovieAdapter()
    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()

    }

    private fun initListeners() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            val manager = supportFragmentManager

            when(item.itemId) {
                R.id.it_new -> {
                    val transaction = manager.beginTransaction()
                    transaction.replace(binding.frmContainer.id, ListNowPlayingFragment() )
                    transaction.commit()
                    true
                }
                /*
                R.id.it_popular -> {
                    val transaction = manager.beginTransaction()
                    transaction.replace(binding.frmContainer.id, FavoriteFragment())
                    transaction.commit()
                    true
                }*/
                else -> {
                    false
                }
            }
        }

    }


}