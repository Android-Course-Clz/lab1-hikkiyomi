package ru.hikkiyomi.socialmedia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.hikkiyomi.socialmedia.adapters.CompositeAdapter
import ru.hikkiyomi.socialmedia.adapters.PostAdapterDelegate
import ru.hikkiyomi.socialmedia.adapters.ProfileAdapterDelegate
import ru.hikkiyomi.socialmedia.models.ListItem
import ru.hikkiyomi.socialmedia.models.Post
import ru.hikkiyomi.socialmedia.models.Profile

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)

        val content = listOf<ListItem>(
            Profile(
                id = -1,
                profileImageUrl =
                    "https://sun9-61.userapi.com/impf/byTiuiCzlcw-7HQqNI8VeOhaKVPrGm-Sdt9wsQ/banGDBotKVM.jpg?size=731x1000&quality=96&sign=432b2466a268e6e51f46eb47c80ce26a&type=album",
            ),
            Post(
                id = 1,
                text = "Легендарное возвращение стены",
                imageUrl = null,
                likeCount = 999999,
                commentCount = 999999,
            ),
            Post(
                id = 2,
                text = "Я приостанавливаю работу телеграма в связи с созданием этой соцсети)",
                imageUrl = "https://camo.githubusercontent.com/2f2b0c82cb9dc05f15a7b3724637a4862a98f06ad90260c6577fa873571475e6/68747470733a2f2f646f776e6c6f61642e6c6f676f2e77696e652f6c6f676f2f54656c656772616d5f28736f667477617265292f54656c656772616d5f28736f667477617265292d4c6f676f2e77696e652e706e67",
                likeCount = -100000,
                commentCount = 999999,
            ),
            Post(
                id = 3,
                text = "Тест на мужчину: это круто?",
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d7/Android_robot.svg/1745px-Android_robot.svg.png",
                likeCount = 123123,
                commentCount = 10,
            ),
            Post(
                id = 4,
                text = "детка ты выполнила задание на 5 с плюсом",
                imageUrl = null,
                likeCount = 987654,
                commentCount = 555555,
            )
        )

        val profileView = findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = CompositeAdapter(ProfileAdapterDelegate(), PostAdapterDelegate())

        profileView.layoutManager = LinearLayoutManager(this)
        profileView.adapter = adapter

        adapter.updateData(content)
    }
}