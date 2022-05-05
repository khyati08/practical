package com.khyati.practical

import com.khyati.practical.databinding.RowItemMealListBinding
import com.khyati.practical.model.Categories

class CategoriesViewAdapter(
    mArrayList: ArrayList<Categories>?,
    private val categoriesItemClickListener: CategoriesItemClickListener
) :
    GenericRecyclerViewAdapter<Categories, RowItemMealListBinding>(mArrayList) {

    override val layoutResId: Int
        get() = R.layout.row_item_meal_list

    override fun onBindData(
        model: Categories,
        position: Int,
        dataBinding: RowItemMealListBinding
    ) {
        dataBinding.model = model
        dataBinding.executePendingBindings()
    }

    override fun onItemClick(model: Categories, position: Int) {
        categoriesItemClickListener.itemClick(model, position)
    }
}

interface CategoriesItemClickListener {
    fun itemClick(model: Categories, position: Int)
}