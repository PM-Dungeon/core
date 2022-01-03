package textures;

import com.badlogic.gdx.files.FileHandle;

public record TextureWrapper(String path, String name, String prefix) {
    public TextureWrapper(FileHandle fh) {
        this(fh.path(), fh.name(), fh.parent().path());
    }
}
