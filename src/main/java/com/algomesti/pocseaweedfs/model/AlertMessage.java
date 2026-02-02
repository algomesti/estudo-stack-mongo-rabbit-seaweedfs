package com.algomesti.pocseaweedfs.model;

import java.time.Instant;

public record AlertMessage(
        String cameraId,
        String type,
        String localPath,
        Instant timestamp // Alterado de String para Instant para tipagem forte
) {}