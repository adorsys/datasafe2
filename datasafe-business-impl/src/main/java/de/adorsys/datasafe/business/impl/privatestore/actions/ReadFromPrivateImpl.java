package de.adorsys.datasafe.business.impl.privatestore.actions;

import de.adorsys.datasafe.business.api.deployment.credentials.BucketAccessService;
import de.adorsys.datasafe.business.api.deployment.document.DocumentReadService;
import de.adorsys.datasafe.business.api.deployment.keystore.PrivateKeyService;
import de.adorsys.datasafe.business.api.deployment.pathencryption.PathEncryption;
import de.adorsys.datasafe.business.api.deployment.privatespace.actions.ReadFromPrivate;
import de.adorsys.datasafe.business.api.deployment.profile.ProfileRetrievalService;
import de.adorsys.datasafe.business.api.types.DFSAccess;
import de.adorsys.datasafe.business.api.types.action.ReadRequest;
import de.adorsys.datasafe.business.api.types.privatespace.PrivateReadRequest;
import de.adorsys.dfs.connection.api.complextypes.BucketPath;

import javax.inject.Inject;
import java.net.URI;
import java.util.function.Function;

public class ReadFromPrivateImpl implements ReadFromPrivate {

    private final PrivateKeyService privateKeyService;
    private final BucketAccessService accessService;
    private final DocumentReadService reader;
    private final PathEncryption pathEncryption;

    @Inject
    public ReadFromPrivateImpl(PrivateKeyService privateKeyService, BucketAccessService accessService,
                               DocumentReadService reader, PathEncryption pathEncryption) {
        this.privateKeyService = privateKeyService;
        this.accessService = accessService;
        this.reader = reader;
        this.pathEncryption = pathEncryption;
    }

    @Override
    public void read(PrivateReadRequest request) {
        DFSAccess userInbox = accessService.privateAccessFor(
                request.getOwner(),
                resolveFileLocation(request)
        );

        ReadRequest readRequest = ReadRequest.builder()
                .from(userInbox)
                .keyStore(privateKeyService.keystore(request.getOwner()))
                .response(request.getResponse())
                .build();

        reader.read(readRequest);
    }

    private Function<ProfileRetrievalService, URI> resolveFileLocation(PrivateReadRequest request) {
        return profiles -> profiles
            .privateProfile(request.getOwner())
            .getPrivateStorage()
            // TODO: Decrypt file name and path
            .resolve(request.getPath());
    }
}
