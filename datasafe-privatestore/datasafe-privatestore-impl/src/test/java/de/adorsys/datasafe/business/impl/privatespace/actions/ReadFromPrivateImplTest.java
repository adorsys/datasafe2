package de.adorsys.datasafe.business.impl.privatespace.actions;

import de.adorsys.datasafe.business.api.encryption.document.EncryptedDocumentReadService;
import de.adorsys.datasafe.business.api.types.UserID;
import de.adorsys.datasafe.business.api.types.UserIDAuth;
import de.adorsys.datasafe.business.api.types.action.ReadRequest;
import de.adorsys.datasafe.business.api.types.keystore.ReadKeyPassword;
import de.adorsys.datasafe.business.api.types.resource.AbsoluteLocation;
import de.adorsys.datasafe.business.api.types.resource.BasePrivateResource;
import de.adorsys.datasafe.business.api.types.resource.PrivateResource;
import de.adorsys.datasafe.shared.BaseMockitoTest;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.ByteArrayInputStream;
import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class ReadFromPrivateImplTest extends BaseMockitoTest {

    private static final String BYTES = "Hello";
    private static final URI ABSOLUTE_PATH = URI.create("s3://absolute");

    private UserIDAuth auth = new UserIDAuth(new UserID(""), new ReadKeyPassword(""));

    @Mock
    private EncryptedResourceResolver resolver;

    @Mock
    private EncryptedDocumentReadService readService;

    @InjectMocks
    private ReadFromPrivateImpl inbox;

    @Captor
    private ArgumentCaptor<ReadRequest<UserIDAuth, AbsoluteLocation<PrivateResource>>> captor;

    @Test
    void read() {
        AbsoluteLocation<PrivateResource> resource = BasePrivateResource.forAbsolutePrivate(ABSOLUTE_PATH);
        ReadRequest<UserIDAuth, PrivateResource> request = ReadRequest.forPrivate(
                auth,
                BasePrivateResource.forPrivate(ABSOLUTE_PATH)
        );
        when(resolver.encryptAndResolvePath(request.getOwner(), request.getLocation())).thenReturn(resource);
        when(readService.read(captor.capture())).thenReturn(new ByteArrayInputStream(BYTES.getBytes()));

        assertThat(inbox.read(request)).hasContent(BYTES);
        assertThat(captor.getValue().getLocation()).isEqualTo(resource);
    }
}