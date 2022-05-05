package com.khyati.practical

import com.khyati.practical.databinding.RowItemCategoriesBinding
import com.khyati.practical.model.Meals

class MealsViewAdapter(
    mArrayList: ArrayList<Meals>?
) :
    GenericRecyclerViewAdapter<Meals, RowItemCategoriesBinding>(mArrayList) {

    override val layoutResId: Int
        get() = R.layout.row_item_categories

    override fun onBindData(
        model: Meals,
        position: Int,
        dataBinding: RowItemCategoriesBinding
    ) {
        dataBinding.model = model
        dataBinding.executePendingBindings()
    }

    override fun onItemClick(model: Meals, position: Int) {

    }
}
