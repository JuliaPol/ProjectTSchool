package com.tsystems.ecare.exception;

public class ContractUpdateException  extends RuntimeException {
    public ContractUpdateException() {
        super("Failed to update contract");
    }
}
