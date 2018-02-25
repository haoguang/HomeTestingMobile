package com.example.haoguang.hometesting


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_expandable_list_view.view.*
import kotlinx.android.synthetic.main.contactlist_parent.view.*



/**
 * Created by haoguang on 22/02/2018.
 */
class ContactListAdapter (val context: Context, val contactListTitle: List<String>, val contactListContent: HashMap<String,List<String>>) : BaseExpandableListAdapter(){
    override fun getGroup(p0: Int): Any {
        return contactListTitle.get(p0)
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getGroupView(p0: Int, p1: Boolean, p2: View?, p3: ViewGroup?): View {
        val headerTitle: String = getGroup(p0) as String
        var view: View
        if(p2 == null){
            val inflaters: LayoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflaters.inflate(R.layout.contactlist_parent, null)
        }else{
            view = p2
        }

        view.contactListHeader.text= headerTitle
        return view
    }

    override fun getChildrenCount(p0: Int): Int {
        return contactListContent.get(contactListTitle.get(p0))!!.size
    }

    override fun getChild(p0: Int, p1: Int): Any {
        return contactListContent.get(contactListTitle.get(p0))!!.get(p1)
    }

    override fun getGroupId(p0: Int): Long {
        return p0 as Long
    }

    override fun getChildView(p0: Int, p1: Int, p2: Boolean, p3: View?, p4: ViewGroup?): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getChildId(p0: Int, p1: Int): Long {
        return p1 as Long
    }

    override fun getGroupCount(): Int {
        return contactListTitle.size
    }

}

