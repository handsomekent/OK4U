package my.edu.tarc.ok4umobile

import android.os.Bundle
import android.renderscript.Sampler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import my.edu.tarc.ok4umobile.databinding.FragmentOkuEditProfileBinding


class OkuEditProfile : Fragment() {

    private lateinit var binding: FragmentOkuEditProfileBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

      //  var s:String = email
//        val databaseuser  = FirebaseDatabase.getInstance("https://ok4u-a1047-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users")
//
//databaseuser.addValueEventListener(object : ValueEventListener{
//    override fun onDataChange(snapshot: DataSnapshot) {
//        for (i in snapshot.children) {
//            var namedb=i.child(name).child("name").getValue().toString()
//            var genderdb=i.child(name).child("name").getValue().toString()
//            var addressdb=i.child(name).child("address").getValue().toString()
//            var phonedb=i.child(name).child("phonenumber").getValue().toString()
//            if(name==namedb){
//                binding.tfFullName.setText(name)
//              //  binding.editGender.setText()
//            }
//
//        }
//    }
//
//    override fun onCancelled(error: DatabaseError) {
//        TODO("Not yet implemented")
//    }
//
//
//}
//
//)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_oku_edit_profile, container, false)
    }

}