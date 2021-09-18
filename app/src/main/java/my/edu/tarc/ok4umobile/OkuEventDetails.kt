package my.edu.tarc.ok4umobile

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import org.w3c.dom.Text


class OkuEventDetails : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_oku_event_details, container, false)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db =
            FirebaseDatabase.getInstance("https://ok4u-a1047-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference("events")

        val vehicle = view.findViewById<CheckBox>(R.id.chkVehicle)
        val btnRegis = view.findViewById<Button>(R.id.btnRegister)
        val btnCancel = view.findViewById<Button>(R.id.btnCancel)

        val sharedPref: SharedPreferences? = this.activity?.getSharedPreferences(
            "kotlinsharedpreference", Context.MODE_PRIVATE
        )
        var name: String = sharedPref?.getString("name", "No Data").toString()
        var email: String = sharedPref?.getString("email", "No Data").toString()

        var current : Int

        val tvTitle: TextView = view.findViewById(R.id.tvEventTitle2)
        val tvPost: TextView = view.findViewById(R.id.tvPostDay2)
        val tvDesc: TextView = view.findViewById(R.id.tvEventDescription)
        val tvOrganizer: TextView = view.findViewById(R.id.tvOrganizer)
        val tvLocation: TextView = view.findViewById(R.id.tvLocation)
        val tvSlot: TextView = view.findViewById(R.id.tvCurrentSlot)
        val imgEvent: ImageView = view.findViewById(R.id.imageView2)

        val args = this.arguments
        val inputData = args?.get("eventTitle")
        val inputData2 = args?.get("eventDay")
        val inputData3 = args?.get("location")
        val inputData4 = args?.get("ngoName")
        val inputData5 = args?.get("desc")
        val inputData6 = args?.get("slot")
        val inputData7 = args?.get("imageUrl")

        tvTitle.text = "Event Title: " + inputData.toString()
        tvPost.text = "Event Date: " + inputData2.toString()
        tvLocation.text = "Event Location: " + inputData3.toString()
        tvOrganizer.text = "Event Organizer: " + inputData4.toString()
        tvDesc.text = "Event Description: " + inputData5.toString()
        tvSlot.text = "Remaining Slot: " + inputData6.toString()
        Glide.with(imgEvent.context).load(inputData7).into(imgEvent)

        val db2 =
            FirebaseDatabase.getInstance("https://ok4u-a1047-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference("events").child("$inputData").child("register").child("$email")
        val db3 =
            FirebaseDatabase.getInstance("https://ok4u-a1047-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference("events").child("$inputData")

        db3.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var slot = snapshot.child("currentSlot").value.toString()
                var maxSlot = snapshot.child("maxSlot").value.toString()
                Log.i("Slot", "$slot")
                if (slot.toInt() >= maxSlot.toInt()) {

                    btnRegis.isClickable = false
                    btnRegis.text = "Full"
                    btnRegis.setBackgroundColor(Color.GRAY)
                    showFullDialog()

                } else {
                    db2.addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            var check = snapshot.child("email").value.toString()

                            if (check.equals("$email")) {
                                btnRegis.isClickable = false
                                btnRegis.text = "Registered"
                                btnRegis.setBackgroundColor(Color.GRAY)
                                btnCancel.visibility = View.VISIBLE

                                btnCancel.setOnClickListener() {
                                    current = slot.toInt() - 1
                                    Log.i("current","$current")
                                    db3.child("currentSlot").setValue("$current")
                                    db2.addListenerForSingleValueEvent(object : ValueEventListener {
                                        override fun onDataChange(snapshot: DataSnapshot) {
                                            var del = snapshot.ref.removeValue()

                                        }
                                        override fun onCancelled(error: DatabaseError) {
                                            Log.i("Error", "Read failed")
                                        }
                                    })
                                }
                            } else {
                                btnRegis.isClickable = true
                                btnRegis.text = "Register"
                                btnRegis.setBackgroundColor(Color.parseColor("#4CAF50"))
                                btnCancel.visibility = View.INVISIBLE
                                btnRegis.setOnClickListener() {

                                    val builder: AlertDialog.Builder =
                                        AlertDialog.Builder(view.context)
                                    builder.setTitle("Register Confirmation")
                                    builder.setMessage("Confirm to register the event ?")

                                    builder.setPositiveButton(
                                        "Yes",
                                        DialogInterface.OnClickListener { dialog, which ->

                                            current = slot.toInt() + 1
                                            db3.child("currentSlot").setValue("$current")
                                            Log.i("currentplus","$current")

                                            if (vehicle.isChecked) {
                                                db.child("$inputData").child("register")
                                                    .child("$email").child("email")
                                                    .setValue("$email")
                                                db.child("$inputData").child("register")
                                                    .child("$email").child("transport")
                                                    .setValue("Yes")
                                                Toast.makeText(
                                                    context,
                                                    "Register Successful",
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            } else {
                                                db.child("$inputData").child("register")
                                                    .child("$email").child("email")
                                                    .setValue("$email")
                                                db.child("$inputData").child("register")
                                                    .child("$email").child("transport")
                                                    .setValue("No")
                                                Toast.makeText(
                                                    context,
                                                    "Register Successful",
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }
                                            dialog.cancel()
                                        })

                                    builder.setNegativeButton(
                                        "Cancel",
                                        DialogInterface.OnClickListener { dialog, which ->
                                            dialog.cancel()
                                        })

                                    val alertDialog: AlertDialog = builder.create()
                                    alertDialog.show()


                                }
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            Log.i("Error", "Read failed")
                        }

                    })

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("Error", "Read failed")
            }

        })

    }

    private fun showFullDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Event Registration Full")
        builder.setMessage("The event you looking for register is full.")

        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            dialog.cancel()
        })


        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }
}