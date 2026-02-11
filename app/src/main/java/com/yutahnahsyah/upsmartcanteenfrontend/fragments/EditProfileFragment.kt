package com.yutahnahsyah.upsmartcanteenfrontend.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.yutahnahsyah.upsmartcanteenfrontend.R

class EditProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }

        val etFullName = view.findViewById<EditText>(R.id.etFullName)
        val etEmail = view.findViewById<EditText>(R.id.etEmail)
        val etDepartment = view.findViewById<EditText>(R.id.etDepartment)
        val etPassword = view.findViewById<EditText>(R.id.etPassword)
        val btnSave = view.findViewById<MaterialButton>(R.id.btnSaveProfile)

        // Pre-fill with existing data (In a real app, this would come from a database or shared preferences)
        etFullName.setText("Zyrel Gabriel Maningding")
        etEmail.setText("zyre.maningding.up@phinmaed.com")
        etDepartment.setText("CITE")

        btnSave.setOnClickListener {
            val name = etFullName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val department = etDepartment.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || department.isEmpty()) {
                Toast.makeText(context, "Please fill in all required fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Simple validation for password if they tried to change it
            if (password.isNotEmpty() && password.length < 6) {
                Toast.makeText(context, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Logic to save the updated profile would go here (API call or Local DB)
            Toast.makeText(context, "Profile updated successfully!", Toast.LENGTH_SHORT).show()
            
            // Navigate back to the previous fragment
            parentFragmentManager.popBackStack()
        }
    }
}
