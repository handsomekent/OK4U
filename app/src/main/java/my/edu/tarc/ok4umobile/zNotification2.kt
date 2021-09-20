package my.edu.tarc.ok4umobile

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class zNotification2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        val sharedPref: SharedPreferences? = this.getSharedPreferences(
            "kotlinsharedpreference", Context.MODE_PRIVATE
        ) ?: return
        var id: String = sharedPref?.getString("id", "No Data").toString()

        val database =
            FirebaseDatabase.getInstance("https://ok4u-a1047-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("Users")

//        database.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//            }
//        }


    }
}