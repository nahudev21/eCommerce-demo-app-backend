package com.nahudev.electronic_shop.controller;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import com.nahudev.electronic_shop.request.MercadoPagoPaymentRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}")
public class MercadoPagoController {
    @Value("${USER_TOKEN_MERCADOPAGO}")
    private String mercadoPagoToken;

    @PostMapping("/payment/mercado-pago")
    public String getList(@RequestBody List<MercadoPagoPaymentRequest> paymentRequests) {

        if(paymentRequests == null || paymentRequests.isEmpty()) {
            return "error json :/";  // Si la lista está vacía o es null, devuelve un error.
        }

        try {
            MercadoPagoConfig.setAccessToken(mercadoPagoToken);

            List<PreferenceItemRequest> items = new ArrayList<>();

            // Iterar sobre la lista de objetos de pago
            for (MercadoPagoPaymentRequest paymentRequest : paymentRequests) {

                // Crear el objeto de ítem de preferencia para cada pago
                PreferenceItemRequest itemRequest = PreferenceItemRequest
                        .builder()
                        .id(paymentRequest.getId())
                        .title(paymentRequest.getTitle())
                        .description(paymentRequest.getDescription())
                        .categoryId(paymentRequest.getCategoryId())
                        .quantity(paymentRequest.getQuantity())
                        .unitPrice(paymentRequest.getUnitPrice())
                        .currencyId("ARS")
                        .build();

                // Agregar el ítem a la lista de ítems
                items.add(itemRequest);
            }

            // Crear la URL de redirección
            PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest
                    .builder()
                    .success("https://youtube.com")
                    .pending("https://youtube.com")
                    .failure("https://youtube.com")
                    .build();

            // Ensamblar la preferencia final
            PreferenceRequest preferenceRequest = PreferenceRequest
                    .builder()
                    .items(items)
                    .backUrls(backUrls)
                    .build();

            // Crear la preferencia a través del cliente de MercadoPago
            PreferenceClient preferenceClient = new PreferenceClient();
            Preference preference = preferenceClient.create(preferenceRequest);

            // Retornar el ID de la preferencia generada
            return preference.getId();

        } catch (MPException | MPApiException e) {
            return e.getMessage();
        }
    }
}
