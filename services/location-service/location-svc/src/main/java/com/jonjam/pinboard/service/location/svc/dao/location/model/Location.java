package com.jonjam.pinboard.service.location.svc.dao.location.model;

import com.jonjam.pinboard.common.objectmodel.Immutable;

@Immutable
public abstract class Location {

    public abstract long getLocationId();

    // TODO Ref type
    public abstract long getLocationCode();

    public abstract LocationStatus getLocationStatus();

    public static class Builder extends ImmutableLocation.Builder {}
}

