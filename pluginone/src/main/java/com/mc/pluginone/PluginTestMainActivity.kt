package com.mc.pluginone

import com.mc.pluginfun.byInterfaces.KPluginBaseActivity

class PluginTestMainActivity : KPluginBaseActivity() {

    override fun findView() {

    }

    override fun loadFunction() {
    }

    override fun getContentViewLayoutID(): Int = R.layout.activity_plugin_test_main

}
