package de.adorsys.datasafe.business.api.version;

import de.adorsys.datasafe.business.api.types.UserIDAuth;
import de.adorsys.datasafe.business.api.types.action.ListRequest;
import de.adorsys.datasafe.business.api.types.resource.*;

import java.util.stream.Stream;

/**
 * Links resource versions with its latest version descriptor.
 * @param <V> Versioning class
 */
public interface VersionInfoService<V extends Version> {

    /**
     * Provides all available versions of resource with its timestamp
     * @param request where to list data
     * @return resource with timestamp, with its latest snapshot location and version
     */
    Stream<Versioned<AbsoluteLocation<ResolvedResource>, PrivateResource, V>> versionsOf(
            ListRequest<UserIDAuth, PrivateResource> request
    );

    /**
     * Provides all available versions of resource with its timestamp joined with latest version (to compare time)
     * @param request where to list data
     * @return resource with timestamp, with its latest snapshot location (with timestamp) and version
     */
    Stream<Versioned<AbsoluteLocation<ResolvedResource>, ResolvedResource, V>> listJoinedWithLatest(
            ListRequest<UserIDAuth, PrivateResource> request);
}