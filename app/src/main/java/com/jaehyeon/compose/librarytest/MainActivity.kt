package com.jaehyeon.compose.librarytest

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.jaehyeon.compose.bannerlibrary.BannerItem
import com.jaehyeon.compose.bannerlibrary.BannerType
import com.jaehyeon.compose.librarytest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arrayListOf<BannerItem>().apply {
            add(
                BannerItem(
                    drawable = R.drawable.banner_red,
                    hyperLink = "https://www.google.co.kr"
                )
            )
            add(
                BannerItem(
                    drawable = R.drawable.banner_green,
                    hyperLink = "https://www.naver.com"
                )
            )
            add(
                BannerItem(
                    drawable = R.drawable.banner_blue,
                    hyperLink = "https://developer.android.com/"
                )
            )
        }.also {
            binding.banner.apply {
                setBannerItem(it, BannerType.RES)
                delay = 1500L
                listener = { url ->
                    try {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                    } catch (t: Throwable) {
                        Log.e("TAG", "onCreate: ${t.localizedMessage}")
                    }
                }
                if (!this.isRunning)
                    this.start()
            }
        }

        binding.editTag.apply {
            this.text.hint = "입력해라."
            this.text.setHintTextColor(Color.GRAY)
            this.highlightColor = Color.MAGENTA
        }
    }
}