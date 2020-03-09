package com.mosso.mainmenu.presentation.view

import android.os.Bundle
import com.mosso.mainmenu.R
import com.mosso.shared.view.BaseActivity

class SearchRoutesActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_search_routes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
    }
}