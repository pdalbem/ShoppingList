package com.ifsp.shoppinglist

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ifsp.shoppinglist.adapters.ShoppingItemAdapter
import com.ifsp.shoppinglist.data.ShoppingItem
import com.ifsp.shoppinglist.viewmodels.ShoppingViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val shoppingViewModel: ShoppingViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fabAddItem: FloatingActionButton = findViewById(R.id.fabAddItem)

        val adapter = ShoppingItemAdapter(listOf(), shoppingViewModel)
        val rvShoppingItems: RecyclerView = findViewById(R.id.rvShoppingItems)
        rvShoppingItems.layoutManager = LinearLayoutManager(this)
        rvShoppingItems.adapter = adapter

        shoppingViewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        rvShoppingItems.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy > 10 && fabAddItem.isShown) {
                    fabAddItem.hide()
                }

                if (dy < -10 && !fabAddItem.isShown) {
                    fabAddItem.show()
                }

                if (!rvShoppingItems.canScrollVertically(-1)) {
                    fabAddItem.show()
                }
            }
        })

        fabAddItem.setOnClickListener {
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setCancelable(false)
            val view = layoutInflater.inflate(R.layout.dialog_add_shopping_item, null)
            alertDialogBuilder.setView(view)

            val alertDialog: AlertDialog = alertDialogBuilder.create()
            alertDialog.show()

            val addDialogButton: MaterialButton = view.findViewById(R.id.buttonAddDialog)
            val cancelDialogButton: MaterialButton = view.findViewById(R.id.buttonCancel)

            addDialogButton.setOnClickListener {

                val etName: EditText = view.findViewById(R.id.etName)
                val etAmount: EditText = view.findViewById(R.id.etAmount)
                val name: String = etName.text.toString()
                val amount: String = etAmount.text.toString()

                if (name.isEmpty() || amount.isEmpty()) {
                    etName.error = "This field is required"
                    etAmount.error = "This field is required"
                    return@setOnClickListener
                } else {
                    shoppingViewModel.upsert(ShoppingItem(name, amount.toInt()))
                    alertDialog.dismiss()
                }
            }

            cancelDialogButton.setOnClickListener {
                alertDialog.dismiss()
            }
        }
    }
}