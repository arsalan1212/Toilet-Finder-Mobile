package com.watsoncell.publictoiletfinder

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.watsoncell.publictoiletfinder.appIntro.AppIntroActivity
import com.watsoncell.publictoiletfinder.utils.AppPreference
import com.watsoncell.publictoiletfinder.utils.LocalHelper
import com.watsoncell.publictoiletfinder.utils.MyBounceInterpolator
import kotlinx.android.synthetic.main.activity_splash_screen.*


class SplashScreenActivity : AppCompatActivity() {

    val COUNT_DOWN_INTERVAL: Long = 1000
    val MILLIS_IN_FUTURE: Long = 5000
    var preference: AppPreference? = null

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocalHelper.onAttach(newBase!!))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        preference = AppPreference()

        Log.d("arsalan","is dialog shown: ${preference!!.isAppIntroShown()}")

        //if app intro is not shown then go to app intro activity
        if (!preference!!.isAppIntroShown()) {
            val intent = Intent(this, AppIntroActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
            return
        }

        setContentView(R.layout.activity_splash_screen)

        val logoAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce_anim)

        val interpolator = MyBounceInterpolator(0.2, 20.0)
        logoAnimation.setInterpolator(interpolator)

        imgMarker.animation = logoAnimation

      /*  val tvAnimation1 = AnimationUtils.loadAnimation(this, R.anim.alpha_1000_anim)
        tv_Kpk.animation = tvAnimation1
*/
        val tvAnimation2 = AnimationUtils.loadAnimation(this, R.anim.alpha_1000_anim)
        tv_appName.animation = tvAnimation2

        object : CountDownTimer(MILLIS_IN_FUTURE, COUNT_DOWN_INTERVAL) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            }
        }.start()
    }

}
