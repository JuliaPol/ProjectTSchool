package com.tsystems.ecare.exception;

public class ResourcePermissionException extends RuntimeException {
    public ResourcePermissionException() {
        super("Somebody have not permission to resource");
    }
}
