package com.shop.payment.service;

import com.shop.payment.mapper.PaymentMapper;
import com.shop.payment.notification.NotificationProducer;
import com.shop.payment.payload.rq.PaymentNotificationRequest;
import com.shop.payment.payload.rq.PaymentRequest;
import com.shop.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

  private final PaymentRepository repository;
  private final PaymentMapper mapper;
  private final NotificationProducer notificationProducer;

  public Integer createPayment(PaymentRequest request) {
    var payment = this.repository.save(this.mapper.toPayment(request));

    this.notificationProducer.sendNotification(
            new PaymentNotificationRequest(
                    request.orderReference(),
                    request.amount(),
                    request.paymentMethod(),
                    request.customer().firstname(),
                    request.customer().lastname(),
                    request.customer().email()
            )
    );
    return payment.getId();
  }
}
