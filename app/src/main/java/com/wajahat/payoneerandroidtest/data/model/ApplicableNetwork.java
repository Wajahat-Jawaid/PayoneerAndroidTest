package com.wajahat.payoneerandroidtest.data.model;

import java.net.URL;
import java.util.Map;

import lombok.Getter;

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
@Getter
public class ApplicableNetwork {

    private final String label;
    private final Map<String, URL> links;

    public ApplicableNetwork(String label, Map<String, URL> links) {
        this.label = label;
        this.links = links;
    }

    /**
     * Dummy fields to support the design
     */
    private final String expiry = "03/24";
    private final String last4Digits = "6492";
    private final String cardHolder = "John Doe";
}