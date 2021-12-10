package com.foodfix.management.service;

import com.foodfix.management.client.OrderTakeClient;
import com.foodfix.management.dto.OrderDTO;
import com.foodfix.management.model.Sale;
import com.foodfix.management.model.SaleDetail;
import com.foodfix.management.repository.PlateRepository;
import com.foodfix.management.repository.SaleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final PlateRepository plateRepository;
    private final OrderTakeClient orderTakeClient;

    public SaleService(SaleRepository saleRepository, PlateRepository plateRepository, OrderTakeClient orderTakeClient) {
        this.saleRepository = saleRepository;
        this.plateRepository = plateRepository;
        this.orderTakeClient = orderTakeClient;
    }

    public Page<Sale> findAll(Pageable pageable) {
        return saleRepository.findAll(pageable);
    }

    @Transactional
    public Sale register(OrderDTO dto) throws Exception {
        Sale sale = new Sale();
        List<SaleDetail> saleDetails = new ArrayList<>();

        dto.getDetails().forEach(item -> {
            SaleDetail detail = new SaleDetail();
            detail.setSale(sale);
            plateRepository.findById(item.getPlate().getId()).ifPresent(detail::setPlate);
            detail.setQuantity(item.getQuantity());
            detail.setObservation(item.getObservation());
            detail.setPrice(detail.getPlate().getPrice());
            detail.setAmount(detail.getPrice().multiply(new BigDecimal(item.getQuantity())));
            saleDetails.add(detail);
        });
        sale.setDetails(saleDetails);
        sale.setTableNumber(dto.getTableNumber());

        BigDecimal total = saleDetails
                .stream()
                .map(SaleDetail::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        sale.setTotal(total);

        Sale newSale = saleRepository.save(sale);
        orderTakeClient.delete(dto.getId());

        return newSale;
    }

}
