package de.eymers.matthias.jokerapichallenge.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.eymers.matthias.jokerapichallenge.core.Constant.DEVICE_OFFLINE_ERROR_MESSAGE
import de.eymers.matthias.jokerapichallenge.data.remote.throwable.GeneralApiException
import de.eymers.matthias.jokerapichallenge.domain.model.JokeEvent
import de.eymers.matthias.jokerapichallenge.domain.usecase.GetJokesRemoteUC
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokesListViewModel @Inject constructor(
    private val getJokesUseCase: GetJokesRemoteUC
) : ViewModel() {

    private val _jokeEvent = MutableStateFlow<JokeEvent>(JokeEvent.Loading)
    val jokeEvent = _jokeEvent.asStateFlow()

    fun fetchJokes(isOnline: Boolean) = viewModelScope.launch {
        if (isOnline) {
            getJokesUseCase.invoke().collectLatest { event ->
                _jokeEvent.emit(event)
            }

        } else {
            _jokeEvent.emit(JokeEvent.Error(cause = GeneralApiException(errorMessage = DEVICE_OFFLINE_ERROR_MESSAGE)))
        }
    }
}