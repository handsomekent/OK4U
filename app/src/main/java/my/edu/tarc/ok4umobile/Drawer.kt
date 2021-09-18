package my.edu.tarc.ok4umobile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toolbar
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import my.edu.tarc.ok4umobile.databinding.DrawerAdminBinding
import my.edu.tarc.ok4umobile.databinding.DrawerNgoBinding
import my.edu.tarc.ok4umobile.databinding.DrawerOkuBinding


class Drawer : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration


    private lateinit var binding1: DrawerAdminBinding
    private lateinit var binding2: DrawerNgoBinding
    private lateinit var binding3: DrawerOkuBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val sharedPref: SharedPreferences? = this.getSharedPreferences(
            "kotlinsharedpreference", Context.MODE_PRIVATE
        ) ?: return
        var email: String = sharedPref?.getString("email", "No Data").toString()
        var name: String = sharedPref?.getString("name", "No Data").toString()
        var userType: String = sharedPref?.getString("userType", "No Data").toString()



        Log.i("MainActivity", "" + userType)//test only


//if user is admin
        if (userType == "admin") {
            binding1 = DrawerAdminBinding.inflate(layoutInflater)
            setContentView(binding1.root)

               setSupportActionBar(binding1.appBarDrawer.toolbar)


            val drawerLayout: DrawerLayout = binding1.adminDrawer


            val navView: NavigationView = binding1.navView
            val navController = findNavController(R.id.nav_host_fragment_content_drawer)
            appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.nav_oku_editProfile,
                    R.id.nav_oku_suggest_facilities,
                    R.id.nav_event,
                    R.id.mapsFragment,
                    R.id.nav_apply_event_posting,
                    R.id.nav_verify_event_posting,
                    R.id.nav_verify_new_facilities
                ), drawerLayout
            )
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)
        }
        //if user is ngo
        if (userType == "Ngo") {
            binding2 = DrawerNgoBinding.inflate(layoutInflater)
            setContentView(binding2.root)

            setSupportActionBar(binding2.appBarDrawer.toolbar)

            val drawerLayout: DrawerLayout = binding2.ngoDrawer

            val navView: NavigationView = binding2.navView
            val navController = findNavController(R.id.nav_host_fragment_content_drawer)


            appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.nav_ngo_edit_profile,
                    R.id.nav_oku_suggest_facilities,
                    R.id.nav_event,
                    R.id.mapsFragment,
                    R.id.nav_apply_event_posting,
                    R.id.nav_verify_event_posting,
                    R.id.nav_verify_new_facilities,
                    R.id.event,
                ), drawerLayout
            )
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)


        }
        //if user is oku
        if (userType == "Oku") {
            binding3 = DrawerOkuBinding.inflate(layoutInflater)
            setContentView(binding3.root)

            setSupportActionBar(binding3.appBarDrawer.toolbar)


            val drawerLayout: DrawerLayout = binding3.okuDrawer


            val navView: NavigationView = binding3.navView
            val navController = findNavController(R.id.nav_host_fragment_content_drawer)

            appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.nav_oku_editProfile,
                    R.id.nav_oku_suggest_facilities,
                    R.id.nav_event,
                    R.id.mapsFragment,
                    R.id.nav_apply_event_posting,
                    R.id.nav_verify_event_posting,
                    R.id.nav_verify_new_facilities,
                    R.id.event,
                    R.id.okuEventDetails
                ), drawerLayout
            )
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)

        }


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.drawer, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_drawer)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> {
            // User chose the "Settings" item, show the app settings UI...
            true
        }

        R.id.log_out -> {
            // User chose the "Favorite" action, mark the current item
            // as a favorite...
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }
}