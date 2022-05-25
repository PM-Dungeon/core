package level.generator;

import level.elements.Level;
import level.tools.DesignLabel;
import level.tools.LevelSize;

public interface IGenerator {
    /**
     * Get a level with the given configuration.
     *
     * @param designLabel Design of the level
     * @param size Size of the level
     * @return The level
     */
    Level getLevel(DesignLabel designLabel, LevelSize size);

    /**
     * Get a level with a random configuration.
     *
     * @return The level.
     */
    default Level getLevel() {
        return getLevel(DesignLabel.randomDesign(), LevelSize.randomSize());
    }

    /**
     * Get a level with the given configuration and a random size.
     *
     * @param designLabel Design of the level
     * @return The level
     */
    default Level getLevel(DesignLabel designLabel) {
        return getLevel(designLabel, LevelSize.randomSize());
    }

    /**
     * Get a level with the given configuration and a random design.
     *
     * @param size Size of the level
     * @return The level
     */
    default Level getLevel(LevelSize size) {
        return getLevel(DesignLabel.randomDesign(), size);
    }
}
