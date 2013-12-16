package edu.neumont.jjensen.lab;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: jjensen
 * Date: 11/13/13
 * Time: 2:38 PM
 */
public class PatternMatcher implements Actionable {
    private String pattern;
    private Actionable action;

    public PatternMatcher(String pattern, Actionable action) {
        this.pattern = pattern;
        this.action = action;
    }

    @Override
    public boolean performAction(String move, PatternMatcher patternMatcher) {
        return action.performAction(move, patternMatcher);
    }

    public Matcher getMatcher(String move) {
        Pattern thePattern = Pattern.compile(pattern);
        return thePattern.matcher(move);
    }


}
