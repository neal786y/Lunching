package com.neal786y.mvparchitecture.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<V : BaseView<Any>, P : BasePresenter<V>> : AppCompatActivity(), BaseView<Any> {

    protected var presenter: P? = null

    protected abstract fun getLayout(): Int

    protected abstract fun initPresenter(): P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        presenter = initPresenter()
        presenter?.attachView(this as V)
    }

    override fun onResume() {
        super.onResume()
        presenter?.attachView(this as V)

    }

    override fun onPause() {
        super.onPause()
        //presenter?.detachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.detachView()
    }

    inline fun <T : Any> ifLet(vararg elements: T?, closure: (List<T>) -> Unit) {
        if (elements.all { it != null }) {
            closure(elements.filterNotNull())
        }
    }
}