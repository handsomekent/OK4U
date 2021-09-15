package my.edu.tarc.ok4umobile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
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
        var intent=intent
        var userType: String? =intent.getStringExtra("userType")

        Log.i("MainActivity", ""+userType)//test only


//if user is admin
        if (userType=="admin"){
            binding1 = DrawerAdminBinding.inflate(layoutInflater)
            setContentView(binding1.root)

            setSupportActionBar(binding1.appBarDrawer.toolbar)

            binding1.appBarDrawer.fab.setOnClickListener { view ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
            val drawerLayout: DrawerLayout = binding1.adminDrawer
            //val drawerLayout: DrawerLayout = binding.ngoDrawer
            //val drawerLayout: DrawerLayout = binding.okuDrawer


            val navView: NavigationView = binding1.navView
            val navController = findNavController(R.id.nav_host_fragment_content_drawer)
            appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.nav_oku_editProfile,R.id.nav_event,
                    R.id.mapsFragment,R.id.nav_apply_event_posting,R.id.nav_verify_event_posting,R.id.nav_verify_new_facilities
                ), drawerLayout
            )
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)
        }
        //if user is ngo
        if(userType=="Ngo"){
            binding2 = DrawerNgoBinding.inflate(layoutInflater)
            setContentView(binding2.root)

            setSupportActionBar(binding2.appBarDrawer.toolbar)

            binding2.appBarDrawer.fab.setOnClickListener { view ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

                //val drawerLayout: DrawerLayout = binding2.adminDrawer
                val drawerLayout: DrawerLayout = binding2.ngoDrawer
                //val drawerLayout: DrawerLayout = binding.okuDrawer

                val navView: NavigationView = binding2.navView
                val navController = findNavController(R.id.nav_host_fragment_content_drawer)


                appBarConfiguration = AppBarConfiguration(
                    setOf(
                        R.id.nav_oku_editProfile,R.id.nav_event,
                        R.id.mapsFragment,R.id.nav_apply_event_posting,R.id.nav_verify_event_posting,R.id.nav_verify_new_facilities
                    ), drawerLayout
                )
                setupActionBarWithNavController(navController, appBarConfiguration)
                navView.setupWithNavController(navController)
            }



            val navView: NavigationView = binding2.navView
            val navController = findNavController(R.id.nav_host_fragment_content_drawer)

        }
        //if user is oku
        if(userType=="Oku"){
            binding3 = DrawerOkuBinding.inflate(layoutInflater)
            setContentView(binding3.root)

            setSupportActionBar(binding3.appBarDrawer.toolbar)

            binding3.appBarDrawer.fab.setOnClickListener { view ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
            //val drawerLayout: DrawerLayout = binding3.adminDrawer
            //val drawerLayout: DrawerLayout = binding.ngoDrawer
            val drawerLayout: DrawerLayout = binding3.okuDrawer


            val navView: NavigationView = binding3.navView
            val navController = findNavController(R.id.nav_host_fragment_content_drawer)

            appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.nav_oku_editProfile,R.id.nav_event,
                    R.id.mapsFragment,R.id.nav_apply_event_posting,R.id.nav_verify_event_posting,R.id.nav_verify_new_facilities
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
}