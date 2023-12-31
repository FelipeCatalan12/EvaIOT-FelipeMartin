package com.example.eva02iot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.eva02iot.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //Firebase Authentication
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Inicializar Firebase con auth

        auth = Firebase.auth

        //Activacion de Login on click

        binding.btnLogin.setOnClickListener {

                val email = binding.etEmail.text.toString()
                val password = binding.etPassword.text.toString()

                if (email.isEmpty()) {
                    binding.etEmail.error = "Ingresa un e-mail"
                    return@setOnClickListener
                }

                if (password.isEmpty()) {
                    binding.etPassword.error = "Ingresa una password"
                    return@setOnClickListener
                }

                signIn(email, password)
        }
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, VistaBienvenida::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show()
                }
            }
    }
}