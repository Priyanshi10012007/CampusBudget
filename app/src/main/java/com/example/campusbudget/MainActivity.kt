package com.example.campusbudget

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val username =
            findViewById<EditText>(R.id.etUsername)

        val password =
            findViewById<EditText>(R.id.etPassword)

        val loginButton =
            findViewById<Button>(R.id.btnLogin)

        loginButton.setOnClickListener {

            val user =
                username.text.toString().trim()

            val pass =
                password.text.toString().trim()

            if (user == "student" && pass == "1234") {

                startActivity(
                    Intent(
                        this,
                        DashboardActivity::class.java
                    )
                )

            } else if (user == "parent" && pass == "1234") {

                startActivity(
                    Intent(
                        this,
                        ParentDashboardActivity::class.java
                    )
                )

            } else {

                Toast.makeText(
                    this,
                    "Invalid username or password",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}