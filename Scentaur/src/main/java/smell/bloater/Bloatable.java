package smell.bloater;
import smell.Smellable; // Ctrl + Shift + O to import a package
/**
 * This interface contains all common behaviour among bloaters;
 * long method, large class, primitive obsession, data clumps
 * and long parameter list
 * 
 */
public interface Bloatable extends Smellable{
	abstract void setThreshold(int value); // This method sets the threshold value for a Bloatable instance.
}
