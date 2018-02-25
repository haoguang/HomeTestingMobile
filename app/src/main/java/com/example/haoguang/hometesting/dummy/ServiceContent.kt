package com.example.haoguang.hometesting.dummy

import java.util.ArrayList
import java.util.HashMap

/**
 * Created by haoguang on 25/02/2018.
 */
object ServiceContent {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<Service> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, Service> = HashMap()



    init {
        // Add some sample items.
        addItem(Service("Locker Service", "This is LockerService. This is LockerService. This is LockerService. This is LockerService. This is LockerService. This is LockerService. This is LockerService.", "DSA", "TARUC", "1. Go DSA \n2. Fill up form"))
        addItem(Service("Accomodation Service", "This is Accomodation Service", "DSA", "TARUC", "1. Go DSA \n2. Fill up form"))
        addItem(Service("Financial Aid", "This is Financial Aid", "DSA", "TARUC", "1. Go DSA \n2. Fill up form"))
        addItem(Service("ABC Service", "This is ABC Service", "DSA", "Block ABC", "1. Go DSA \n2. Fill up form"))
    }

    private fun addItem(item: Service) {
        ITEMS.add(item)
        ITEM_MAP.put(item.title, item)
    }

    /**
     * A dummy item representing a piece of content.
     */
    data class Service(val title: String, val description: String, val department: String, val address: String, val proceduce: String) {
        override fun toString(): String = title
    }
}