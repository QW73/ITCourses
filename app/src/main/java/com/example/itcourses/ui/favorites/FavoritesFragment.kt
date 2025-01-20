package com.example.itcourses.ui.favorites

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.mapper.toCourseModel
import com.example.itcourses.R
import com.example.itcourses.databinding.FragmentFavoritesBinding
import com.example.itcourses.ui.home.CoursesAdapter
import kotlinx.coroutines.runBlocking
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null

    private val favoritesViewModel: FavoritesViewModel by viewModel()


    private lateinit var adapter: CoursesAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupRecyclerView()
        observeViewModel()



        return root
    }

    private fun setupRecyclerView() {
        binding.favoritesRecyclerView.layoutManager = LinearLayoutManager(context)

        adapter = CoursesAdapter(
            courses = emptyList(),
            onItemClick = { course ->
                val bundle = Bundle().apply {
                    putParcelable("course", course)
                }
                findNavController().navigate(
                    R.id.action_favoritesFragment_to_CourseInformationFragment, bundle
                )
                Log.d("FavoritesFragment", "Move to Course Information Fragment")
            },
            onFavoriteClick = { course ->
                favoritesViewModel.removeFromFavorites(course.id)
                Log.d("FavoritesFragment", "Removed from favorites: ${course.title}")
            },
            isCourseFavorite = { courseId ->
                runBlocking {
                    favoritesViewModel.favoriteCourseRepository.isCourseFavorite(courseId)
                }
            }
        )

        binding.favoritesRecyclerView.adapter = adapter
    }

    private fun observeViewModel() {
        favoritesViewModel.favorites.observe(viewLifecycleOwner) { favorites ->
            val courseModels = favorites.map { it.toCourseModel() }
            adapter.updateCourses(courseModels)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}