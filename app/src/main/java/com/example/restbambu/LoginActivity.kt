package com.example.restbambu

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
import com.project.restbambu.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)
        auth = Firebase.auth
        binding.btnInicio.setOnClickListener { createLogin() }
    }

    private fun createLogin() {
        val email = binding.etEmail.text.toString()
        val clave = binding.etPassword.text.toString()
        Log.d("Autenticandose", "Haciendo llamado de autenticación")
        auth.signInWithEmailAndPassword(email, clave)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {  //Si se logró... se creo el usuario
                    Log.d("Autenticando", "se autenticó")
                    val user = auth.currentUser
                    refresh(user)

                } else { //Si no se logró hubo un error...
                    Log.e("Autenticando", "Error de Autenticación")
                    println(task.exception.toString())
                    Toast.makeText(baseContext, "Fallo", Toast.LENGTH_LONG).show()
                    refresh(null)
                }
            }
        Log.d("Autenticando", "Sale del proceso...")
    }

    private fun refresh(user: FirebaseUser?) {
        if (user != null) {
            val intent = Intent(this, RestauranteActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        val user = auth.currentUser
        refresh(user)
    }
}