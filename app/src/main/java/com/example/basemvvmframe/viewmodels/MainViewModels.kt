package com.example.basemvvmframe.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.basemvvmframe.base.BaseViewModels
import kotlinx.coroutines.launch

class MainViewModels constructor(application: Application) :
    BaseViewModels<MainModel>(application) {

    var liveData: MutableLiveData<String> = MutableLiveData<String>()

    /**
     * 需要单独创建自己的Model
     */
    fun login() {
        launch {
            val login = mModel.login("1", "2", "3")
            if (login?.code == 1) {
                liveData.postValue("登录成功")
            }
        }
    }

    /**
     * 统一用BaseModel，可以不需要单独创建各自的Model
     */
    fun login2() {
        launch {

            val login = mModel.getApi().login("1", "2", "3")
            if (login?.code == 1) {
                liveData.postValue("登录成功")
            }
        }
    }
}