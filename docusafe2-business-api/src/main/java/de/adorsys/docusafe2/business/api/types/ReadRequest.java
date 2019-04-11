package de.adorsys.docusafe2.business.api.types;

import de.adorsys.docusafe2.business.api.keystore.types.KeyStoreAccess;
import de.adorsys.docusafe2.business.api.types.file.FileOut;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class ReadRequest {

    @NonNull
    private final DFSAccess from;

    @NonNull
    private final FileOut response;

    @NonNull
    private final KeyStoreAccess keyStore;
}