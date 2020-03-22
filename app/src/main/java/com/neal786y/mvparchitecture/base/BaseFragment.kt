package com.neal786y.mvparchitecture.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import android.view.View

abstract class BaseFragment<V : BaseView<Any>, P : BasePresenter<V>> : androidx.fragment.app.Fragment() {

    protected var presenter: P? = null

    protected abstract fun initPresenter(): P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPresenter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter?.attachView(this as V)
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        initDestroy()
    }

    fun initDestroy() {
        presenter?.detachView()
    }

}