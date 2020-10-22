package com.example.basemvvmframe.base

import com.example.basemvvmframe.http.ApiInterface

open class BaseModel {
    /**
     * 直接获取ApiInterface实例，不需要每一个页面都创建独立的Model
     * 缺点：Model层和VM层存在关联性
     */
    fun getApi(): ApiInterface {
        return ApiInterface.create()
    }
}