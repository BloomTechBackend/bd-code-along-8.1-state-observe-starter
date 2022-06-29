package validators;

import validators.rules.*;

import java.util.ArrayList;
import java.util.List;

public class Validator {

    private final Rule[] rules;

    private Validator(Rule[] rules) {
        this.rules = rules;
    }

    public boolean validate(String value) {
        for (Rule rule : rules) {
            if (!rule.isValid(value)) {
                return false;
            }
        }
        return true;
    }

    public static class Builder {

        private List<Rule> rules = new ArrayList<>();

        public Builder() {}

        public Builder characterLimit(int minimumSize) {
            rules.add(new MinimumSizeRule(minimumSize));
            return this;
        }

        public Builder mustIncludeLowerCaseLetter() {
            rules.add(new LowerCaseRule());
            return this;
        }

        public Builder mustIncludeUpperCaseLetter() {
            rules.add(new UpperCaseRule());
            return this;
        }

        public Builder mustIncludeSpecialCharacter() {
            rules.add(new SpecialCharacterRule());
            return this;
        }

        public Builder mustIncludeNumber() {
            rules.add(new NumberRule());
            return this;
        }

        public Builder addRule(Rule rule) {
            rules.add(rule);
            return this;
        }

        public Validator build() {
            return new Validator(rules.toArray(new Rule[rules.size()]));
        }

    }
}
