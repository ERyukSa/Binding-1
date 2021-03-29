package com.medium.binding.src.main.home.room.create

import android.os.Bundle
import android.view.View
import com.medium.binding.R
import com.medium.binding.config.ApplicationClass
import com.medium.binding.config.BaseFragment
import com.medium.binding.databinding.FragmentHomeCreateBinding

class HomeCreateFragment: BaseFragment<FragmentHomeCreateBinding>(
    FragmentHomeCreateBinding::bind,
    R.layout.fragment_home_create
){
    private val sp = ApplicationClass.sSharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // FrameLayout에 띄운 프래그먼트 하단 뷰로 터치 이벤트가 전달되는 것을 막는다
        binding.homeRoomCreateRoot.setOnTouchListener { v, event ->
            v.performClick()
            true
        }


    }
}