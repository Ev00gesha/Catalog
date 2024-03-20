package com.example.catalog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.catalog.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(CatalogFragment())
        bottomNav = binding.bottomNavigationView as BottomNavigationView
        bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.catalogMenu ->{
                    loadFragment(CatalogFragment())
                    true
                }
                R.id.addMenu -> {
                    loadFragment(AddFragment())
                    true
                }

                else -> {true}
            }
        }

        binding.removeLayout.setOnClickListener {
            val handler = ServiceHandler(this)
            handler.clearAllData()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_place, CatalogFragment.newInstance())
                .commit()
        }
    }
    fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_place,fragment)
        transaction.commit()
    }
}