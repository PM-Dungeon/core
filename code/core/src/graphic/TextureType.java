package graphic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;

public class TextureType implements Comparable<TextureType> {
    private final String name;
    private final String folderPath;
    private final TextureType2[] availableTextures;

    public TextureType(String name, String folderPath) {
        this.name = name;
        this.folderPath = folderPath;
        availableTextures =
                Arrays.stream(Gdx.files.internal(folderPath).list())
                        .filter(Predicate.not(FileHandle::isDirectory))
                        .map(fh -> new TextureType2(fh.name(), fh.path()))
                        .toArray(TextureType2[]::new);
    }

    @Override
    public int compareTo(TextureType o) {
        return name.compareTo(o.name);
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
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public TextureType2[] getAvailableTextures() {
        return availableTextures;
    }
}
