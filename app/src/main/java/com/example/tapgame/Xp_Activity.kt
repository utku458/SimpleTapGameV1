package com.example.tapgame

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.tapgame.databinding.ActivityBoss2Binding
import com.example.tapgame.databinding.ActivityXpBinding

class Xp_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityXpBinding
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityXpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var attack=1
       // val sharedPreference =  getSharedPreferences("com.example.tapgame",MODE_PRIVATE)
       // var editor = sharedPreference.edit()
        var deger: Int? = null
        deger=0
        val alert = AlertDialog.Builder(this@Xp_Activity)
        alert.setTitle("Game Over")
        alert.setMessage("Restart The Game?")
        alert.setPositiveButton("Yes",
            DialogInterface.OnClickListener { dialog, id ->
                //   deger = attack
                var intent = intent
                var attack = intent.getIntExtra("attack",1)
                binding.progressBarxp.setProgress(attack)
                finish()
                startActivity(intent)
            })
        alert.create()
      var sure = object : CountDownTimer(10000,1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.suretxtxp.text = (millisUntilFinished/1000).toString()
            }
            override fun onFinish() {
                binding.attackBtnxp.visibility = View.GONE
                alert.show()
                deger=0
            }
        }.start()
        binding.attackBtnxp.setOnClickListener {
            deger = deger!! +  MainActivity.a
            binding.progressBarxp.setProgress(deger!!)
            binding.progressBarxp.setMax(25)
            binding.anlikHasarxp.setText( MainActivity.a.toString())
            deger  =  binding.progressBarxp.progress
            if (deger!!.equals(25)){
                MainActivity.a +=1
            //    sharedPreference.edit().putInt("attack",2)
                var intent = Intent(this@Xp_Activity,MainActivity::class.java)
                intent.putExtra("attack", MainActivity.a)
                sure.cancel()
                startActivity(intent)
                finish()
            }

        }
    }
}