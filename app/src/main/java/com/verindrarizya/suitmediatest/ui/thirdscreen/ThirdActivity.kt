package com.verindrarizya.suitmediatest.ui.thirdscreen

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.verindrarizya.suitmediatest.R
import com.verindrarizya.suitmediatest.data.entity.User
import com.verindrarizya.suitmediatest.databinding.ActivityThirdBinding
import com.verindrarizya.suitmediatest.util.setGone
import com.verindrarizya.suitmediatest.util.setVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThirdActivity : AppCompatActivity() {

    private val binding: ActivityThirdBinding by lazy {
        ActivityThirdBinding.inflate(layoutInflater)
    }

    private val viewModel: ThirdViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initToolbarConfig()
        initRvConfig()
        initSwipeRefreshAction()
        initObservers()
    }

    private fun initObservers() {
        viewModel.isLoading.observe(this) {
            if (it == true) {
                binding.rvUsers.setGone()
                binding.tvStatusStatement.setGone()
                binding.progressBar.setVisible()
            } else {
                binding.progressBar.setGone()
            }
        }

        viewModel.isError.observe(this) {
            if (it == true) {
                binding.tvStatusStatement.text = getString(R.string.error_statement)
                binding.tvStatusStatement.setVisible()
            } else {
                binding.tvStatusStatement.setGone()
            }
        }

        viewModel.users.observe(this) {
            if (it.isNotEmpty()) {
                initAdapter(it)
                binding.rvUsers.setVisible()
            } else {
                binding.tvStatusStatement.text = getString(R.string.empty_user_stament)
                binding.tvStatusStatement.setVisible()
            }
        }
    }

    private fun initToolbarConfig() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initRvConfig() {
        with(binding.rvUsers) {
            addItemDecoration(DividerItemDecoration(this@ThirdActivity, DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@ThirdActivity)
            clipToPadding = false
        }
    }

    private fun initSwipeRefreshAction() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getUsers()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun initAdapter(users: List<User>) {
        val userAdapter = UserAdapter {
            val returnIntent = Intent()
            returnIntent.putExtra(USERNAME_RESULT, "${it.firstName} ${it.lastName}")
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }

        userAdapter.submitList(users)

        binding.rvUsers.adapter = userAdapter
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    companion object {
        const val USERNAME_RESULT = "username_result"
    }
}