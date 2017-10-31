package cz.sparko.boxitory.factory;

import cz.sparko.boxitory.conf.AppProperties;
import cz.sparko.boxitory.service.filesystem.FilesystemDigestHashService;
import cz.sparko.boxitory.service.HashService.HashAlgorithm;
import cz.sparko.boxitory.service.noop.NoopHashService;
import cz.sparko.boxitory.service.HashService;
import cz.sparko.boxitory.service.HashStore;

public class HashServiceFactory {

    public static HashService createHashService(AppProperties appProperties, HashStore hashStore) {
        HashAlgorithm algorithm = appProperties.getChecksum();
        if (algorithm == HashAlgorithm.DISABLED) {
            return new NoopHashService();
        } else {
            return new FilesystemDigestHashService(appProperties.getChecksum(), appProperties.getChecksum_buffer_size(),
                                                   hashStore);
        }
    }
}
