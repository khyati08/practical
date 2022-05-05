package com.khyati.practical

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.khyati.practical.databinding.FragmentFirstBinding
import com.khyati.practical.model.Categories
import com.khyati.practical.model.CategoryListResponse
import com.khyati.practical.network.ResponseHandler
import com.khyati.practical.viewmodel.FirstViewModel

/**
 * A simple [Fragment] subclass as the default destination in the
 * navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var firstViewModel: FirstViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        firstViewModel = ViewModelProvider(this).get(FirstViewModel::class.java)
        (requireActivity() as MainActivity).supportActionBar?.title = getString(R.string.app_name)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstViewModel.responseLiveCategoryResponse?.observe(viewLifecycleOwner, Observer {
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
                is ResponseHandler.OnSuccessResponse<CategoryListResponse?> -> {
                    (requireActivity() as MainActivity).displayProgress(false)
                    binding.adapter = CategoriesViewAdapter(
                        it.response?.categories,
                        object : CategoriesItemClickListener {
                            override fun itemClick(model: Categories, position: Int) {
                                findNavController().navigate(
                                    FirstFragmentDirections.actionFirstFragmentToSecondFragment(
                                        model.strCategory!!
                                    )
                                )
                            }
                        })
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}