package com.example.univofseoul_meal

import com.example.univofseoul_meal.Model.recyclerviewModel
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.recycler_item.view.*

class reyclerview(val recyclerviewModel : recyclerviewModel) : Item<ViewHolder>() {
    override fun getLayout(): Int = R.layout.recycler_item

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.apply {
            titleText_item.text = recyclerviewModel.title
            descText_item.text = recyclerviewModel.desc
        }
    }
}