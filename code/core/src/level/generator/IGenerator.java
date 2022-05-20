package level.generator;

import level.elements.Level;

public interface IGenerator {
    /**
     * Get a level with a random configuration.
     *
     * @return The level.
     */
    Level getLevel();
}
