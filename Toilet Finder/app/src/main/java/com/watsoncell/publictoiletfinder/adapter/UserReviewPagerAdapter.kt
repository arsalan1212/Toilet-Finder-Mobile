package com.watsoncell.publictoiletfinder.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.watsoncell.publictoiletfinder.R
import com.watsoncell.publictoiletfinder.models.UserReviewModel

class UserReviewPagerAdapter(ctx :  Context, reviewList : ArrayList<UserReviewModel>) : PagerAdapter(){
    var userReviewList = reviewList
    var context = ctx

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val view  = LayoutInflater.from(context).inflate(R.layout.layout_review_viewpager,container,false)
        val tvUserName : TextView = view.findViewById(R.id.tvReviewerName)
        val ratingBar : RatingBar = view.findViewById(R.id.userToiletRating)
        val tvUserReviewMsg : TextView = view.findViewById(R.id.tvUserReview)
        tvUserName.text = userReviewList[position].userName
        tvUserReviewMsg.text = userReviewList[position].userReviewMsg
        ratingBar.rating = userReviewList[position].userRating
        container.addView(view)
        Log.d("arsalan","user name: ${tvUserName.text}")
        return view

    }

    override fun isViewFromObject(view: View, p1: Any): Boolean {
        return view == p1
    }


    override fun getCount(): Int {
        return userReviewList.size
    }


    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        container.removeView(view as View)
    }
}