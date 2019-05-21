package de.adorsys.datasafe.business.api.privatespace.actions;

import de.adorsys.datasafe.business.api.types.UserIDAuth;
import de.adorsys.datasafe.business.api.types.actions.WriteRequest;
import de.adorsys.datasafe.business.api.types.resource.PrivateResource;

import java.io.OutputStream;

public interface WriteToPrivate {

    OutputStream write(WriteRequest<UserIDAuth, PrivateResource> request);
}