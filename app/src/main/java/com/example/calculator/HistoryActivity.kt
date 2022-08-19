package com.example.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculator.Databases.HistoryDB
import com.example.calculator.databinding.ActivityHistoryBinding
import com.example.calculator.recyclerAdapter.HistoryAdapter
import com.example.calculator.schemas.History
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        val historyDB = HistoryDB.getHistoryDB(this).getHistoryDao()

        GlobalScope.launch {
            val historyData: List<History> = historyDB.getAll()
//            withContext(Dispatchers.Main) {
//                binding.historyList.adapter = HistoryAdapter(historyData)
//            }
            runOnUiThread {
                binding.historyList.adapter = HistoryAdapter(historyData)
            }
        }
    }
}