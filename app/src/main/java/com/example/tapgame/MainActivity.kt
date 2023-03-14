package com.example.tapgame

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.tapgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {



    companion object{

        var a:Int=1



        }


        var number = 0
    var runnable :Runnable = Runnable {}
    var handler:Handler=Handler()
    var attack : Int? = null
    private lateinit var binding: ActivityMainBinding
   var sure: CountDownTimer?=null




    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var deger: Int? = null
        var sharedPreference =  getSharedPreferences("com.example.tapgame",MODE_PRIVATE)
        var shareddeger=sharedPreference.getInt("attack",1)
        a=intent.getIntExtra("attack",shareddeger)











        deger=0
        val alert = AlertDialog.Builder(this@MainActivity)
        alert.setTitle("Game Over")
        alert.setMessage("Restart The Game?")
        alert.setPositiveButton("Yes",
            DialogInterface.OnClickListener { dialog, id ->



             //   deger = attack
               // attack=1
                binding.progressBar.setProgress(attack!!)

                finish()
                startActivity(intent)

            })
        alert.create()



       var sure = object : CountDownTimer(10000,1000){
           override fun onTick(millisUntilFinished: Long) {

               binding.suretxt.text = (millisUntilFinished/1000).toString()

           }

           override fun onFinish() {
               binding.attackBtn.visibility = View.GONE
               alert.show()
               deger=0
           }


       }.start()


        binding.attackBtn.setOnClickListener {

            var intent = intent
            attack = intent.getIntExtra("attack",1)
            deger = deger!! + a!!
            binding.progressBar.setProgress(deger!!)
            binding.progressBar.setMax(100)
            binding.anlikHasar.setText(a.toString())
              deger  =  binding.progressBar.progress


            if (deger!!.equals(100)){
               var intent = Intent(this@MainActivity,Boss2::class.java)
                a+=5
                var saved_value = a
                sharedPreference.edit().putInt("attack",saved_value)
                sure.cancel()
                startActivity(intent)


            }

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.options_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId==R.id.Level2){
            var intent = Intent(this,Boss2::class.java)
            intent.putExtra("attack",1)
            startActivity(intent)

        }
        if (item.itemId==R.id.Level3){
            var intent = Intent(this,Boss3::class.java)
            intent.putExtra("attack",1)
            startActivity(intent)

        }
        if (item.itemId==R.id.xp_farm){
            var intent = Intent(this,Xp_Activity::class.java)
            intent.putExtra("attack",attack)
            sure?.let {
                sure!!.cancel()
            }
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPause() {

        super.onPause()
    }
}