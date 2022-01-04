package textures;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/** Singleton, lazy initialization (possibly not thread-safe). */
public class TextureHandler {
    private static TextureHandler instance;

    private final Map<String, Set<TextureWrapper>> pathMap = new LinkedHashMap<>();

    private TextureHandler() {
        addAllAssets(Gdx.files.internal(Gdx.files.getLocalStoragePath()));
    }

    public static TextureHandler getInstance() {
        if (instance == null) {
            instance = new TextureHandler();
        }
        return instance;
    }

    private void addAllAssets(FileHandle fh) {
        if (fh.isDirectory()) {
            Arrays.stream(fh.list()).forEach(this::addAllAssets);
        } else {
            TextureWrapper w = new TextureWrapper(fh);
            pathMap.computeIfAbsent(w.path(), x -> new LinkedHashSet<>()).add(w);
        }
    }

    public Set<String> getAvailablePaths() {
        return pathMap.keySet();
    }

    private List<String> getTexturesForPath(String path) {
        return pathMap.get(path).stream().map(TextureWrapper::path).collect(Collectors.toList());
    }

    /**
     * Searches for all textures paths that matches with the given regular expression.
     *
     * @param regex the regular expression
     * @return a String List with all texture paths, that have matched
     */
    public List<String> getTextures(String regex) {
        Pattern pattern = Pattern.compile(regex);
        return getAvailablePaths().stream()
                .filter(pattern.asPredicate())
                .map(this::getTexturesForPath)
                .collect(ArrayList::new, List::addAll, List::addAll);
    }
}
