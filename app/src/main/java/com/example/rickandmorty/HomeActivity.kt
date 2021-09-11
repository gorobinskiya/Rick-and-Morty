package com.example.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.rickandmorty.characters.CharactersFragment
import com.example.rickandmorty.episodes.EpisodesFragment
import com.example.rickandmorty.locations.LocationFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private val episodesFragment = EpisodesFragment()
    private val locationFragment = LocationFragment()
    private val characterslistFragment = CharactersFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters_acivity)
        replaceFragment(characterslistFragment)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_char -> replaceFragment(characterslistFragment)
                R.id.nav_episodes -> replaceFragment(episodesFragment)
                R.id.nav_location -> replaceFragment(locationFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}