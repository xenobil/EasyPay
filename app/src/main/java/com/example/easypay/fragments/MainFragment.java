package com.example.easypay.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import com.example.easypay.Model.Payment;
import com.example.easypay.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Locale;

import cielo.orders.domain.Order;

public class MainFragment extends Fragment {
    private RadioGroup paymentFormat, numberParcel;
    private RadioButton credit, debit, parcel1x, parcel2x, parcel3x, parcel4x, parcel5x, parcel6x, parcel7x, parcel8x, parcel9x, parcel10x, parcel11x, parcel12x;
    private TextInputEditText description, valueProduct;
    private Locale ptBr = new Locale("pt", "BR");
    private View viewFrag;
    private Payment payment;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewFrag = inflater.inflate(R.layout.activity_main, container, false);
        payment = new Payment() {
            @Override
            public void onPayment(Order paidOrder) {

            }
        };

        valueProduct = viewFrag.findViewById(R.id.tx_value_product);
        numberParcel = viewFrag.findViewById(R.id.rg_number_parcel);
        paymentFormat = viewFrag.findViewById(R.id.rg_payment_format);
        credit = viewFrag.findViewById(R.id.rb_credit);
        debit = viewFrag.findViewById(R.id.rb_debit);
        description = viewFrag.findViewById(R.id.tx_description_product);
        parcel1x = viewFrag.findViewById(R.id.rb_parcel1x);
        parcel2x = viewFrag.findViewById(R.id.rb_parcel2x);
        parcel3x = viewFrag.findViewById(R.id.rb_parcel3x);
        parcel4x = viewFrag.findViewById(R.id.rb_parcel4x);
        parcel5x = viewFrag.findViewById(R.id.rb_parcel5x);
        parcel6x = viewFrag.findViewById(R.id.rb_parcel6x);
        parcel7x = viewFrag.findViewById(R.id.rb_parcel7x);
        parcel8x = viewFrag.findViewById(R.id.rb_parcel8x);
        parcel9x = viewFrag.findViewById(R.id.rb_parcel9x);
        parcel10x = viewFrag.findViewById(R.id.rb_parcel10x);
        parcel11x = viewFrag.findViewById(R.id.rb_parcel11x);
        parcel12x = viewFrag.findViewById(R.id.rb_parcel12x);

        radioGroupPaymentFormat();
        radioGroupNumberParcel();
        valueProductChange();

        return viewFrag;
    }

    private void radioGroupPaymentFormat() {
        paymentFormat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_credit) {
                    if (!valueProduct.getText().toString().isEmpty() && !valueProduct.getText().toString().equals("R$0,00")) {
                        numberParcel.setVisibility(View.VISIBLE);
                        checkNumberParcel();
                        payment.setPaymentFormat("CRÉDITO");
                    } else {
                        valueProduct.setError("Preencha o campo para continuar");
                    }
                } else {
                    numberParcel.setVisibility(View.GONE);
                    payment.setNumberParcel(1);
                    payment.setPaymentFormat("DÉBITO");
                }
            }
        });
    }

    private void radioGroupNumberParcel() {
        numberParcel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_parcel1x) {
                    payment.setNumberParcel(1);
                    payment.setTextNumberParcel(parcel1x.getText().toString());
                } else if (checkedId == R.id.rb_parcel2x) {
                    payment.setNumberParcel(2);
                    payment.setTextNumberParcel(parcel2x.getText().toString());
                } else if (checkedId == R.id.rb_parcel3x) {
                    payment.setNumberParcel(3);
                    payment.setTextNumberParcel(parcel3x.getText().toString());
                } else if (checkedId == R.id.rb_parcel4x) {
                    payment.setNumberParcel(4);
                    payment.setTextNumberParcel(parcel4x.getText().toString());
                } else if (checkedId == R.id.rb_parcel5x) {
                    payment.setNumberParcel(5);
                    payment.setTextNumberParcel(parcel5x.getText().toString());
                } else if (checkedId == R.id.rb_parcel6x) {
                    payment.setNumberParcel(6);
                    payment.setTextNumberParcel(parcel6x.getText().toString());
                } else if (checkedId == R.id.rb_parcel7x) {
                    payment.setNumberParcel(7);
                    payment.setTextNumberParcel(parcel7x.getText().toString());
                } else if (checkedId == R.id.rb_parcel8x) {
                    payment.setNumberParcel(8);
                    payment.setTextNumberParcel(parcel8x.getText().toString());
                } else if (checkedId == R.id.rb_parcel9x) {
                    payment.setNumberParcel(9);
                    payment.setTextNumberParcel(parcel9x.getText().toString());
                } else if (checkedId == R.id.rb_parcel10x) {
                    payment.setNumberParcel(10);
                    payment.setTextNumberParcel(parcel10x.getText().toString());
                } else if (checkedId == R.id.rb_parcel11x) {
                    payment.setNumberParcel(11);
                    payment.setTextNumberParcel(parcel11x.getText().toString());
                } else if (checkedId == R.id.rb_parcel12x) {
                    payment.setNumberParcel(12);
                    payment.setTextNumberParcel(parcel12x.getText().toString());
                }
            }
        });
    }

    private void checkNumberParcel() {
        final Activity activity = getActivity();
        final InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void valueProductChange() {
        // Implemente a lógica para monitorar a mudança de valor do produto
    }

    protected void hideSoftwareKeyboard() {
        // Implemente a lógica para ocultar o teclado virtual
    }

    public Payment returnPayment() {
        payment.setDescription(description.getText().toString());
        payment.setValueProduct(Float.parseFloat(valueProduct.getText().toString()));
        // Implemente a lógica para preencher os outros campos do pagamento, se necessário
        return payment;
    }

    public interface OnPaymentListener {
        void onPaymentCompleted();
    }
}
