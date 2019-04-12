package de.adorsys.datasafe.business.api.credentials.dto;

import lombok.Data;

@Data
public class DFSCredentials {

    private final String s3AccessKey;
    private final String s3AccessSecret;
}