package com.example.itcourses.ui.course_information

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.core.utils.DateUtils
import com.example.core.utils.openExternalLink
import com.example.itcourses.databinding.FragmentCourseInformationBinding
import com.example.data.models.CourseModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Locale

class CourseInformationFragment : Fragment() {

    private var _binding: FragmentCourseInformationBinding? = null

    private val courseInformationViewModel: CourseInformationViewModel by viewModel()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCourseInformationBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.courseInformationFragmentGoBack.setOnClickListener {
            findNavController().navigateUp()
        }


        val course = arguments?.getParcelable<CourseModel>("course") ?: return

        binding.buttonGoToPlatform.setOnClickListener {
            val stepikUrl = "https://stepik.org/course/${course.id}"
            Log.d("CourseInformationFragment", "Stepik URL: $stepikUrl")
            openExternalLink(requireContext(), stepikUrl)
        }

        binding.courseInformationFragmentDate.text = course.date

        val formattedDate = DateUtils.formatDateRU(course.date)
        binding.courseInformationFragmentDate.text = formattedDate

        if (course.learners == 0) {
            binding.courseInformationFragmentStarContainer.visibility = View.GONE
        } else {
            binding.courseInformationFragmentStar.visibility = View.VISIBLE
            binding.courseInformationFragmentStar.text =
                String.format(Locale.getDefault(), "%,d", course.learners)
        }

        binding.courseInformationFragmentTitle.text = course.title
        binding.courseInformationDescription.text =
            Html.fromHtml(course.description, Html.FROM_HTML_MODE_COMPACT)

        binding.courseInformationAuthor.text = course.authors.toString()

        course.cover?.let { coverUrl ->
            Glide.with(binding.courseInformationFragmentImage.context).load(coverUrl)
                .into(binding.courseInformationFragmentImage)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}