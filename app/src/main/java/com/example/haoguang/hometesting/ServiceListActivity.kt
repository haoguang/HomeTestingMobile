package com.example.haoguang.hometesting

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.haoguang.hometesting.dummy.DummyContent
import com.example.haoguang.hometesting.dummy.ServiceContent
import kotlinx.android.synthetic.main.activity_service_list.*
import kotlinx.android.synthetic.main.service_list_content.view.*

import kotlinx.android.synthetic.main.service_list.*

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ServiceDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class ServiceListActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var mTwoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_list)

        setSupportActionBar(toolbar)
        toolbar.title = title


        if (service_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true
        }

        setupRecyclerView(service_list)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = SimpleItemRecyclerViewAdapter(this, ServiceContent.ITEMS, mTwoPane)
    }

    class SimpleItemRecyclerViewAdapter(private val mParentActivity: ServiceListActivity,
                                        private val mValues: List<ServiceContent.Service>,
                                        private val mTwoPane: Boolean) :
            RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        private val mOnClickListener: View.OnClickListener

        init {
            mOnClickListener = View.OnClickListener { v ->
                val item = v.tag as ServiceContent.Service
                if (mTwoPane) {
                    val fragment = ServiceDetailFragment().apply {
                        arguments = Bundle().apply {
                            putString(ServiceDetailFragment.ARG_ITEM_ID, item.title)
                        }
                    }
                    mParentActivity.supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.service_detail_container, fragment)
                            .commit()
                } else {
                    val intent = Intent(v.context, ServiceDetailActivity::class.java).apply {
                        putExtra(ServiceDetailFragment.ARG_ITEM_ID, item.title)
                    }
                    v.context.startActivity(intent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.service_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = mValues[position]
            holder.mIdView.text = item.title

            with(holder.itemView) {
                tag = item
                setOnClickListener(mOnClickListener)
            }
        }

        override fun getItemCount(): Int {
            return mValues.size
        }

        inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
            val mIdView: TextView = mView.id_text
        }
    }
}
