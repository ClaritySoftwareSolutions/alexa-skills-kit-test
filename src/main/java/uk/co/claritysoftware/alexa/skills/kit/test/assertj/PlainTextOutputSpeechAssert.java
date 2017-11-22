package uk.co.claritysoftware.alexa.skills.kit.test.assertj;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.assertj.core.api.AbstractAssert;
import com.amazon.speech.ui.PlainTextOutputSpeech;

/**
 * Specialisation of Assertj {@link AbstractAssert} for making assertions on {@link PlainTextOutputSpeech} instances
 */
public class PlainTextOutputSpeechAssert extends AbstractAssert<PlainTextOutputSpeechAssert, PlainTextOutputSpeech> {

	private PlainTextOutputSpeechAssert(PlainTextOutputSpeech outputSpeech) {
		super(outputSpeech, PlainTextOutputSpeechAssert.class);
	}

	public static PlainTextOutputSpeechAssert assertThat(PlainTextOutputSpeech actual) {
		if (actual == null) {
			throw new IllegalArgumentException("Cannot make assertions on null PlainTextOutputSpeech");
		}
		return new PlainTextOutputSpeechAssert(actual);
	}

	/**
	 * Assert that the {@link PlainTextOutputSpeech} has the specified speech text
	 *
	 * @param expectedSpeechText the expected speech text
	 * @return this {@link PlainTextOutputSpeechAssert} for further assertion chaining
	 */
	public PlainTextOutputSpeechAssert hasText(String expectedSpeechText) {
		String actualSpeechText = this.actual.getText();

		if (actualSpeechText == null && expectedSpeechText == null) {
			return this;
		}

		if (actualSpeechText == null && expectedSpeechText != null || !actualSpeechText.equals(expectedSpeechText)) {
			failWithMessage("Expected PlainTextOutputSpeech to have text of <%s> but was <%s>", expectedSpeechText, actualSpeechText);
		}

		return this;
	}

	/**
	 * Assert that the {@link PlainTextOutputSpeech} text matches the specified pattern
	 *
	 * @param expectedSpeechTextPattern the pattern for the expected speech text
	 * @return this {@link PlainTextOutputSpeechAssert} for further assertion chaining
	 */
	public PlainTextOutputSpeechAssert hasText(Pattern expectedSpeechTextPattern) {
		String actualSpeechText = this.actual.getText();

		if (actualSpeechText == null && expectedSpeechTextPattern == null) {
			return this;
		}

		if (expectedSpeechTextPattern == null) {
			failWithMessage("Expected PlainTextOutputSpeech to match text pattern <%s> but was <%s>", expectedSpeechTextPattern, actualSpeechText);
		}

		Matcher matcher = expectedSpeechTextPattern.matcher(actualSpeechText != null ? actualSpeechText : "");
		if (actualSpeechText == null || !matcher.find()) {
			failWithMessage("Expected PlainTextOutputSpeech to match text pattern <%s> but was <%s>", expectedSpeechTextPattern.pattern(), actualSpeechText);
		}

		return this;
	}
}
