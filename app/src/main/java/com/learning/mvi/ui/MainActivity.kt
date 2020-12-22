package com.learning.mvi.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.learning.mvi.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		dateFact.setOnClickListener {
			openFactPage(0)
		}
		mathFact.setOnClickListener {
			openFactPage(1)
		}
		yearFact.setOnClickListener {
			openFactPage(2)
		}
		randomFact.setOnClickListener {
			openFactPage(3)
		}
		triviaFact.setOnClickListener {
			openFactPage(4)
		}
	}

	private fun openFactPage(type : Int) {
		val intent = Intent(this, FactActivity::class.java)
		intent.putExtra("TYPE", type)
		startActivity(intent)
	}
}