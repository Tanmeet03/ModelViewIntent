package com.learning.mvi.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.learning.mvi.model.FactModel
import com.learning.mvi.repository.MainRepository
import com.learning.mvi.util.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainViewModel
@ViewModelInject constructor(private val mainRepository : MainRepository,
                             @Assisted
                             private val savedStateHandle : SavedStateHandle) : ViewModel() {
	private val _dataState : MutableLiveData<DataState<FactModel>> = MutableLiveData()

	//getter
	val dataState : LiveData<DataState<FactModel>>
		get() = _dataState

	//setter
	fun setStateEvent(mainStateEvent : MainStateEvent, month : Int, date : Int, number : Long) {
		viewModelScope.launch {
			when (mainStateEvent) {
				is MainStateEvent.GetDateFactEvent -> {
					mainRepository.getDateFact(month, date).onEach { dataState ->
						_dataState.value = dataState
					}.launchIn(viewModelScope)
				}
				is MainStateEvent.GetMathFactEvent -> {
					mainRepository.getMathFact(number).onEach { dataState ->
						_dataState.value = dataState
					}.launchIn(viewModelScope)
				}
				is MainStateEvent.GetRandomNumberFactEvent -> {
					mainRepository.getRandomFact().onEach { dataState ->
						_dataState.value = dataState
					}.launchIn(viewModelScope)
				}
				is MainStateEvent.GetTriviaFactEvent -> {
					mainRepository.getTriviaFact(number).onEach { dataState ->
						_dataState.value = dataState
					}.launchIn(viewModelScope)
				}
				is MainStateEvent.GetYearFactTrivia -> {
					mainRepository.getYearFact(number).onEach { dataState ->
						_dataState.value = dataState
					}.launchIn(viewModelScope)
				}
				is MainStateEvent.None -> {
				}
			}
		}
	}
}

sealed class MainStateEvent {
	object GetMathFactEvent : MainStateEvent()
	object GetRandomNumberFactEvent : MainStateEvent()
	object GetTriviaFactEvent : MainStateEvent()
	object GetYearFactTrivia : MainStateEvent()
	object GetDateFactEvent : MainStateEvent()
	object None : MainStateEvent()
}