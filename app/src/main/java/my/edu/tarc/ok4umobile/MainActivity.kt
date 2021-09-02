package my.edu.tarc.ok4umobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn1=findViewById<Button>(R.id.button2)
        val btn2=findViewById<Button>(R.id.button3)
        var usertype: String
        btn1.setOnClickListener{
            usertype="OKU"
            val i= Intent(this,Drawer::class.java)
            i.putExtra( "Name",usertype)

            startActivity(i)
        }
        btn2.setOnClickListener{
             usertype="admin"

            val i= Intent(this,Drawer::class.java)
            i.putExtra( "Name",usertype)

            startActivity(i)

        }

    }
}