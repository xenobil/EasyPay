package com.example.easypay.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.easypay.Model.Payment;
import com.example.easypay.R;

public class PaymentConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_confirmation);

        // Recupera os detalhes do pagamento do intent
        Payment payment = getIntent().getParcelableExtra("paymentDetails");

        // Exibe os detalhes do pagamento nos elementos de interface do usuário
        TextView textViewDescription = findViewById(R.id.text_description);
        TextView textViewValue = findViewById(R.id.text_value);
        TextView textViewPaymentFormat = findViewById(R.id.text_payment_format);
        TextView textViewNumberParcel = findViewById(R.id.text_number_parcel);

        textViewDescription.setText("Descrição: " + payment.getDescription());
        textViewValue.setText("Valor: " + payment.getValueProduct());
        textViewPaymentFormat.setText("Formato de pagamento: " + payment.getPaymentFormat());
        textViewNumberParcel.setText("Número de parcelas: " + payment.getNumberParcel());
    }
}
