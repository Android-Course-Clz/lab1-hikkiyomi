package ru.hikkiyomi.socialmedia.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.hikkiyomi.socialmedia.models.ListItem

interface AdapterDelegate<T : ListItem> {
    fun isForViewType(item: ListItem): Boolean
    fun createViewHolder(inflater: LayoutInflater, parent: ViewGroup): DelegateViewHolder<T>
}