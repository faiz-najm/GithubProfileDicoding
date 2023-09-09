package com.bangkit.githubprofiledicoding.ui.detailprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bangkit.githubprofiledicoding.databinding.FragmentDashboardBinding

class DetailProfileFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val detailProfileViewModel =
            ViewModelProvider(this).get(DetailProfileViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        detailProfileViewModel.text.observe(viewLifecycleOwner) {
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}