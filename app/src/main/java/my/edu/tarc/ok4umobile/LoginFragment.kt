package my.edu.tarc.ok4umobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.graphics.Color
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.kzassignment.databinding.FragmentLoginBinding
import com.example.kzassignment.databinding.FragmentRegisterBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.auth.User
import com.google.firebase.ktx.Firebase
import my.edu.tarc.ok4umobile.databinding.FragmentLoginBinding
import kotlin.contracts.Returns

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var userList: ArrayList<User>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        val email = binding.txtEmail.text.toString()
        val pass = binding.txtPassword.text.toString()
        binding.btnLogin.setOnClickListener(){
            //val database = Firebase.database("https://kzassignment-d1445-default-rtdb.asia-southeast1.firebasedatabase.app/")
            //val ref = database.getReference("Users")

            val database  = FirebaseDatabase.getInstance("https://kzassignment-d1445-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users")
            database.addValueEventListener(object : ValueEventListener{

                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        binding.textView.text = "can"
                        //change text failed

                        for (i in snapshot.children) {
                            var temp1 = i.child("id").getValue().toString()
                            var temp2 = i.child("name").getValue().toString()
                            var temp3 = i.child("gender").getValue().toString()
                            var temp4 = i.child("password").getValue().toString()
                            var temp5 = i.child("email").getValue().toString()

                            if (email.compareTo(temp5) == 0 && pass.compareTo(temp4) == 0) {
                                Toast.makeText( context,"Sucess", Toast.LENGTH_LONG)
                                // toast failed
                                //val user = User(temp1, temp2, temp3, temp4, temp5)
                                //failed to pass obj
                                //val bundle = bundleOf(Pair("bundle", user))

                                val bundle = bundleOf(Pair("bundle", temp2))
                                Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_testFragment, bundle)
                            } else {
                                Toast.makeText(context, "failed", Toast.LENGTH_LONG) //   }
                            }
//                            val temp = i.getValue(User::class.java)
//                            binding.textView.text = temp?.name.toString()
//                            //userList.add(temp)
                        }
                        // binding.textView.text = userList.size.toString()
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    //Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_testFragment)
                }
            })
        }

        binding.lblRegister.setOnClickListener(){
            binding.lblRegister.setTextColor( Color.BLACK)
            Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.lblReset.setOnClickListener(){
            binding.lblRegister.setTextColor( Color.BLACK)
            Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_forgetpassFragment2)
        }

        return binding.root
    }
}