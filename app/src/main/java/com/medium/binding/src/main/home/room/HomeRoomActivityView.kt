package com.medium.binding.src.main.home.room

import com.medium.binding.config.BaseResponse
import com.medium.binding.src.main.home.models.GetCommentsResponse

interface HomeRoomActivityView {

    // 최신순 책방 댓글 콜백 함수
    fun onGetNewestWRSuccess(response: GetCommentsResponse)

    fun onGetNewestWRFailure(message: String)

    // 북마크순 책방 댓글 콜백 함수
    fun onGetMarkedWRSuccess(response: GetCommentsResponse)

    fun onGetMarkedWRFailure(message: String)

    // 북마크 변경 콜백 함수
    fun onPatchWBookmarkSuccess(response: BaseResponse, itemPos: Int)

    fun onPatchWBookmarkFailure(message: String)

    // 책방 글 발행 콜백 함수
    fun onPostCommentsSuccess(response: BaseResponse)

    fun onPostCommentsFailure(message: String)
}