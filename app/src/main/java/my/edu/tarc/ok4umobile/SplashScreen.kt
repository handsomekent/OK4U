package my.edu.tarc.ok4umobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_scree)


        val logo=findViewById<ImageView>(R.id.OK4U_Logo)

        val database = Firebase.database
        val myRef = database.getReference("message")

        myRef.setValue("Hello, World!")

        logo.alpha=0f
        logo.animate().setDuration(1500).alpha(1f).withEndAction{
            val i= Intent(this,MapsActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }
    }
}