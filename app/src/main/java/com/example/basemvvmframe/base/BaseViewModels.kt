package com.example.basemvvmframe.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import java.lang.reflect.*
import java.lang.reflect.Array

open class BaseViewModels<M : BaseModel> constructor(application: Application) :
    AndroidViewModel(application), CoroutineScope by MainScope() {
    protected var mModel: M

    init {
        mModel = initModel()!!
    }

    private fun initModel(): M? {
        try {
            val superClass = javaClass.genericSuperclass
            val actualTypeArguments =
                (superClass as ParameterizedType?)!!.actualTypeArguments
            if (actualTypeArguments != null && actualTypeArguments.isNotEmpty()) {
                val type = actualTypeArguments[0]
                val clazz: Class<*> = getRawType(type)!!
                val constructors =
                    clazz.constructors
                if (constructors.size >= 0) {
                    return constructors[0].newInstance() as M
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null

//        val mCreate = clz.getDeclaredConstructor()
//        mCreate. isAccessible = true
//        return mCreate. newInstance()
    }

    // type不能直接实例化对象，通过type获取class的类型，然后实例化对象
    open fun getRawType(type: Type?): Class<*>? {
        return if (type is Class<*>) {
            type as Class<*>?
        } else if (type is ParameterizedType) {
            val parameterizedType =
                type as ParameterizedType
            val rawType = parameterizedType.rawType
            rawType as Class<*>
        } else if (type is GenericArrayType) {
            val componentType =
                (type as GenericArrayType).genericComponentType
            Array.newInstance(getRawType(componentType), 0).javaClass
        } else if (type is TypeVariable<*>) {
            Any::class.java
        } else if (type is WildcardType) {
            getRawType((type as WildcardType).upperBounds[0])
        } else {
            val className = type?.javaClass?.name ?: "null"
            throw IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <$type> is of type $className")
        }
    }
}