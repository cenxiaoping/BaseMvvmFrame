package com.example.basemvvmframe.viewmodels

import com.example.basemvvmframe.base.BaseModel
import com.example.basemvvmframe.bean.BaseBean
import com.example.basemvvmframe.bean.UserInfo

class MainModel : BaseModel() {

    /**
     * 每个页面单独创建自己的Model，Model层和VM层解除耦合，
     * 万一Model发送变化，只需要在Model层对应的方法修改即可，返回值保持一致，就不会影响到VM上层调用
     *
     */
    suspend fun login(phone: String, smsCode: String, imageCode: String): BaseBean<UserInfo>? {
        return getApi().login(phone, smsCode, imageCode)
    }

}