package de.adorsys.datasafe.business.api.types.action;

import de.adorsys.datasafe.business.api.types.UserIDAuth;
import de.adorsys.datasafe.business.api.types.keystore.PublicKeyIDWithPublicKey;
import de.adorsys.datasafe.business.api.types.resource.PrivateResource;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class PrivateWriteRequest {

    @NonNull
    private final UserIDAuth owner;

    @NonNull
    private final PrivateResource to;
}
