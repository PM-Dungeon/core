package graphic;

import com.badlogic.gdx.files.FileHandle;

import java.util.Objects;

public class TextureType implements Comparable<TextureType> {
    private final String prefix;
    private final String name;
    private final String path;

    public TextureType(FileHandle fh) {
        prefix = fh.parent().path();
        name = fh.name();
        path = fh.path();
    }

    public String getPrefix() {
        return prefix;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    @Override
    public int compareTo(TextureType o) {
        return path.compareTo(o.path);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TextureType that = (TextureType) o;
        return Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path);
    }
}
