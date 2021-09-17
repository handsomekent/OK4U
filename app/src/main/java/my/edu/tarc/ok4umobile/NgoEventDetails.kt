package my.edu.tarc.ok4umobile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class NgoEventDetails : Fragment() {



    var sharedPref = this.activity
        ?.getSharedPreferences("pref", Context.MODE_PRIVATE)

    var email: String = sharedPref?.getString("email", "No Data").toString()
    var name: String = sharedPref?.getString("name", "No Data").toString()
    var userType: String = sharedPref?.getString("userType", "No Data").toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ngo_event_details, container, false)
    }


}