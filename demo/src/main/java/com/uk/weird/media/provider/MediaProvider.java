package com.uk.weird.media.provider;

import com.uk.weird.media.entity.MediaType;

import java.util.List;

public interface MediaProvider {

    MediaType type();

    List<MediaSummary> search(String query);

    MediaDetails getByExternalId(String externalId);
}
