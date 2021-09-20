package my.edu.tarc.ok4umobile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import my.edu.tarc.ok4umobile.data.Event
import my.edu.tarc.ok4umobile.databinding.FragmentNgoEditProfileBinding
import my.edu.tarc.ok4umobile.databinding.FragmentNotificationUserNgoBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Notification_UserNgo.newInstance] factory method to
 * create an instance of this fragment.
 */
class Notification_UserNgo : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

  //  private lateinit var binding: FragmentNotificationUserNgoBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

     //   return binding.root
        return inflater.inflate(R.layout.fragment_notification__user_ngo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val sharedPref : SharedPreferences? = this.activity?.getSharedPreferences(
//            "kotlinsharedpreference", Context.MODE_PRIVATE)
//        var id: String = sharedPref?.getString("id", "No Data").toString()
//        val listview= view?.findViewById<ListView>(R.id.listNotification)
//        // val intArr:Array<String>
//        val intArr= arrayOf<String>("asd","asd")
//        var h :Int=0
//
//
//        val database =
//            FirebaseDatabase.getInstance("https://ok4u-a1047-default-rtdb.asia-southeast1.firebasedatabase.app/")
//                .getReference("Users").child(id).child("notification")
//
////        database.addValueEventListener(object : ValueEventListener {
////
////            override fun onDataChange(snapshot: DataSnapshot) {
////
////                for (i in snapshot.children) {
////                    var reason=i.child("content").getValue().toString()
////                    //  intArr.set(h,reason)
////                    Log.i("MainActivity", reason)
////
////                    //    h++
////                }
////                // Log.i("MainActivity", intArr.toString())
////
////
////            }
////
////            override fun onCancelled(error: DatabaseError) {
////            }
////        })
////        intArr.set(0,"sad")
////        intArr.set(1,"sad")
////        intArr.set(2,"sad")
//
//
//        val arrayAdapter :ArrayAdapter<*> = ArrayAdapter(requireActivity(),R.layout.fragment_notification__user_ngo, intArr)
//        listview.adapter=arrayAdapter

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Notification_UserNgo.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Notification_UserNgo().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}