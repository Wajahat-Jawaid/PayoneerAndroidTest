package com.wajahat.payoneerandroidtest.data.model;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.Getter;

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
@Getter
public class ApplicableNetwork {

    private final String label;
    private final Map<String, URL> links;
    private final ArrayList<InputElement> inputElements;

    public ApplicableNetwork(String label, Map<String, URL> links, ArrayList<InputElement> inputElements) {
        this.label = label;
        this.links = links;
        this.inputElements = inputElements;
    }

    /**
     * Dummy fields to support the design
     */
    private final String expiry = "03/24";
    private final String last4Digits = "6492";
    private final String cardHolder = "John Doe";
}