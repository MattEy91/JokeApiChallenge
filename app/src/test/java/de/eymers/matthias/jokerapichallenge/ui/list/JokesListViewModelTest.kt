package de.eymers.matthias.jokerapichallenge.ui.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import de.eymers.matthias.jokerapichallenge.core.Constant.DEVICE_OFFLINE_ERROR_MESSAGE
import de.eymers.matthias.jokerapichallenge.data.remote.throwable.GeneralApiException
import de.eymers.matthias.jokerapichallenge.domain.model.Joke
import de.eymers.matthias.jokerapichallenge.domain.model.JokeEvent
import de.eymers.matthias.jokerapichallenge.domain.usecase.GetJokesRemoteUC
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain

@OptIn(ExperimentalCoroutinesApi::class)
class JokesListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: JokesListViewModel
    private val getJokesUseCase: GetJokesRemoteUC = mockk()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = JokesListViewModel(getJokesUseCase)
    }

    @Test
    fun `fetchJokes emits JokeEvent_Loading and then JokeEvent_Error when offline`() = runTest {
        val expectedEvent = JokeEvent.Error(cause = GeneralApiException(errorMessage = DEVICE_OFFLINE_ERROR_MESSAGE))

        viewModel.jokeEvent.test {
            viewModel.fetchJokes(isOnline = false)

            assertEquals(JokeEvent.Loading, awaitItem())
            assertEquals(expectedEvent, awaitItem())

            expectNoEvents()
        }
    }

    @Test
    fun `fetchJokes emits JokeEvent_Loading and then JokeEvent_Success when online`() = runTest {
        val jokes = listOf(Joke(0, "Why did the chicken cross the road?", "To get to the other side!"))
        coEvery { getJokesUseCase.invoke() } returns flowOf(JokeEvent.Success(jokes))

        viewModel.jokeEvent.test {
            viewModel.fetchJokes(isOnline = true)

            assertEquals(JokeEvent.Loading, awaitItem())
            assertEquals(JokeEvent.Success(jokes), awaitItem())

            coVerify { getJokesUseCase.invoke() }
            expectNoEvents()
        }
    }
}
