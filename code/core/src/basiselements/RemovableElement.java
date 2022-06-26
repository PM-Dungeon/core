package basiselements;

public interface RemovableElement {
    /**
     * @return <code>true</code>, if this instance can be deleted; <code>false</code> otherwise
     */
    default boolean removable() {
        return false;
    }
}
