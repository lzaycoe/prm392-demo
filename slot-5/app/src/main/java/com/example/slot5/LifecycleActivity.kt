package com.example.slot5

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity

class LifecycleActivity : ComponentActivity() {
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate called")
        // Initialize UI components and variables
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart called")
        // Activity becomes visible to the user
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume called")
        // Activity starts interacting with the user
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause called")
        // Save state or stop resource-intensive tasks
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop called")
        // Perform cleanup tasks or save states
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy called")
        // Release resources and clean up connections
    }
}
