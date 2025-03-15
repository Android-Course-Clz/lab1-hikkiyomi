package ru.hikkiyomi.socialmedia.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.hikkiyomi.socialmedia.adapters.diffutils.DiffUtilsCallback
import ru.hikkiyomi.socialmedia.models.ListItem

class CompositeAdapter<T : ListItem>(
    private val delegates: List<AdapterDelegate<T>>
) : RecyclerView.Adapter<DelegateViewHolder<T>>() {
    constructor(vararg delegates: AdapterDelegate<T>) : this(delegates.toList())

    private var data: List<T> = emptyList()

    override fun getItemViewType(position: Int): Int {
        val item = data[position]
        val delegateIndex = delegates.indexOfFirst { it.isForViewType(item) }

        if (delegateIndex < 0) {
            throw IllegalArgumentException("Unsupported view type, $position $item")
        }

        return delegateIndex
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DelegateViewHolder<T> {
        return delegates[viewType].createViewHolder(LayoutInflater.from(parent.context), parent)
    }

    override fun onBindViewHolder(
        holder: DelegateViewHolder<T>,
        position: Int
    ) = holder.bind(data[position])

    override fun getItemCount(): Int = data.size

    fun updateData(newData: List<T>) {
        val callback = DiffUtilsCallback(
            oldList = data,
            newList = newData
        )

        val result = DiffUtil.calculateDiff(callback)

        this.data = newData
        result.dispatchUpdatesTo(this)
    }
}