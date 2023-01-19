package com.example.verga

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.Toast
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class RegisterFragment : Fragment() {


    lateinit var etEmail: EditText
    lateinit var etConfPass: EditText
    private lateinit var etPass: EditText
    private lateinit var btnSignUp: Button

    // create Firebase authentication object
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_register, container, false)
        val Buttonback = view.findViewById<ImageView>(R.id.back)
        val Buttonreg = view.findViewById<Button>(R.id.button_create)

        etEmail = view.findViewById<EditText>(R.id.EmailAddress)
        etConfPass = view.findViewById<EditText>(R.id.CFPassword)
        etPass = view.findViewById<EditText>(R.id.Password)
        btnSignUp = Buttonreg


        // Initialising auth object
        auth = Firebase.auth
        btnSignUp.setOnClickListener {
            signUpUser()
        }
        Buttonback.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        return view
    }
    private fun signUpUser() {
        val email = etEmail.text.toString()
        val pass = etPass.text.toString()
        val confirmPassword = etConfPass.text.toString()

        // check pass
        if (email.isBlank() || pass.isBlank() || confirmPassword.isBlank()) {
            Toast.makeText(activity, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }

        if (pass != confirmPassword) {
            Toast.makeText(activity, "Password and Confirm Password do not match", Toast.LENGTH_SHORT)
                .show()
            return
        }
        // If all credential are correct
        // We call createUserWithEmailAndPassword
        // using auth object and pass the
        // email and pass in it.

        activity?.let {
            auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(it) {
                if (it.isSuccessful) {
                    Toast.makeText(activity, "Successfully Singed Up", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)

                } else {
                    Toast.makeText(activity, "Singed Up Failed!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}