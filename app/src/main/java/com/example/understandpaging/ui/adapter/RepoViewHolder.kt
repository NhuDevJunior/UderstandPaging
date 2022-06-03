package com.example.understandpaging.ui.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.understandpaging.R
import com.example.understandpaging.databinding.RepoViewItemBinding
import com.example.understandpaging.model.Repo

/**
 * View Holder for a [Repo] RecyclerView list item.
 */
class RepoViewHolder(repoViewItemBinding: RepoViewItemBinding) : RecyclerView.ViewHolder(repoViewItemBinding.root) {
    private var repo: Repo? = null
    private var binding: RepoViewItemBinding = repoViewItemBinding

    init {
        binding.root.setOnClickListener {
            repo?.url?.let { url ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                binding.root.context.startActivity(intent)
            }
        }
    }

    fun bind(repo: Repo?) {
        if (repo == null) {
            val resources = itemView.resources
            binding.apply {
                repoName.text = resources.getString(R.string.loading)
                repoDescription.visibility = View.GONE
                repoLanguage.visibility = View.GONE
                repoStars.text = resources.getString(R.string.unknown)
                repoForks.text = resources.getString(R.string.unknown)
            }
        } else {
            showRepoData(repo)
        }
    }

    private fun showRepoData(repo: Repo) {
        this.repo = repo
        binding.apply {
            repoName.text = repo.fullName

            // if the description is missing, hide the TextView
            var descriptionVisibility = View.GONE
            if (repo.description != null) {
                repoDescription.text = repo.description
                descriptionVisibility = View.VISIBLE
            }
            repoDescription.visibility = descriptionVisibility

            repoStars.text = repo.stars.toString()
            repoForks.text = repo.forks.toString()

            // if the language is missing, hide the label and the value
            var languageVisibility = View.GONE
            if (!repo.language.isNullOrEmpty()) {
                val resources = binding.root.context.resources
                repoLanguage.text = resources.getString(R.string.language, repo.language)
                languageVisibility = View.VISIBLE
            }
            repoLanguage.visibility = languageVisibility
        }
    }

    companion object {
        fun create(parent: ViewGroup): RepoViewHolder {
            val itemBinding = RepoViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return RepoViewHolder(itemBinding)
        }
    }
}