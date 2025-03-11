package ru.hikkiyomi.socialmedia.models

interface ListItem {
    val id: Long
}

data class Profile(
    override val id: Long,
    val profileImageUrl: String?
) : ListItem

data class Post(
    override val id: Long,
    val text: String,
    val imageUrl: String?,
    var likeCount: Int,
    var commentCount: Int
) : ListItem
