package my.edu.tarc.ok4umobile

import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.Navigation
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class AdminVerifyNewFacilities : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_verify_new_facilities, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var Id:Int = 0
        val btnApprove = view.findViewById<Button>(R.id.btnApprove)
        val btnReject = view.findViewById<Button>(R.id.btnReject)

        val tvFacTitle: TextView = view.findViewById(R.id.tvFacTitle)
        val tvFacDesc: TextView = view.findViewById(R.id.tvFacDesc)
        val tvFacType: TextView = view.findViewById(R.id.tvFacType)
        val tvFacLat: TextView = view.findViewById(R.id.tvFacLat)
        val tvFacLong: TextView = view.findViewById(R.id.tvFacLong)

        val args = this.arguments
        val inputData = args?.get("facTitle")
        val inputData1 = args?.get("facDesc")
        val inputData2 = args?.get("facType")
        val inputData3 = args?.get("facLat")
        val inputData4 = args?.get("facLong")
        val inputData5 = args?.get("okuId")

        tvFacTitle.text = "Facility Name: " + inputData.toString()
        tvFacDesc.text = "Facility Description: " + inputData1.toString()
        if(inputData2 == "1"){
            tvFacType.text = "Facility Type: Facility"

        }else{
            tvFacType.text = "Facility Type: Service"

        }
        tvFacLat.text = "Facility Latitude: " + inputData3.toString()
        tvFacLong.text = "Facility Longitude: " + inputData4.toString()

        val db =
            FirebaseDatabase.getInstance("https://ok4u-a1047-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference("PendingFacility").child("$inputData")

        val db2 =
            FirebaseDatabase.getInstance("https://ok4u-a1047-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference("Facilities").child("$inputData")

        btnApprove.setOnClickListener(){
            db.addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    var del = snapshot.ref.removeValue()
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.i("Error", "Read failed")
                }

            })
            db2.child("facilityName").setValue("$inputData")
            db2.child("facility").setValue("$inputData2")
            db2.child("faciDesc").setValue("$inputData1")
            db2.child("status").setValue("1")
            db2.child("latitude").setValue("$inputData3")
            db2.child("longitude").setValue("$inputData4")
            Toast.makeText(
                context,
                "Facility Approve",
                Toast.LENGTH_LONG
            ).show()
            Navigation.findNavController(it).navigate(R.id.action_nav_verify_new_facilities_to_facility2)

        }
        btnReject.setOnClickListener(){
            var denyMsg = ""
            Toast.makeText(
                context,
                "Facility Rejected",
                Toast.LENGTH_LONG
            ).show()
            val builder: AlertDialog.Builder =
                AlertDialog.Builder(view.context)
            builder.setTitle("Reject Confirmation")
            builder.setMessage("Confirm to Reject the Facility?")
            val input = EditText(view.context)
            input.inputType = InputType.TYPE_CLASS_TEXT
            input.setHint("Enter Reason")
            builder.setView(input)

            builder.setPositiveButton(
                "Confirm",
                DialogInterface.OnClickListener { dialog, which ->
                    denyMsg = input.text.toString()

                        db.addListenerForSingleValueEvent(object : ValueEventListener{
                            override fun onDataChange(snapshot: DataSnapshot) {
                                var del = snapshot.ref.removeValue()
                            }
                            override fun onCancelled(error: DatabaseError) {
                                Log.i("Error", "Read failed")
                            }
                        })

                    val db4 = FirebaseDatabase.getInstance("https://ok4u-a1047-default-rtdb.asia-southeast1.firebasedatabase.app")
                        .getReference("Users").child("$inputData5").child("notification")

                    db4.addListenerForSingleValueEvent(object:ValueEventListener{
                        override fun onDataChange(snapshot: DataSnapshot) {
                            Id = snapshot.childrenCount.toInt()+1
                            var pkId = Id.toString()
                            db4.child("$pkId").child("content").setValue("Facility Name: $inputData , Deny Reason: $denyMsg")
                        }

                        override fun onCancelled(error: DatabaseError) {
                            Log.i("Error","Not Found")
                        }

                    })

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