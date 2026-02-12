package com.yutahnahsyah.upsmartcanteenfrontend.auth

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.Spanned
import android.text.TextWatcher
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.yutahnahsyah.upsmartcanteenfrontend.MainActivity
import com.yutahnahsyah.upsmartcanteenfrontend.R

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var etFullName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etDepartment: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var cbTerms: CheckBox
    private lateinit var btnRegister: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        etFullName = findViewById(R.id.etFullName)
        etEmail = findViewById(R.id.etEmail)
        etDepartment = findViewById(R.id.etDepartment)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        cbTerms = findViewById(R.id.cbTerms)
        val tvTermsAndPrivacy = findViewById<TextView>(R.id.tvTermsAndPrivacy)
        btnRegister = findViewById(R.id.btnRegister)
        val tvLogin = findViewById<TextView>(R.id.tvLogin)

        // Block space characters in password fields
        val noSpaceFilter = InputFilter { source, start, end, _, _, _ ->
            for (i in start until end) {
                if (Character.isWhitespace(source[i])) {
                    return@InputFilter ""
                }
            }
            null
        }
        etPassword.filters = arrayOf(noSpaceFilter)
        etConfirmPassword.filters = arrayOf(noSpaceFilter)

        // Initial state
        btnRegister.isEnabled = false

        // Add listeners to all fields
        val watcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkFields()
            }
            override fun afterTextChanged(s: Editable?) {}
        }

        etFullName.addTextChangedListener(watcher)
        etEmail.addTextChangedListener(watcher)
        etDepartment.addTextChangedListener(watcher)
        etPassword.addTextChangedListener(watcher)
        etConfirmPassword.addTextChangedListener(watcher)
        cbTerms.setOnCheckedChangeListener { _, _ -> checkFields() }

        tvTermsAndPrivacy.setOnClickListener {
            showTermsDialog()
        }

        btnRegister.setOnClickListener {
            val name = etFullName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val department = etDepartment.text.toString().trim()
            val password = etPassword.text.toString() // No trim to catch spaces if filter failed
            val confirmPassword = etConfirmPassword.text.toString()

            if (validateInput(name, email, department, password, confirmPassword)) {
                Toast.makeText(this, "Welcome, $name!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        tvLogin.setOnClickListener {
            finish()
        }
    }

    private fun checkFields() {
        val name = etFullName.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val department = etDepartment.text.toString().trim()
        val password = etPassword.text.toString()
        val confirmPassword = etConfirmPassword.text.toString()
        val isChecked = cbTerms.isChecked

        btnRegister.isEnabled = name.isNotEmpty() && 
                             email.isNotEmpty() && 
                             department.isNotEmpty() && 
                             password.length >= 8 && // Enforce 8 char minimum for button enabling
                             confirmPassword.isNotEmpty() && 
                             isChecked
    }

    private fun showTermsDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Terms & Privacy Policy")
            .setMessage("1. Data Collection: We collect minimal data like name and email.\n\n" +
                    "2. Usage: Your data is used for ordering and identification.\n\n" +
                    "3. Security: We use industry standards to protect your data.\n\n" +
                    "4. Payments: Cash and Stub are the only accepted methods.\n\n" +
                    "By using this app, you agree to these terms.")
            .setPositiveButton("I Understand") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun validateInput(name: String, email: String, department: String, password: String, confirmPassword: String): Boolean {
        val emailLower = email.lowercase()
        val isValidDomain = emailLower.endsWith("@phinmaed.com") || emailLower.endsWith("@gmail.com")
        
        if (!isValidDomain) {
            Toast.makeText(this, "Use PhinmaEd or Gmail account", Toast.LENGTH_SHORT).show()
            return false
        }

        // Strong Password Regex: 1 Uppercase, 1 Lowercase, 1 Number, 1 Special Char, Min 8 chars
        val passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$".toRegex()
        
        if (!password.matches(passwordRegex)) {
            Toast.makeText(this, "Password must be at least 8 chars with Uppercase, Lowercase, Number, and Special Char", Toast.LENGTH_LONG).show()
            return false
        }

        if (password != confirmPassword) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}
