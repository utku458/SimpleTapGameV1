package com.example.tapgame

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.tapgame.databinding.ActivityBoss2Binding
import com.example.tapgame.databinding.ActivityBoss3Binding

class Boss3 : AppCompatActivity() {
    private lateinit var binding: ActivityBoss3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoss3Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var attack=1
        // val sharedPreference =  getSharedPreferences("com.example.tapgame",MODE_PRIVATE)
        // var editor = sharedPreference.edit()
        var deger: Int? = null
        deger=0
        val alert = AlertDialog.Builder(this@Boss3)
        alert.setTitle("Game Over")
        alert.setMessage("Restart The Game?")
        alert.setPositiveButton("Yes",
            DialogInterface.OnClickListener { dialog, id ->
                //   deger = attack
                var intent = intent
                var attack = intent.getIntExtra("attack",1)
                binding.progressBar.setProgress(attack)
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
            deger = deger!! +  MainActivity.a
            binding.progressBar.setProgress(deger!!)
            binding.progressBar.setMax(1000)
            binding.anlikHasar.setText( MainActivity.a.toString())
            deger  =  binding.progressBar.progress
            if (deger!!.equals(1000)){
                MainActivity.a +=1
                //    sharedPreference.edit().putInt("attack",2)
                var intent = Intent(this@Boss3,MainActivity::class.java)
                intent.putExtra("attack", MainActivity.a)
                sure.cancel()
                Toast.makeText(this, "Oyun bitti", Toast.LENGTH_SHORT).show()
                startActivity(intent)
                finish()
            }

        }









    }
}