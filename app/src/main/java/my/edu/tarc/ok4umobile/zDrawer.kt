package my.edu.tarc.ok4umobile

import android.app.Notification
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
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
import com.google.android.gms.maps.GoogleMap
import my.edu.tarc.ok4umobile.databinding.DrawerAdminBinding
import my.edu.tarc.ok4umobile.databinding.DrawerNgoBinding
import my.edu.tarc.ok4umobile.databinding.DrawerOkuBinding


class zDrawer : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var map: GoogleMap


    private lateinit var binding1: DrawerAdminBinding
    private lateinit var binding2: DrawerNgoBinding
    private lateinit var binding3: DrawerOkuBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref: SharedPreferences? = this.getSharedPreferences(
            "kotlinsharedpreference", Context.MODE_PRIVATE
        ) ?: return
        var id: String = sharedPref?.getString("id", "No Data").toString()

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
                    R.id.nav_verify_new_facilities,
                    R.id.event
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
                    R.id.notification_UserNgo
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
                    R.id.okuEventDetails,
                    R.id.notification_UserNgo

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
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        //  if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //       return
        //    }
        // if (isPermissionGranted(permissions, grantResults, Manifest.permission.ACCESS_FINE_LOCATION)) {
        when(requestCode){
            requestCode->{
                if(grantResults.isEmpty()||grantResults[0]!= PackageManager.PERMISSION_GRANTED){
                    // Enable the my location layer if the permission has been granted.
                    //   permissionDenied = true
                    map.isMyLocationEnabled = false
                    //   map.isMyLocationEnabled = true

                } else {
                    // Permission was denied. Display an error message
                    // Display the missing permission error dialog when the fragments resume.
                    //  enableMyLocation()

                    //    map.isMyLocationEnabled = false
                }
            }
        }

    }
}