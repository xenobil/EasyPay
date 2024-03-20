package com.example.easypay.Model;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.easypay.Product;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.UUID;

import cielo.orders.domain.CheckoutRequest;
import cielo.orders.domain.Credentials;
import cielo.orders.domain.Order;
import cielo.orders.domain.ResultOrders;
import cielo.sdk.order.OrderManager;
import cielo.sdk.order.ServiceBindListener;
import cielo.sdk.order.payment.PaymentCode;
import cielo.sdk.order.payment.PaymentError;
import cielo.sdk.order.payment.PaymentListener;

public class APILio {
    private Order order;
    private OrderManager orderManager;
    private ResultOrders resultOrders;
    private Credentials credentials;
    private Locale ptBr = new Locale("pt", "BR");
    public volatile static APILio instance;

    public static APILio getInstance() {
        if (instance == null)
            instance = new APILio();
        return instance;
    }


    public ResultOrders getResultOrders() {
        return resultOrders;
    }

    public void setResultOrders(ResultOrders resultOrders) {
        this.resultOrders = resultOrders;
    }

    public void initSDK(Context context) {
        /*======================= Credenciais ======================*/
        credentials = new Credentials("fLpicJJAYjwEKN2LlG8QU4bfMOJKH0hv3Og8e121f0kNW31AiB","G8yiO3xq9B6fFFy01iMi08n1PlSSFL8eyYDpRNqxiGKcZ3MJsu");
        /*=======================  OrderManager ======================*/
        orderManager = new OrderManager(credentials, context);
        ServiceBindListener serviceBindListener = new ServiceBindListener() {

            @Override
            public void onServiceBoundError(Throwable throwable) {
                Log.e("onServiceBoundError", throwable.getMessage());
                //Ocorreu um erro ao tentar se conectar com o serviço OrderManager
            }

            @Override
            public void onServiceBound() {
                Log.e("onServiceBound", "Conectado");
                setResultOrders(orderManager.retrieveOrders(200, 0));
                //Você deve garantir que sua aplicação se conectou com a LIO a partir desse listener
                //A partir desse momento você pode utilizar as funções do OrderManager, caso contrário uma exceção será lançada.
            }

            @Override
            public void onServiceUnbound() {
                Log.e("onServiceUnBound", "Desconectado");
                // O serviço foi desvinculado
            }
        };

        orderManager.bind(context, serviceBindListener);
    }

    public void initOrder() throws Exception {
        order = orderManager.createDraftOrder(String.valueOf(UUID.randomUUID()));
    }

    public void addItemToPay(Payment payment, Product product) throws Exception {
        String valueFormat = NumberFormat.getCurrencyInstance(ptBr).format(product.getPrice());
        valueFormat = valueFormat.substring(3);
        valueFormat = valueFormat.replace(",", "");
        long valueProductLong = Long.parseLong(valueFormat);
        payment.addItem("1", product.getName(), valueProductLong, 1, "UNIDADE");
    }

    public void placeOrder() {
        orderManager.placeOrder(order);
    }

    public void payment(Payment payment, PaymentListener paymentListener) throws Exception {

        CheckoutRequest request;
        final PaymentCode paymentCode;

        if(payment.getPaymentFormat().equals("CRÉDITO") && payment.getNumberParcel() == 1){
            paymentCode = PaymentCode.CREDITO_AVISTA;
        }else if(payment.getPaymentFormat().equals("CRÉDITO") && payment.getNumberParcel() > 1){
            paymentCode = PaymentCode.CREDITO_PARCELADO_LOJA;
        }else{
            paymentCode = PaymentCode.DEBITO_AVISTA;
        }

        if(payment.getNumberParcel() == 1){
            request = new CheckoutRequest.Builder()
                    .orderId(order.getId()) /* Obrigatório */
                    .amount(order.getPrice()) /* Opcional */
                    .paymentCode(paymentCode) /* Opcional */
                    .build();
        }else{
            request = new CheckoutRequest.Builder()
                    .orderId(order.getId()) /* Obrigatório */
                    .amount(order.getPrice()) /* Opcional */
                    .installments(payment.getNumberParcel()) /* Opcional */
                    .paymentCode(paymentCode) /* Opcional */
                    .build();
        }

        orderManager.checkoutOrder(request, new PaymentListener() {
            @Override
            public void onStart() {
                Log.d("SDKClient", "O pagamento começou.");

            }

            @Override
            public void onPayment(@NonNull Order order) {
                Log.d("SDKClient", "Um pagamento foi realizado.");
            }

            @Override public void onCancel() {
                Log.d("SDKClient", "A operação foi cancelada.");

            }

            @Override public void onError(@NonNull PaymentError paymentError) {
                Log.d("SDKClient", "Houve um erro no pagamento.");
            }
        });
    }

    public void addItemToPay(Payment payment) {
    }
}
