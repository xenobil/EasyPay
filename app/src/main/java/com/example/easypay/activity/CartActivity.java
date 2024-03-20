package com.example.easypay.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easypay.Product;
import com.example.easypay.R;
import com.example.easypay.adapter.CartAdapter;
import com.example.easypay.fragments.MainFragment;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CartAdapter adapter;
    private List<Product> cartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.cart_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cartList = getIntent().getParcelableArrayListExtra("cartList");

        adapter = new CartAdapter(cartList);
        recyclerView.setAdapter(adapter);

        TextView totalTextView = findViewById(R.id.total_text_view);
        double total = calculateTotal(cartList);
        totalTextView.setText("Total: R$" + String.format("%.2f", total));

        TextInputEditText txValueProduct = findViewById(R.id.tx_value_product);
        txValueProduct.setText(String.format("%.2f", total));

        // Botão "Pagar com Cielo"
        findViewById(R.id.pay_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPaymentActivity();
            }
        });
    }

    private double calculateTotal(List<Product> cartList) {
        double total = 0;
        for (Product product : cartList) {
            total += product.getPrice();
        }
        return total;
    }

    private void startPaymentActivity() {
        Intent paymentIntent = new Intent(this, PaymentActivity.class);
        double total = calculateTotal(cartList);
        paymentIntent.putExtra("totalAmount", total);

        // Adiciona o fragmento MainFragment ao intent
        Bundle args = new Bundle();
        args.putDouble("totalAmount", total);
        MainFragment mainFragment = new MainFragment();
        mainFragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, mainFragment)
                .commit();

        startActivity(paymentIntent);
    }


}
