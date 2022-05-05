package com.khyati.practical

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.khyati.practical.databinding.FragmentSecondBinding
import com.khyati.practical.model.MealListResponse
import com.khyati.practical.network.ResponseHandler
import com.khyati.practical.viewmodel.SecondViewModel

/**
 * A simple [Fragment] subclass as the second destination in the
 * navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var secondViewModel: SecondViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        secondViewModel = ViewModelProvider(this).get(SecondViewModel::class.java)
        secondViewModel.getMealFromCategory(arguments?.get("categoryName") as String)
        (requireActivity() as MainActivity).supportActionBar?.title =
            arguments?.get("categoryName") as String
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        secondViewModel.responseLiveMealResponse?.observe(viewLifecycleOwner, Observer {
            if (it == null) {
                return@Observer
            }
            when (it) {
                is ResponseHandler.Loading -> {
                    (requireActivity() as MainActivity).displayProgress(true)
                }
                is ResponseHandler.OnFailed -> {
                    (requireActivity() as MainActivity).displayProgress(false)
                }
                is ResponseHandler.OnSuccessResponse<MealListResponse?> -> {
                    (requireActivity() as MainActivity).displayProgress(false)
                    binding.adapter = MealsViewAdapter(it.response?.meals)
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}