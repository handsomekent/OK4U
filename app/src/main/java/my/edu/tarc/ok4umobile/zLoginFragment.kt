package my.edu.tarc.ok4umobile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.auth.User
import com.google.firebase.ktx.Firebase
import my.edu.tarc.ok4umobile.databinding.FragmentLoginBinding
import my.edu.tarc.ok4umobile.databinding.FragmentRegisterBinding
import kotlin.contracts.Returns

class zLoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var userList: ArrayList<User>



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {



        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)


//        binding.btnLogin.setOnClickListener()
//        {
//            val intent = Intent(activity, Drawer::class.java) // open activity from fragment
//            startActivity(intent)
//        }

        binding.btnLogin.setOnClickListener(){
            val email1 = binding.txtEmail.text.toString()
            val pass1 = binding.txtPassword.text.toString()
            //val database = Firebase.database("https://kzassignment-d1445-default-rtdb.asia-southeast1.firebasedatabase.app/")
            //val ref = database.getReference("Users")

            val database  = FirebaseDatabase.getInstance("https://ok4u-a1047-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users")
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
                            var temp6 = i.child("userType").getValue().toString()




                            if (email1.equals(temp5)==true&& pass1.equals(temp4)==true) {
                            //if (email.compareTo(temp5) == 1 && pass.compareTo(temp4) == 1) {

                                Toast.makeText( context,"Sucess", Toast.LENGTH_LONG)
                                // toast failed
                                //val user = User(temp1, temp2, temp3, temp4, temp5)
                                //failed to pass obj
                                //val bundle = bundleOf(Pair("bundle", user))

//                                    usertype="OKU"
//                                    val i= Intent(this,Drawer::class.java)
//                                    i.putExtra( "Name",usertype)
//
//                                    startActivity(i)

                                //val bundle = bundleOf(Pair("bundle", temp2))
                                val intent = Intent(activity, Drawer::class.java) // open activity from fragment
                            //    intent.putExtra("Name",bundle)
//                                intent.putExtra("userType",temp6)
//                                intent.putExtra("email",temp5)
//                                intent.putExtra("name",temp2)
                                val sharedPrefFile = "kotlinsharedpreference"

                                val sharedPref : SharedPreferences? = activity?.getSharedPreferences(
                                    sharedPrefFile, Context.MODE_PRIVATE)
                                val editor: SharedPreferences.Editor? =  sharedPref?.edit()
                                editor?.putString("userType",temp6)
                                editor?.putString("email",temp5)
                                editor?.putString("name",temp2)
                                editor?.apply()



                                startActivity(intent)
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