package com.example.administrator.kweather.domain

/**
 * Created by Administrator on 2016/11/30.
 * 函数式接口，执行一个行为然后返回泛型类型
 */
public interface Command<T> {
    fun execute():T
}