package com.verindrarizya.suitmediatest.ui.firstscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.verindrarizya.suitmediatest.R
import com.verindrarizya.suitmediatest.databinding.ActivityFirstBinding
import com.verindrarizya.suitmediatest.ui.secondscreen.SecondActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstActivity : AppCompatActivity() {

    private val binding: ActivityFirstBinding by lazy {
        ActivityFirstBinding.inflate(layoutInflater)
    }

    private val viewModel: FirstViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initConfigActionBar()

        initPalindromeDialogObserver()
        initButtonListener()
    }

    private fun initConfigActionBar() {
        supportActionBar?.hide()
    }

    private fun initButtonListener() {
        binding.btnCheck.setOnClickListener {
            val text = binding.edtPalindrome.text.toString()
            if (text.isNotEmpty()) {
                viewModel.checkPalindrome(text)
            } else {
                showToast(getString(R.string.palindrome_warning))
            }
        }

        binding.btnNext.setOnClickListener {
            val username = binding.edtName.text.toString()
            if (username.isNotEmpty()) {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra(SecondActivity.USERNAME_EXTRA, username)
                startActivity(intent)
            } else {
               showToast(getString(R.string.name_warning))
            }
        }
    }

    private fun initPalindromeDialogObserver() {
        val dialog = PalindromeDialog()
        viewModel.isPalindrome.observe(this) {
            it.getContentIfNotHandled()?.let {
                if (it == true) {
                    val args = Bundle()
                    args.putString(PalindromeDialog.PALINDROME_STATE, getString(R.string.palindrome))
                    dialog.arguments = args
                    dialog.show(supportFragmentManager, "palindrome_dialog")
                } else {
                    val args = Bundle()
                    args.putString(PalindromeDialog.PALINDROME_STATE, getString(R.string.not_palindrome))
                    dialog.arguments = args
                    dialog.show(supportFragmentManager, "palindrome_dialog")
                }
            }
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    companion object{
        private const val TAG = "FirstTag"
    }
}