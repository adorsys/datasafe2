package de.adorsys.datasafe.business.impl.e2e.performance.fixture.generator;

import de.adorsys.datasafe.business.impl.e2e.performance.fixture.dto.ContentId;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

public class RandomContentIdGenerator {

    private final Random random;
    private final List<ContentId> idPool;

    public RandomContentIdGenerator(Random random, int maxContentEntries) {
        this.random = random;
        idPool = new ArrayList<>();
        IntStream.range(0, maxContentEntries).forEach(it -> idPool.add(new ContentId(UUID.randomUUID().toString())));
    }

    public ContentId randomContentId() {
        return idPool.get(random.nextInt(idPool.size()));
    }
}
