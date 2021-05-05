package com.example.projetofinal
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Login : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance();

        val LoginBTN: Button = findViewById(R.id.signIn) as Button
        val RegisterBTN: Button = findViewById(R.id.register) as Button
        val editTextEmail: EditText = findViewById(R.id.email) as EditText
        val editTextPassword: EditText = findViewById(R.id.password) as EditText

        LoginBTN.setOnClickListener {
            Toast.makeText(this, "login", Toast.LENGTH_SHORT).show()

            if(editTextEmail.text.toString().trim().isNotEmpty() && editTextPassword.text.toString().trim().isNotEmpty()) {
                auth.signInWithEmailAndPassword(
                    editTextEmail.text.toString().trim(),
                    editTextPassword.text.toString().trim()
                )
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(baseContext, " login com sucesso.", Toast.LENGTH_SHORT).show()
                            val user = auth.currentUser
                            updateUI(user)

                            val intent = Intent(this, PortalDeNoticias::class.java)
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
        RegisterBTN.setOnClickListener {
            Toast.makeText(this, "register", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Register::class.java)
            startActivity(intent)

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