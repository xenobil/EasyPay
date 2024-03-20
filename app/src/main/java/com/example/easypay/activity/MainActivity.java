package com.example.easypay.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easypay.Model.APILio;
import com.example.easypay.Model.Payment;
import com.example.easypay.Product;
import com.example.easypay.adapter.ProductAdapter;
import com.example.easypay.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ProductAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<Product> productList;
    private List<Product> cartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList<>();
        productList.add(new Product("Sanduíche", 10.00));
        productList.add(new Product("Cachorro Quente", 8.50));
        productList.add(new Product("Refrigerante", 4.50));
        productList.add(new Product("Suco", 4.00));

        adapter = new ProductAdapter(productList, this);
        recyclerView.setAdapter(adapter);

        cartList = new ArrayList<>();
    }

    @Override
    public void onItemClick(Product product) {
        addToCart(product);
    }

    private void addToCart(Product product) {
        cartList.add(product);
        Toast.makeText(this, product.getName() + " adicionado ao carrinho", Toast.LENGTH_SHORT).show();
    }

    public void openPaymentActivity(View view) {
        // Criar um novo objeto de pagamento
        Payment payment = new Payment();

        // Adicionar cada item do carrinho ao pedido
        for (Product product : cartList) {
            try {
                APILio.getInstance().addItemToPay(payment, product);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Erro ao adicionar item ao pedido", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        // Abrir a atividade de pagamento
        Intent intent = new Intent(this, PaymentActivity.class);
        startActivity(intent);
    }

    public void openCartActivity(View view) {
        Intent intent = new Intent(this, CartActivity.class);
        intent.putParcelableArrayListExtra("cartList", new ArrayList<>(cartList));
        startActivity(intent);
    }
}
