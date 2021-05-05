package com.example.projetofinal

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class Register : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        auth = FirebaseAuth.getInstance();

        val RegisterBTN: Button = findViewById(R.id.register2) as Button
        val editTextName: EditText = findViewById(R.id.Name2) as EditText
        val editTextEmail: EditText = findViewById(R.id.email2) as EditText
        val editTextPassword: EditText = findViewById(R.id.password2) as EditText

        RegisterBTN.setOnClickListener {
            if(editTextName.text.toString().isEmpty()){
                editTextName.error = "Digite o seu nome"

            }
            if(editTextEmail.text.toString().isEmpty()){
                editTextEmail.error = "Digite o email"
            }
            if(editTextPassword.text.toString().isEmpty()){
                editTextPassword.error = "Digite a senha"
            }
            if(editTextName.text.toString().isNotEmpty() && editTextEmail.text.toString().trim().isNotEmpty() && editTextPassword.text.toString().trim().isNotEmpty()){
                auth.createUserWithEmailAndPassword(editTextEmail.text.toString(), editTextPassword.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                            updateUI(user)
                            val intent = Intent(this, Login::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                            updateUI(null)
                        }
                    }
            }


        }



    }
    public override fun onStart() {
        super.onStart()
        val currentUser : FirebaseUser? = auth.currentUser
        updateUI(currentUser)
    }
    fun updateUI(currentUser: FirebaseUser?){

    }




}