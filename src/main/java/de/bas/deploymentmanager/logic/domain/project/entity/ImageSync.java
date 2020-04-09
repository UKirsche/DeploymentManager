package de.bas.deploymentmanager.logic.domain.project.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImageSync {

    public static final String DELIMITER = ",";
    @Getter
    @Setter
    private List<String> projekctIdentifiers;


    public ImageSync(String imageSync) {
        if (imageSync == null || imageSync.isEmpty()) {
            return;
        }
        String[] split = imageSync.split(DELIMITER);
        projekctIdentifiers = new ArrayList<>(Arrays.asList(split));
    }

    public String getPersistedValue() {
        if (projekctIdentifiers == null) {
            return null;
        }
        return String.join(DELIMITER, projekctIdentifiers);
    }

    public boolean isAktiv() {
        return projekctIdentifiers != null;
    }
}
