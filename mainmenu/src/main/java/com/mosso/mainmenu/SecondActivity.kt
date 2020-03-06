package com.mosso.mainmenu

import android.os.Bundle
import com.mosso.shared.view.BaseActivity

class SecondActivity: BaseActivity() {
    override fun getLayoutId(): Int = R.layout.layout2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
    }
}