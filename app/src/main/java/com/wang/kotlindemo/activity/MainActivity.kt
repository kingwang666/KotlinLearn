package com.wang.kotlindemo.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.wang.kotlindemo.R
import com.wang.kotlindemo.adapter.TextAdapter
import com.wang.kotlindemo.function.getLogger
import com.wang.kotlindemo.interfaces.OnRecyclerClickListener
import com.wang.kotlindemo.mdoel.TextMode

class MainActivity : AppCompatActivity(), OnRecyclerClickListener {

    lateinit var mRecyclerView: RecyclerView

    lateinit var mItemArray: MutableList<TextMode>

    val logger = getLogger()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        mItemArray = mutableListOf(
                TextMode(KotlinBaseActivity::class.java, "Base"),
                TextMode(ClassExtendsActivity::class.java, "类和继承"),
                TextMode(FieldActivity::class.java, "属性和字段"),
                TextMode(ExtendActivity::class.java, "扩展"),
                TextMode(ObjectActivity::class.java, "对象"),
                TextMode(ByActivity::class.java, "委托"),
                TextMode(FunActivity::class.java, "函数"),
                TextMode(CoroutinesActivity::class.java, "协程")
        )
        mRecyclerView = findViewById(R.id.recycler_view) as RecyclerView
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = TextAdapter(mItemArray, this)


    }


    override fun onClick(itemType: Int, position: Int) {
        val model = mItemArray[position]
        val intent = Intent()
        intent.setClass(this, model.clazz)
        startActivity(intent)
    }

}
