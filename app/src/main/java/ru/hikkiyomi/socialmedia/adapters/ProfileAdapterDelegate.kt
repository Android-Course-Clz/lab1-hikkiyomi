package ru.hikkiyomi.socialmedia.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import ru.hikkiyomi.socialmedia.R
import ru.hikkiyomi.socialmedia.models.ListItem
import ru.hikkiyomi.socialmedia.models.Profile

class ProfileAdapterDelegate : AdapterDelegate<ListItem> {
    override fun isForViewType(item: ListItem): Boolean = item is Profile

    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): DelegateViewHolder<ListItem> {
        return ProfileViewHolder(
            inflater.inflate(R.layout.profile_info, parent, false)
        )
    }

    class ProfileViewHolder(
        view: View
    ) : DelegateViewHolder<ListItem>(view) {
        private var profile: Profile? = null

        private val pfp = itemView.findViewById<ImageView>(R.id.avatar)
        private val subscribeButton = itemView.findViewById<Button>(R.id.subscribe_button)

        init {
            subscribeButton.setOnClickListener {
                if (subscribeButton.text == "Подписаться") {
                    subscribeButton.text = "Отписаться"
                } else {
                    subscribeButton.text = "Подписаться"
                }
            }
        }

        override fun bind(item: ListItem) {
            when(item) {
                is Profile -> {
                    profile = Profile(item.id, item.profileImageUrl)

                    item.profileImageUrl?.let { url ->
                        Glide
                            .with(itemView)
                            .load(url)
                            .into(pfp)
                    }
                }
                else -> throw IllegalArgumentException("couldn't bind: item is not profile")
            }
        }
    }
}