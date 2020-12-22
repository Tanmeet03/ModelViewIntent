package com.learning.mvi.framework.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.learning.mvi.R
import com.learning.mvi.framework.presentation.fragment.MainFragment
import com.learning.mvi.framework.presentation.fragment.MainFragmentFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class FactActivity : AppCompatActivity() {

	private lateinit var bundle : Bundle

	@Inject
	lateinit var fragmentFactory : MainFragmentFactory

	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_fact)

		supportFragmentManager.fragmentFactory = fragmentFactory

		val type = intent.extras?.get("TYPE") as Int

		showFact(type)
	}

	private fun showFact(type : Int) {
		val bundle = Bundle()
		bundle.putInt("TYPE", type)
		supportFragmentManager.beginTransaction()
			.replace(R.id.main_container, MainFragment::class.java, bundle).commit()
	}
}