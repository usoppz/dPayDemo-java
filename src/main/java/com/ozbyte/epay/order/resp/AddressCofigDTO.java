package com.ozbyte.epay.order.resp;

import lombok.Data;

/**
 * order_address
 *
 * @author alex
 */
@Data
public class AddressCofigDTO {

    /**
     * 钱包地址
     */
    private String address;

    private String signature;

}