package com.example.itcourses.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.data.models.CourseModel
import com.example.itcourses.R
import com.example.itcourses.databinding.ContentItemBinding

class CoursesAdapter(
    private var courses: List<CourseModel>,
    private val onItemClick: (CourseModel) -> Unit,
    private val onFavoriteClick: (CourseModel) -> Unit,
    private val isCourseFavorite: suspend (Int) -> Boolean
) : RecyclerView.Adapter<CoursesAdapter.CourseViewHolder>() {

    fun updateCourses(newCourses: List<CourseModel>) {
        courses = newCourses
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding = ContentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = courses[position]
        holder.bind(course)
        holder.itemView.setOnClickListener {
            onItemClick(course)
        }
    }

    override fun getItemCount(): Int = courses.size

    inner class CourseViewHolder(private val binding: ContentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(course: CourseModel) {

            binding.courseInfoTitle.text = course.title
            if (course.price == "-") {
                binding.courseInfoPrice.text = "Бесплатно"
            } else {
                binding.courseInfoPrice.text = course.price
            }

            Glide.with(binding.courseImage.context).load(course.cover).into(binding.courseImage)

            updateFavoriteIcon(course.favorite)

            binding.buttonAddToFavorites.setOnClickListener {
                onFavoriteClick(course)
            }

            // Item OnClick
            binding.root.setOnClickListener {
                onItemClick(course)
            }
        }

        private fun updateFavoriteIcon(isFavorite: Boolean) {
            binding.buttonAddToFavorites.setImageResource(
                if (isFavorite) R.drawable.ic_bookmark_favorite
                else R.drawable.ic_bookmark
            )
        }
    }
}
