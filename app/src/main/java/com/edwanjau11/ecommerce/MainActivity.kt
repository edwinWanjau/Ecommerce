package com.edwanjau11.ecommerce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.edwanjau11.ecommerce.databinding.ActivityMainBinding
import com.edwanjau11.ecommerce.fragments.HomeFragment
import com.edwanjau11.ecommerce.fragments.ProfileFragment
import com.edwanjau11.ecommerce.fragments.SearchFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        replaceFragment(HomeFragment())

        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.side_nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> Toast.makeText(applicationContext,"Clicked Home" , Toast.LENGTH_SHORT).show()
                R.id.search -> Toast.makeText(applicationContext,"Clicked search" , Toast.LENGTH_SHORT).show()
                R.id.profile -> Toast.makeText(applicationContext,"Clicked profile" , Toast.LENGTH_SHORT).show()
                R.id.log_out -> Toast.makeText(applicationContext,"Clicked log out" , Toast.LENGTH_SHORT).show()
            }
            true
        }

        binding.bottomNavigationView.setOnItemReselectedListener{

            when(it.itemId){

                R.id.home -> replaceFragment(HomeFragment())
                R.id.search -> replaceFragment(SearchFragment())
                R.id.profile -> replaceFragment(ProfileFragment())

                else ->{

                }
            }
            true
        }

    }

    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }
}