package com.example.slot9
// User.kt (Data Model)
data class User(val id: Int, val name: String, val email: String)

// UserRepository.kt
class UserRepository {
    private val users = listOf(
        User(1, "Alice", "alice@example.com"),
        User(2, "Bob", "bob@example.com"),
        User(3, "Charlie", "charlie@example.com")
    )

    fun getUsers(): List<User> = users
}

