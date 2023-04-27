package com.example.studybuddy20

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TechniqueAdapter(private val techniques: List<Technique>, private val onItemClick: (Technique) -> Unit) :
    RecyclerView.Adapter<TechniqueAdapter.TechniqueViewHolder>() {

    inner class TechniqueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val techniqueTitle: TextView = itemView.findViewById(R.id.technique_name)
        val techniqueSteps: TextView = itemView.findViewById(R.id.technique_steps)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TechniqueViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_techniques, parent, false)
        return TechniqueViewHolder(view)
    }

    override fun onBindViewHolder(holder: TechniqueViewHolder, position: Int) {
        val technique = techniques[position]
        holder.techniqueTitle.text = technique.title
        holder.techniqueSteps.text = technique.steps.joinToString(separator = "\n")
        holder.itemView.setOnClickListener {
            Log.d("TechniqueAdapter", "Selected technique: ${technique.title}") // add this line
            onItemClick(technique)
        }
    }


    override fun getItemCount(): Int {
        return techniques.size
    }
}
