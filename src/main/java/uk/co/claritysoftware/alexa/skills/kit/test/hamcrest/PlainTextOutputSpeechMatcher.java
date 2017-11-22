package uk.co.claritysoftware.alexa.skills.kit.test.hamcrest;

import static java.lang.String.format;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import com.amazon.speech.ui.PlainTextOutputSpeech;

/**
 * Hamcrest matchers for making assertions on a {@link PlainTextOutputSpeech}
 */
public class PlainTextOutputSpeechMatcher {

	private PlainTextOutputSpeechMatcher() {

	}

	/**
	 * Returns a {@link PlainTextOutputSpeechTextMatcher} to assert the text content of the {@link PlainTextOutputSpeech}
	 *
	 * @param expectedSpeechText the text that the {@link PlainTextOutputSpeech} is expected to have
	 * @return the PlainTextOutputSpeechTextMatcher
	 */
	public static PlainTextOutputSpeechTextMatcher hasText(String expectedSpeechText) {
		return new PlainTextOutputSpeechTextMatcher(expectedSpeechText);
	}

	/**
	 * Returns a {@link PlainTextOutputSpeechPatternMatcher} to assert the text content of the {@link PlainTextOutputSpeech}
	 *
	 * @param expectedSpeechTextPattern the pattern that the {@link PlainTextOutputSpeech}'s text is expected to match
	 * @return the PlainTextOutputSpeechPatternMatcher
	 */
	public static PlainTextOutputSpeechPatternMatcher hasText(Pattern expectedSpeechTextPattern) {
		return new PlainTextOutputSpeechPatternMatcher(expectedSpeechTextPattern);
	}

	/**
	 * Hamcrest matcher for asserting {@link PlainTextOutputSpeech} has the specified speech text
	 */
	static class PlainTextOutputSpeechTextMatcher extends TypeSafeDiagnosingMatcher<PlainTextOutputSpeech> {

		private final String expectedSpeechText;

		private PlainTextOutputSpeechTextMatcher(String expectedSpeechText) {
			this.expectedSpeechText = expectedSpeechText;
		}

		@Override
		protected boolean matchesSafely(PlainTextOutputSpeech plainTextOutputSpeech, Description description) {
			String actualSpeechText = plainTextOutputSpeech.getText();

			if (actualSpeechText == null && expectedSpeechText == null) {
				return true;
			}

			if (actualSpeechText == null && expectedSpeechText != null || !actualSpeechText.equals(expectedSpeechText)) {
				description.appendText(format("was <%s>", actualSpeechText));
				return false;
			}

			return true;
		}

		@Override
		public void describeTo(Description description) {
			description.appendText(format("Expected PlainTextOutputSpeech to have text of <%s>", expectedSpeechText));
		}

	}

	/**
	 * Hamcrest matcher for asserting the {@link PlainTextOutputSpeech} text matches the specified pattern
	 */
	static class PlainTextOutputSpeechPatternMatcher extends TypeSafeDiagnosingMatcher<PlainTextOutputSpeech> {

		private final Pattern expectedSpeechTextPattern;

		private PlainTextOutputSpeechPatternMatcher(Pattern expectedSpeechTextPattern) {
			this.expectedSpeechTextPattern = expectedSpeechTextPattern;
		}

		@Override
		protected boolean matchesSafely(PlainTextOutputSpeech plainTextOutputSpeech, Description description) {
			String actualSpeechText = plainTextOutputSpeech.getText();

			if (actualSpeechText == null && expectedSpeechTextPattern == null) {
				return true;
			}

			if (expectedSpeechTextPattern == null) {
				description.appendText(format("was <%s>", actualSpeechText));
				return false;
			}

			Matcher matcher = expectedSpeechTextPattern.matcher(actualSpeechText != null ? actualSpeechText : "");
			if (actualSpeechText == null || !matcher.find()) {
				description.appendText(format("was <%s>", actualSpeechText));
				return false;
			}

			return true;
		}

		@Override
		public void describeTo(Description description) {
			description.appendText(format("Expected PlainTextOutputSpeech to match text pattern <%s>", expectedSpeechTextPattern != null ? expectedSpeechTextPattern.pattern() : null));
		}
	}
}
