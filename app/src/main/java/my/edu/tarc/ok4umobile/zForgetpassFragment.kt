package my.edu.tarc.ok4umobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import my.edu.tarc.ok4umobile.databinding.FragmentForgetpassBinding

class zForgetpassFragment : Fragment() {
    private lateinit var binding: FragmentForgetpassBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_forgetpass, container, false)

//fAuth.

        return binding.root
    }

}