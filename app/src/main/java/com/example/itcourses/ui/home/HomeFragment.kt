package com.example.itcourses.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.itcourses.R
import com.example.itcourses.databinding.FragmentHomeBinding
import com.example.itcourses.ui.filter.FilterBottomSheetFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModel()

    private lateinit var coursesAdapter: CoursesAdapter

    private var currentPage = 1
    private var isLoading = false // Page loading flag
    private val minItemsBeforeLoading = 5

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        binding.coursesRecyclerView.layoutManager = LinearLayoutManager(context)

        binding.searchView.buttonFilter.setOnClickListener {
            showFilterDialog()
        }

        coursesAdapter = CoursesAdapter(emptyList(), onItemClick = { course ->
            val bundle = Bundle().apply {
                putParcelable("course", course)
            }
            findNavController().navigate(
                R.id.action_homeFragment_to_CourseInformationFragment, bundle
            )
        }, onFavoriteClick = { course ->
            homeViewModel.toggleFavorite(course)
        }, isCourseFavorite = { courseId ->
            homeViewModel.favoriteCourseRepository.isCourseFavorite(
                courseId
            )
        })


        binding.coursesRecyclerView.adapter = coursesAdapter

        // Load first page
        homeViewModel.fetchCourses(currentPage)

        // Course list observe
        homeViewModel.courses.observe(viewLifecycleOwner) { courses ->
            coursesAdapter.updateCourses(courses)
            isLoading = false
            showLoading(false)
        }

        // List end observe
        homeViewModel.paginationInfo.observe(viewLifecycleOwner) { meta ->
            if (!meta.hasNext) {
                isLoading = true
            }
        }

        // ScrollListener
        binding.coursesRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val visibleItemCount = layoutManager.childCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!isLoading && (firstVisibleItemPosition + visibleItemCount >= totalItemCount || totalItemCount < minItemsBeforeLoading)) {
                    showLoading(true)
                    isLoading = true

                    val pagesToLoad = 3

                    homeViewModel.fetchCourses(currentPage, pagesToLoad)
                    currentPage += pagesToLoad
                }
            }
        })

        return root
    }


    private fun showLoading(show: Boolean) {
        if (show) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun showFilterDialog() {


        val filterDialog = FilterBottomSheetFragment { category, difficulty, price ->
            Log.d(
                "Filter",
                "Applying filters: category=$category, difficulty=$difficulty, price=$price"
            )
            currentPage = 1
            isLoading = true
            showLoading(true)
            homeViewModel.fetchCourses(currentPage, 1, price, category, difficulty)
        }
        filterDialog.show(childFragmentManager, "FilterBottomSheetDialog")


    }


}

