package com.carhub.carrental.Fleet.domain.model.valueobject;

import java.net.URI;
import java.util.regex.Pattern;
import com.carhub.carrental.Common.exception.DomainAssert;
import com.carhub.carrental.Common.exception.DomainException;

public record Photo(
        URI uri,
        boolean isPrimary,
        int order
) {
    private static final String HTTP = "http";
    private static final String HTTPS = "https";
    private static final Pattern VALID_IMAGE_EXTENSION_PATTERN = Pattern.compile("(?i)\\.(jpg|jpeg|png|webp)$");

    public Photo {
        DomainAssert.notNull(uri, "car.photo.NotNull", "Photo URL cannot be null or blank");
        DomainAssert.isConditionTrue(
                order >= 0,
                "car.photo.InvalidOrder",
                "Photo order must be a non-negative integer"
        );

        if (uri.getScheme() == null || uri.getHost() == null) {
            throw new DomainException("car.photo.InvalidURI", "Photo URL must be a valid URI with scheme and host");
        }

        String scheme = uri.getScheme().toLowerCase();
        if (!HTTP.equals(scheme) && !HTTPS.equals(scheme)) {
            throw new DomainException("car.photo.InvalidScheme", "Photo URL must use http or https scheme");
        }

        String path = uri.getPath();
        if (path == null || !VALID_IMAGE_EXTENSION_PATTERN.matcher(path).find()) {
            throw new DomainException(
                    "car.photo.InvalidFormat",
                    "Photo URL must point to an image file (jpg, jpeg, png, webp)"
            );
        }
    }

    public static Photo cover(URI coverUri) {
        return new Photo(coverUri, true, 0);
    }

    public static Photo gallery(URI coverUri, int order) {
        return new Photo(coverUri, false, order);
    }
}
