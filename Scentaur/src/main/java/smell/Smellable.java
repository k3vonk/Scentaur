package smell;
/**
 * This interface contains behaviour common to all types
 * of code smells.
 */
public interface Smellable {
	abstract boolean isClassEmpty(Object obj); // Checks if a class contains any methods or instance variables. Lazy class?
}
