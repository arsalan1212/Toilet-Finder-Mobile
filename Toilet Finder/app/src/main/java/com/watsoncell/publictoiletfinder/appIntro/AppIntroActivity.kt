package com.watsoncell.publictoiletfinder.appIntro

import android.os.Bundle
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro2
import com.watsoncell.publictoiletfinder.fragments.FragmentPermission
import com.watsoncell.publictoiletfinder.utils.Constant

class AppIntroActivity : AppIntro2() {

    val currentPosition = 0

    @Override
    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addSlide(FragmentExplore())
        addSlide(FragmentNavigate())
        addSlide(FragmentUserRate())

        //if app intro open from help in navigation drawer then don't display permission fragment
        if (!Constant.isIntroOpenFromHelp)
            addSlide(FragmentPermission())

        // Hide Skip/Done button.
        isSkipButtonEnabled = false

        /*if (Constant.isIntroOpenFromHelp)
            showDoneButton(true)
        else
            showDoneButton(false)


        setFadeAnimation()*/
        /*  setZoomAnimation()
          setFlowAnimation()
          setSlideOverAnimation()
          setDepthAnimation()*/


    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        finish()
    }
}