/**
 *
 */
package com.ecaresoft.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

import edu.vt.middleware.password.AlphabeticalSequenceRule;
import edu.vt.middleware.password.CharacterCharacteristicsRule;
import edu.vt.middleware.password.DigitCharacterRule;
import edu.vt.middleware.password.LengthRule;
import edu.vt.middleware.password.LowercaseCharacterRule;
import edu.vt.middleware.password.NumericalSequenceRule;
import edu.vt.middleware.password.Password;
import edu.vt.middleware.password.PasswordData;
import edu.vt.middleware.password.PasswordGenerator;
import edu.vt.middleware.password.PasswordValidator;
import edu.vt.middleware.password.QwertySequenceRule;
import edu.vt.middleware.password.RepeatCharacterRegexRule;
import edu.vt.middleware.password.Rule;
import edu.vt.middleware.password.RuleResult;
import edu.vt.middleware.password.RuleResultDetail;
import edu.vt.middleware.password.UsernameRule;
import edu.vt.middleware.password.WhitespaceRule;

/**
 * Validates that the password complies with certain rules.
 * Password must be 8 to 20 characters long
 * should not contain blanks
 * should contain at least one digit
 * should contain at least one upper case letter (removed)
 * should contain at least one lower case letter
 * should not contain alphabetic sequences (like stuvw or KLMNO)
 * should not contain more than 3 consecutive digits
 * should not contain keyboard sequences (qwerty, asdfg, zxcvb, etc)
 * should not contain more than 4 times the same character
 * should not be equal to or contain the user name
 * @author mrojas
 *
 */
public class PasswordHandler {

	private static final int MIN_LEN = 8;
	private static final int MAX_LEN = 20;

	// application context
	private transient Properties ctx;

	// password must be between MIN_LEN and MAX_LEN chars long
	private transient LengthRule lengthRule = null;

	// don't allow whitespace
	private transient WhitespaceRule whitespaceRule = null;

	// control allowed characters
	private transient CharacterCharacteristicsRule charRule = null;

	// don't allow alphabetical sequences
	private transient AlphabeticalSequenceRule alphaSeqRule = null;

	// don't allow numerical sequences of length 3
	private transient NumericalSequenceRule numSeqRule = null;

	// don't allow qwerty sequences
	private transient QwertySequenceRule qwertySeqRule = null;

	// don't allow 4 repeat characters
	private transient RepeatCharacterRegexRule repeatRule = null;

	// don't allow user name in the password (ignore case, check backwards)
	private transient UsernameRule usernameRule = null;

	// group all rules together in a List
	private transient List<Rule> ruleList = null;

	private transient PasswordValidator validator = null;


	public PasswordHandler(Properties ctx) {
		super();

		this.ctx = ctx;

		init();
	}


	private void init() {
		// password must be between MIN_LEN and MAX_LEN chars long
		lengthRule = new LengthRule(MIN_LEN, MAX_LEN);

		// don't allow whitespace
		whitespaceRule = new WhitespaceRule();

		// control allowed characters
		charRule = new CharacterCharacteristicsRule();
		// require at least 1 digit in passwords
		charRule.getRules().add(new DigitCharacterRule(1));
		// require at least 1 upper case char (removed, hope only temporarily)
		//charRule.getRules().add(new UppercaseCharacterRule(1));
		// require at least 1 lower case char
		charRule.getRules().add(new LowercaseCharacterRule(1));
		// require at least 3 of the previous rules be met
		charRule.setNumberOfCharacteristics(2);

		// don't allow alphabetical sequences
		alphaSeqRule = new AlphabeticalSequenceRule();

		// don't allow numerical sequences of length 3
		numSeqRule = new NumericalSequenceRule(3, false);

		// don't allow qwerty sequences
		qwertySeqRule = new QwertySequenceRule();

		// don't allow 4 repeat characters
		repeatRule = new RepeatCharacterRegexRule(4);

		// don't allow user name in the password (ignore case, check backwards)
		usernameRule = new UsernameRule(true, true);

		// group all rules together in a List
		ruleList = new ArrayList<Rule>();
		ruleList.add(lengthRule);
		ruleList.add(whitespaceRule);
		ruleList.add(charRule);
		ruleList.add(alphaSeqRule);
		ruleList.add(numSeqRule);
		ruleList.add(qwertySeqRule);
		ruleList.add(repeatRule);
		ruleList.add(usernameRule);

		validator = new PasswordValidator(ruleList);
	}

	public String checkPassword(final String password, final String username) {

		StringBuffer errorMessages = new StringBuffer();
		String retVal = null;

		PasswordData passwordData = new PasswordData(new Password(password));
		passwordData.setUsername(username);

		RuleResult result = validator.validate(passwordData);
		if (!result.isValid()) {
			List<RuleResultDetail> resultDetails = result.getDetails();
			for (RuleResultDetail ruleResultDetail : resultDetails) {
				errorMessages.append(generateErrorMessage(ruleResultDetail)).append('\n');
			}
		}

		if(errorMessages.length() > 0) {
			retVal = errorMessages.toString();
		}

		return retVal;
	}


	private String generateErrorMessage(RuleResultDetail detail) {
		final String key = detail.getErrorCode();
		final String message =
				Utilerias.getMessage(ctx, Env.getLoginLanguage(ctx).getLocale(), key);

		String format = StringUtils.EMPTY;
		if (message != null) {
			format = String.format(message, detail.getValues());
		} else {
			if (detail.getParameters().isEmpty()) {
				format = String.format("%s", key);
			} else {
				format = String.format("%s:%s", key, detail.getParameters());
			}
		}

		if("es".equals(Env.getLoginLanguage(ctx).getLocale().getLanguage())) {
			format = StringUtils.replace(format, " digit.", " num\u00e9rico(s).");
			format = StringUtils.replace(format, " non-alphanumeric.", " no alfa-num\u00e9rico(s).");
			format = StringUtils.replace(format, " uppercase.", " en may\u00fascula(s).");
			format = StringUtils.replace(format, " lowercase.", " en min\u00fascula(s).");
		}

		return format;
	}

	public String generatePassword() {
		return generatePassword(10);
	}
	
	public String generatePassword(int chars) {
		String retVal = null;
		PasswordGenerator generator = new PasswordGenerator();

		retVal = generator.generatePassword(chars, charRule.getRules());

		return retVal;
	}


	public static void main(String[] args) {
		Properties ctx = new Properties();

		PasswordHandler passwdCheck = new PasswordHandler(ctx);
		String password = passwdCheck.generatePassword();
		System.out.println(password);

		System.out.println("Error msg :" + passwdCheck.checkPassword(password, "mrojas"));

	}
}
