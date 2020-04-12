package de.bas.deploymentmanager.logic.domain.project.entity;

import de.bas.deploymentmanager.logic.domain.project.entity.exception.TagNotValidException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class Tag {

    @Embedded
    private Version version;

    @Column(name = "BUILD_NUMBER")
    private Integer buildNumber;

    public Tag(Version version, int buildNumber) {
        this.version = version;
        this.buildNumber = buildNumber;
    }

    public Tag() {
    }

    public Tag(String tag) throws TagNotValidException {
        this.version = new Version(tag);
        this.buildNumber = getBuildnumberFromString(tag);
    }

    private Integer getBuildnumberFromString(String tag) throws TagNotValidException {
        if (tag != null && !tag.isEmpty()) {
            String[] split = tag.split("-");
            if (split.length == 2) {
                String buildNr = split[1];
                return Integer.valueOf(buildNr);
            }
        }
        throw new TagNotValidException(tag);
    }


    @Override
    public String toString() {
        return String.format("%s-%s", version, buildNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(version, tag.version) &&
                Objects.equals(buildNumber, tag.buildNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(version, buildNumber);
    }
}
