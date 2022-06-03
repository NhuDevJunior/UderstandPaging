package com.example.understandpaging.ui

import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import com.example.understandpaging.api.GithubService
import com.example.understandpaging.repository.GithubRepository
import com.example.understandpaging.ui.viewmodel.ViewModelFactory

object Injection {
    /**
     * Creates an instance of [GithubRepository] based on the [GithubService] and a
     * [GithubLocalCache]
     */
    private fun provideGithubRepository(): GithubRepository {
        return GithubRepository(GithubService.create())
    }

    /**
     * Provides the [ViewModelProvider.Factory] that is then used to get a reference to
     * [ViewModel] objects.
     */
    fun provideViewModelFactory(owner: SavedStateRegistryOwner): ViewModelProvider.Factory {
        return ViewModelFactory(owner, provideGithubRepository())
    }
}