package edu.neumont.jjensen.lab;

/**
 * Created with IntelliJ IDEA.
 * User: Jonathan
 * Date: 11/13/13
 * Time: 2:37 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Actionable {
    public boolean performAction(String next, PatternMatcher patternMatcher);
}
