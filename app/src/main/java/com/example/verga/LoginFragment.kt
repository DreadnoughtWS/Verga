package com.example.verga

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {

    private lateinit var tvRedirectSignUp: TextView
    lateinit var etEmail: EditText
    private lateinit var etPass: EditText
    lateinit var btnLogin: Button

    // Creating firebaseAuth object
    lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_login, container, false)
        val Buttonreg = view.findViewById<Button>(R.id.button_register)

        tvRedirectSignUp = view.findViewById(R.id.tvRedirectLogin)
        btnLogin = view.findViewById(R.id.button_login)
        etEmail = view.findViewById(R.id.email)
        etPass = view.findViewById(R.id.pass)

        // initialising Firebase auth object
        auth = FirebaseAuth.getInstance()


        btnLogin.setOnClickListener{
            login()
        }

        Buttonreg.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        return view
    }

    private fun login() {
        val email = etEmail.text.toString()
        val pass = etPass.text.toString()
        // calling signInWithEmailAndPassword(email, pass)
        // function using Firebase auth object
        // On successful response Display a Toast

        if (email.isBlank() || pass.isBlank()) {
            Toast.makeText(activity, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }

        activity?.let {
            auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener() {
                if (it.isSuccessful) {
                    Toast.makeText(activity, "Successfully LoggedIn", Toast.LENGTH_SHORT).show()
                    val i = Intent(activity, MainActivity::class.java)
                    startActivity(i)
                } else
                    Toast.makeText(activity, "Log In failed ", Toast.LENGTH_SHORT).show()
            }
        }
    }

}

