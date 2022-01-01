package graphic;

import com.badlogic.gdx.files.FileHandle;

public record TextureType(String prefix, String name, String path) {
    public TextureType(FileHandle fh) {
        this(fh.parent().path(), fh.name(), fh.path());
    }
}
