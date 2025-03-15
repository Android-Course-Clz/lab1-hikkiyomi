package ru.hikkiyomi.socialmedia.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import ru.hikkiyomi.socialmedia.R
import ru.hikkiyomi.socialmedia.models.ListItem
import ru.hikkiyomi.socialmedia.models.Post

class PostAdapterDelegate : AdapterDelegate<ListItem> {
    override fun isForViewType(item: ListItem): Boolean = item is Post

    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): DelegateViewHolder<ListItem> {
        return PostViewHolder(
            inflater.inflate(R.layout.post, parent, false)
        )
    }

    class PostViewHolder(
        view: View
    ) : DelegateViewHolder<ListItem>(view) {
        private var post: Post? = null
        private var isLiked: Boolean = false

        private val text = itemView.findViewById<TextView>(R.id.post_text)
        private val image = itemView.findViewById<ImageView>(R.id.post_image)
        private val likeCount = itemView.findViewById<TextView>(R.id.like_count)
        private val commentCount = itemView.findViewById<TextView>(R.id.comments_count)

        private val likeButton = itemView.findViewById<ImageView>(R.id.like_button)
        private val commentButton = itemView.findViewById<ImageView>(R.id.comments_button)

        init {
            likeButton.setOnClickListener {
                if (post == null) {
                    throw NullPointerException("cannot handle like button click: post is null")
                }

                if (isLiked) {
                    isLiked = false
                    post!!.likeCount--
                    likeCount.text = post!!.likeCount.toString()
                } else {
                    isLiked = true
                    post!!.likeCount++
                    likeCount.text = post!!.likeCount.toString()
                }
            }

            commentButton.setOnClickListener {
                if (post == null) {
                    throw NullPointerException("cannot handle comment button click: post is null")
                }

                post!!.commentCount++
                commentCount.text = post!!.commentCount.toString()
            }
        }

        override fun bind(item: ListItem) {
            when(item) {
                is Post -> {
                    post = Post(
                        item.id,
                        item.text,
                        item.imageUrl,
                        item.likeCount,
                        item.commentCount
                    )

                    text.text = item.text
                    likeCount.text = item.likeCount.toString()
                    commentCount.text = item.commentCount.toString()

                    item.imageUrl?.let { url ->
                        Glide
                            .with(itemView)
                            .load(url)
                            .into(image)
                    } ?: run {
                        image.visibility = View.GONE
                    }
                }
                else -> throw IllegalArgumentException("couldn't bind: item is not post")
            }
        }
    }
}