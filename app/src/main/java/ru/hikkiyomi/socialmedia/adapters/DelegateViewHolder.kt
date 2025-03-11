package ru.hikkiyomi.socialmedia.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.hikkiyomi.socialmedia.models.ListItem

abstract class DelegateViewHolder<T : ListItem>(item: View) : RecyclerView.ViewHolder(item) {
    abstract fun bind(item: T)
}