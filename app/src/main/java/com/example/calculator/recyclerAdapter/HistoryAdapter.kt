package com.example.calculator.recyclerAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.calculator.R
import com.example.calculator.schemas.History

class HistoryAdapter(
    private val dataset: List<History>
) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.history_result)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.history_data, parent, false)
        return HistoryViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text = item.result
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}