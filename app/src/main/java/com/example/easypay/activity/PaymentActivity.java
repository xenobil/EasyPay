package com.example.easypay.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.easypay.Model.APILio;
import com.example.easypay.Model.Payment;
import com.example.easypay.R;
import com.example.easypay.fragments.MainFragment;

import cielo.orders.domain.Order;
import cielo.sdk.order.payment.PaymentError;
import cielo.sdk.order.payment.PaymentListener;

public class PaymentActivity extends AppCompatActivity implements MainFragment.OnPaymentListener {

    private Button startPaymentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        startPaymentButton = findViewById(R.id.pagar);

        startPaymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new MainFragment())
                    .commit();
        }
    }

    private void startPayment() {
        try {
            MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            if (mainFragment == null) {
                Log.e("PaymentActivity", "Fragment not found");
                return;
            }

            Payment payment = mainFragment.returnPayment();
            if (payment == null) {
                Log.e("PaymentActivity", "Payment object not found");
                return;
            }

            APILio apiLio = APILio.getInstance();
            if (apiLio == null) {
                Log.e("PaymentActivity", "Lio API not initialized");
                return;
            }

            apiLio.initSDK(this);
            apiLio.initOrder();
            apiLio.addItemToPay(payment);
            apiLio.placeOrder();

            PaymentListener paymentListener = new PaymentListener() {
                @Override
                public void onStart() {
                    Log.d("SDKClient", "Payment started.");
                }

                @Override
                public void onPayment(Order order) {
                    Log.d("SDKClient", "Payment completed.");
                }

                @Override
                public void onCancel() {
                    Log.d("SDKClient", "Operation canceled.");
                }

                @Override
                public void onError(PaymentError paymentError) {
                    Log.d("SDKClient", "Error in payment.");
                }
            };

            apiLio.payment(payment, paymentListener);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("PaymentActivity", "Error starting payment: " + e.getMessage());
        }
    }

    @Override
    public void onPaymentCompleted() {
        // Handle payment completed event
    }
}
