package my.edu.tarc.ok4umobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.auth.User
import my.edu.tarc.ok4umobile.R
import my.edu.tarc.ok4umobile.databinding.FragmentLoginBinding
import my.edu.tarc.ok4umobile.databinding.FragmentParticipateBinding

class ParticipateFragment : Fragment() {
    private lateinit var binding: FragmentParticipateBinding
    private lateinit var userList: ArrayList<User>
    private lateinit var auth: FirebaseAuth;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflate(inflater, R.layout.fragment_participate, container, false)
        val database = FirebaseDatabase.getInstance("https://ok4u-a1047-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users")



        return binding.root
    }

}