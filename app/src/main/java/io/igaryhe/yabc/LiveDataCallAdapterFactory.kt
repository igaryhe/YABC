package io.igaryhe.yabc

import androidx.lifecycle.LiveData
import retrofit2.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

class LiveDataCallAdapterFactory : CallAdapter.Factory() {

    override fun get(returnType: Type?, annotations: Array<out Annotation>?, retrofit: Retrofit?): CallAdapter<*, *>? {
        if(returnType !is ParameterizedType){
            throw IllegalArgumentException("返回值需为参数化类型")
        }
        val returnClass = getRawType(returnType)
        if(returnClass != LiveData::class.java){
            throw IllegalArgumentException("返回值不是LiveData类型")
        }
        val type = getParameterUpperBound(0, returnType)
        return LiveDataCallAdapter<Any>(type)
    }

    class LiveDataCallAdapter<R>(var type:Type):CallAdapter<R,LiveData<R>>{
        override fun adapt(call: Call<R>?): LiveData<R> {
            return object:LiveData<R>(){
                val flag = AtomicBoolean(false)
                override fun onActive() {
                    super.onActive()
                    if(flag.compareAndSet(false,true)){
                        call!!.enqueue(object: Callback<R> {
                            override fun onFailure(call: Call<R>?, t: Throwable?) {
                                postValue(null)
                            }
                            override fun onResponse(call: Call<R>?, response: Response<R>?) {
                                postValue(response?.body())
                            }
                        })
                    }
                }
            }
        }
        override fun responseType(): Type {
            return type
        }
    }
}
