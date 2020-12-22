package com.learning.mvi.framework.presentation.fragment

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.text.InputFilter
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.learning.mvi.R
import com.learning.mvi.business.domain.model.FactModel
import com.learning.mvi.framework.presentation.MainStateEvent
import com.learning.mvi.framework.presentation.MainViewModel
import com.learning.mvi.business.domain.state.DataState
import com.learning.mvi.business.domain.util.InputFilterMinMax
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_date.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.*


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainFragment constructor(private val someString : String) : Fragment(R.layout.fragment_date) {

	private var type : Int? = null
	private lateinit var myCalendar : Calendar
	private val viewModel : MainViewModel by viewModels()

	override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		subscribeObservers()
		initListeners()
		type = arguments?.getInt("TYPE")
		myCalendar = Calendar.getInstance()
		when (type) {
			0 -> {
				setDateView()
			}
			1 -> {
				setNumberView()
			}
			2 -> {
				setYearView()
			}
			3 -> {
				setRandomFactView()
			}
			4 -> {
				setNumberView()
			}
		}
	}

	private fun setYearView() {
		dateSelector.visibility = View.VISIBLE
		numberEt.visibility = View.GONE
		searchBtn.visibility = View.GONE
		dateTv.text = getString(R.string.year_title)
		viewModel.setStateEvent(MainStateEvent.GetYearFactTrivia, myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH), myCalendar.get(Calendar.YEAR)
			.toLong())
	}

	private fun generateRandomNumber(high : Int) : Long {
		val r = Random()
		val low = 1
		val result = r.nextInt(high - low) + low
		return result.toLong()
	}

	private fun setRandomFactView() {
		dateSelector.visibility = View.GONE
		numberEt.visibility = View.GONE
		searchBtn.visibility = View.GONE
		viewModel.setStateEvent(MainStateEvent.GetRandomNumberFactEvent, 6, 18, generateRandomNumber(999999999))
	}

	private fun setNumberView() {
		numberEt.hint = "Enter number"
		numberEt.filters = arrayOf<InputFilter>(InputFilterMinMax("1", "99999"))
		dateSelector.visibility = View.GONE
		numberEt.visibility = View.VISIBLE
		searchBtn.visibility = View.VISIBLE
		viewModel.setStateEvent(MainStateEvent.GetMathFactEvent, 6, 18, generateRandomNumber(99999))
	}

	private fun setDateView() {
		dateSelector.visibility = View.VISIBLE
		dateTv.text = "Enter date"
		numberEt.visibility = View.GONE
		searchBtn.visibility = View.GONE
		viewModel.setStateEvent(MainStateEvent.GetDateFactEvent, myCalendar.get(Calendar.MONTH) + 1, myCalendar.get(Calendar.DAY_OF_MONTH), 11)
	}

	private fun initListeners() {
		val date = OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
			myCalendar.set(Calendar.YEAR, year)
			myCalendar.set(Calendar.MONTH, monthOfYear)
			myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
			when (type) {
				0 -> {
					viewModel.setStateEvent(MainStateEvent.GetDateFactEvent, monthOfYear + 1, dayOfMonth, myCalendar.get(Calendar.YEAR)
						.toLong())
				}
				2 -> {
					viewModel.setStateEvent(MainStateEvent.GetYearFactTrivia, monthOfYear + 1, dayOfMonth, myCalendar.get(Calendar.YEAR)
						.toLong())
				}
			}
		}

		dateSelector.setOnClickListener {
			DatePickerDialog(this.requireContext(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show()
		}

		searchBtn.setOnClickListener {
			val number = numberEt.text.toString()
			if (!number.isNullOrEmpty()) {
				viewModel.setStateEvent(MainStateEvent.GetMathFactEvent, 6, 18, number.toLong())
			} else {
				numberEt.error = "Enter valid number"
			}
		}
	}

	private fun subscribeObservers() {
		viewModel.dataState.observe(viewLifecycleOwner, Observer {
			when (it) {
				is DataState.Success<FactModel> -> {
					displayProgressBar(false)
					appendDateFact(it.data)
				}
				is DataState.Error              -> {
					displayProgressBar(false)
					displayError(it.exception.message)
				}
				is DataState.Loading            -> {
					displayProgressBar(true)
				}
			}
		})
	}

	private fun displayError(message : String?) {
		if (message != null) {
			displayText.text = message
		} else {
			displayText.text = "Please try again later"
		}
	}

	private fun displayProgressBar(isDisplayed : Boolean) {
		progressBar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
	}

	private fun appendDateFact(factData : FactModel) {
		when (type) {
			0 -> {
				setDateUi(factData)
			}
			1 -> {
				setMatchFactUi(factData)
			}
			2 -> {
				setYearFactUi(factData)
			}
			3 -> {
				setRandomFactUi(factData)
			}
			4 -> {
				setRandomFactUi(factData)
			}
		}
	}

	private fun setRandomFactUi(factData : FactModel) {
		selectedVal1.visibility = View.VISIBLE
		selectedVal2.visibility = View.GONE
		displayText.visibility = View.VISIBLE
		selectedVal1.text = "Number- ${factData.number}"
		displayText.text = factData.text
	}

	private fun setYearFactUi(factData : FactModel) {
		selectedVal1.visibility = View.VISIBLE
		selectedVal2.visibility = View.GONE
		displayText.visibility = View.VISIBLE
		selectedVal1.text = "Year- ${myCalendar.get(Calendar.YEAR).toString()}"
		displayText.text = factData.text
	}

	private fun setMatchFactUi(factData : FactModel) {
		selectedVal1.visibility = View.VISIBLE
		selectedVal2.visibility = View.GONE
		displayText.visibility = View.VISIBLE
		selectedVal1.text = factData.number.toString()
		displayText.text = factData.text
	}

	private fun setDateUi(factData : FactModel) {
		selectedVal1.visibility = View.VISIBLE
		selectedVal2.visibility = View.VISIBLE
		displayText.visibility = View.VISIBLE
		selectedVal1.text = "Month- ${myCalendar.get(Calendar.MONTH) + 1}"
		selectedVal2.text = "Day- ${myCalendar.get(Calendar.DAY_OF_MONTH).toString()}"
		displayText.text = factData.text
	}
}