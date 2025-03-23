package net.rankedproject.common.data.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public abstract class BasePlayer {
    private UUID id;
}
