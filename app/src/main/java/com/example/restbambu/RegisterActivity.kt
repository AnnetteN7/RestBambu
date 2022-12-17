package com.example.restbambu

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.project.restbambu.R
import com.project.restbambu.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    private lateinit var auth: FirebaseAuth

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)
        auth = Firebase.auth
        binding.btnRegistro.setOnClickListener{createRegister()}
    }



    private fun createRegister() {

        val email = binding.etEmail.text.toString()
        val clave = binding.etClave.text.toString()

        Log.d("Registrándose","Haciendo llamado a creación")
        //Utilizo el objeto auth para hacer el registro...
        auth.createUserWithEmailAndPassword(email,clave)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {  //Si se logró... se creo el usuario
                    Log.d("Registrándose","se registró")
                    val user = auth.currentUser
                    refresh(user)
                } else { //Si no se logró hubo un error...
                    Log.e("Registrándose","Error de registró")
                    println(task.exception.toString())
                    Toast.makeText(baseContext,task.exception.toString(), Toast.LENGTH_LONG).show()
                    refresh(null)
                }
            }
        Log.d("Registrándose","Sale del proceso...")
    }

    private fun refresh(user: FirebaseUser?) {
        if (user!=null){
            val  intent = Intent(this, RestauranteActivity::class.java)
            startActivity(intent)
        }
    }
    public override fun onStart() {
        super.onStart()
        val usuario = auth.currentUser
        refresh(usuario)
    }
}