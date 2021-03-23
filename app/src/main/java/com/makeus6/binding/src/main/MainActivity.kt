package com.makeus6.binding.src.main

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.makeus6.binding.R
import com.makeus6.binding.config.BaseActivity
import com.makeus6.binding.databinding.ActivityMainBinding
import com.makeus6.binding.src.main.home.HomeFragment
import com.makeus6.binding.src.main.menu.MenuFragment
import com.makeus6.binding.src.main.my_page.MyPageFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private var homeFragment: HomeFragment? = null
    private var menuFragment: MenuFragment? = null
    private var myPageFragment: MyPageFragment? = null

    // 뒤로가기 2번 눌러 종료할 때 사용
    private val FINISH_INTERVAL_TIME: Long = 2000
    private var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 처음 화면이 켜졌을 때 활성화될 하단 버튼 - 실제 화면이 아닌 버튼에만 적용
        binding.mainBtmNav.selectedItemId = R.id.menu_main_btm_nav_home

        // 홈 프래그먼트로 진입
        if(homeFragment == null){
            homeFragment = HomeFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.main_frm, HomeFragment())
                .commitAllowingStateLoss()
        }


        // 아이콘 틴트는 셀렉터로 적용
        binding.mainBtmNav.itemIconTintList = null

        // 탭을 선택했을 때 프래그먼트 화면 전환
        binding.mainBtmNav.setOnNavigationItemSelectedListener(
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_main_btm_nav_home -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, homeFragment!!, "home")
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.menu_main_btm_nav_menu -> {
                        if(menuFragment == null){
                            menuFragment = MenuFragment()
                        }
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, menuFragment!!, "menu")
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.menu_main_btm_nav_my_page -> {
                        if(myPageFragment == null){
                            myPageFragment = MyPageFragment()
                        }
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, myPageFragment!!, "myPage")
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }
                }
                false
            })
    }

    override fun onBackPressed() {

        when(binding.mainBtmNav.selectedItemId){
            R.id.menu_main_btm_nav_home -> {
                finishOnBackPressed()
            }
            R.id.menu_main_btm_nav_menu -> {

                if(menuFragment!!.childFragmentManager.backStackEntryCount > 0){
                    menuFragment!!.childFragmentManager.popBackStack()
                }else{
                    finishOnBackPressed()
                }

            }
            R.id.menu_main_btm_nav_my_page -> {

                // 마이페이지 프래그먼트에 childFragment가 있으면 childFragment만 종료
                if(myPageFragment!!.childFragmentManager.backStackEntryCount > 0){
                    myPageFragment!!.childFragmentManager.popBackStack()
                }else{
                    finishOnBackPressed()   // 두 번 눌러 앱 종료
                }
            }
        }
    }

    // 두 번 눌러 앱 종료
    private fun finishOnBackPressed() {
        val tempTime: Long = System.currentTimeMillis()       // 현재 시간과 1970년 1월 1일 시간 차
        val intervalTime: Long = tempTime - backPressedTime

        if(intervalTime >= 0 && FINISH_INTERVAL_TIME >= intervalTime){
            super.onBackPressed()
            Log.d("로그", "onBackPressed() called")
        }else{
            backPressedTime = tempTime
            showCustomToast("뒤로 버튼을 한번 더 누르시면 종료됩니다.")
        }
    }
}