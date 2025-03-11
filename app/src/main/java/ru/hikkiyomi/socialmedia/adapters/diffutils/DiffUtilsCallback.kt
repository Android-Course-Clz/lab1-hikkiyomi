package ru.hikkiyomi.socialmedia.adapters.diffutils

import androidx.recyclerview.widget.DiffUtil
import ru.hikkiyomi.socialmedia.models.ListItem

class DiffUtilsCallback(
    private val oldList: List<ListItem>,
    private val newList: List<ListItem>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}