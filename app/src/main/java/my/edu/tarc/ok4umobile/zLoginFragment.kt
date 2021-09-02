package my.edu.tarc.ok4umobile

import android.content.Intent
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
        val email = binding.txtEmail.text.toString()
        val pass = binding.txtPassword.text.toString()

//        binding.btnLogin.setOnClickListener()
//        {
//            val intent = Intent(activity, Drawer::class.java) // open activity from fragment
//            startActivity(intent)
//        }





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