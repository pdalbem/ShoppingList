package com.ifsp.shoppinglist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ifsp.shoppinglist.R
import com.ifsp.shoppinglist.data.ShoppingItem
import com.ifsp.shoppinglist.viewmodels.ShoppingViewModel
import com.google.android.material.button.MaterialButton

class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currentShoppingItem: ShoppingItem = items[position]

        val itemName: TextView = holder.itemView.findViewById(R.id.tvName)
        itemName.text = currentShoppingItem.name

        val itemAmount: TextView = holder.itemView.findViewById(R.id.tvAmount)
        itemAmount.text = currentShoppingItem.amount.toString()

        val buttonDelete: MaterialButton = holder.itemView.findViewById(R.id.buttonDelete)
        buttonDelete.setOnClickListener {
            viewModel.delete(currentShoppingItem)
        }

        val buttonAdd: MaterialButton = holder.itemView.findViewById(R.id.buttonAdd)
        buttonAdd.setOnClickListener {
            currentShoppingItem.amount++
            viewModel.upsert(currentShoppingItem)
        }

        val buttonRemove: MaterialButton = holder.itemView.findViewById(R.id.buttonRemove)
        buttonRemove.setOnClickListener {
            if (currentShoppingItem.amount > 0) {
                currentShoppingItem.amount--
                viewModel.upsert(currentShoppingItem)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


    inner class ShoppingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}