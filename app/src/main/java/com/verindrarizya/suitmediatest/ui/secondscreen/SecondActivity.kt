package com.verindrarizya.suitmediatest.ui.secondscreen

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.verindrarizya.suitmediatest.databinding.ActivitySecondBinding
import com.verindrarizya.suitmediatest.ui.thirdscreen.ThirdActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondActivity : AppCompatActivity() {

    private val binding: ActivitySecondBinding by lazy {
        ActivitySecondBinding.inflate(layoutInflater)
    }

    private val getUsername = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            data?.getStringExtra(ThirdActivity.USERNAME_RESULT)?.let { onUsernameResultPopulate(it) }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            onUsernameResultPopulate(savedInstanceState.getString(SELECTED_USERNAME, ""))
        }

        initToolbarConfig()

        // Set Name text
        binding.tvName.text = intent.getStringExtra(USERNAME_EXTRA)

        // Set button listener
        binding.btnChooseUser.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            getUsername.launch(intent)
        }
    }

    private fun initToolbarConfig() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun onUsernameResultPopulate(username: String) {
        binding.tvSelectedUsername.text = username
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(SELECTED_USERNAME, binding.tvSelectedUsername.text.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    companion object {
        const val USERNAME_EXTRA = "username_extra"
        private const val SELECTED_USERNAME = "selected_username"
    }
}