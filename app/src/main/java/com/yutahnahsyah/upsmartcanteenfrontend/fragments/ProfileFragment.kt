package com.yutahnahsyah.upsmartcanteenfrontend.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.yutahnahsyah.upsmartcanteenfrontend.OnboardingActivity
import com.yutahnahsyah.upsmartcanteenfrontend.R
import com.yutahnahsyah.upsmartcanteenfrontend.auth.Login

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Onboarding Help Icon (Question Mark)
        val onboardingInfoIcon = view.findViewById<ImageView>(R.id.onboardingInfoIcon)
        onboardingInfoIcon.setOnClickListener {
            val intent = Intent(requireContext(), OnboardingActivity::class.java)
            intent.putExtra("forceShow", true)
            startActivity(intent)
        }

        val editProfileCard = view.findViewById<MaterialCardView>(R.id.editProfileCard)
        editProfileCard.setOnClickListener {
            findNavController().navigate(R.id.action_nav_profile_to_nav_edit_profile)
        }

        val orderHistoryCard = view.findViewById<MaterialCardView>(R.id.orderHistoryCard)
        orderHistoryCard.setOnClickListener {
            findNavController().navigate(R.id.action_nav_profile_to_nav_history)
        }

        val paymentCard = view.findViewById<MaterialCardView>(R.id.paymentCard)
        paymentCard.setOnClickListener {
            findNavController().navigate(R.id.action_nav_profile_to_nav_payment)
        }

        val notificationsCard = view.findViewById<MaterialCardView>(R.id.notificationsCard)
        notificationsCard.setOnClickListener {
            findNavController().navigate(R.id.action_nav_profile_to_nav_notifications)
        }

        val aboutCard = view.findViewById<MaterialCardView>(R.id.aboutCard)
        aboutCard.setOnClickListener {
            findNavController().navigate(R.id.action_nav_profile_to_nav_about)
        }

        val helpCard = view.findViewById<MaterialCardView>(R.id.helpCard)
        helpCard.setOnClickListener {
            findNavController().navigate(R.id.action_nav_profile_to_nav_support)
        }

        val logoutButton = view.findViewById<MaterialButton>(R.id.logoutButton)
        logoutButton.setOnClickListener {
            // Log out logic: Navigate back to Login activity and clear the task stack
            val intent = Intent(requireActivity(), Login::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            requireActivity().finish()
        }
    }
}
