package com.farmaciasperuanas.dermaapp.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.farmaciasperuanas.dermaapp.R
import com.farmaciasperuanas.dermaapp.buscar.BuscarActivity
import com.farmaciasperuanas.dermaapp.carrito.CarritoActivity
import com.farmaciasperuanas.dermaapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        drawerLayout = mainBinding.drawerLayout
        navController = Navigation.findNavController(this, R.id.dermo_nav_fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        // Set up ActionBar
        setSupportActionBar(mainBinding.toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
        // Set up navigation menu
        mainBinding.navigationView.setupWithNavController(navController)
        initViews()
    }

    private fun initViews() {
        initViewHeader()

        imageViewBuscar.setOnClickListener { view ->
            startActivity(Intent(this@MainActivity, BuscarActivity::class.java))
        }
        imageViewCart.setOnClickListener { view ->
            startActivity(Intent(this@MainActivity, CarritoActivity::class.java))
        }
    }

    private fun initViewHeader() {
        val view = mainBinding.navigationView.getHeaderView(0)
        view.textViewNombre.setText("Luis Enrique Rojas Palomino")
        Glide.with(this)
            .load("https://avatars0.githubusercontent.com/u/7980095?s=400&v=4")
            .into(view.imageViewPerfil)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
